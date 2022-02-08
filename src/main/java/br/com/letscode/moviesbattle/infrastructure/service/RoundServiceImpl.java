package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.api.model.payload.convert.ConvertToRoundValidateResponse;
import br.com.letscode.moviesbattle.api.model.payload.response.RoundValidateResponse;
import br.com.letscode.moviesbattle.domain.constants.DomainConstants;
import br.com.letscode.moviesbattle.domain.exceptionhandler.RoundPlayedException;
import br.com.letscode.moviesbattle.api.model.enums.ChoiceMovieEnum;
import br.com.letscode.moviesbattle.core.security.service.LoggedInUser;
import br.com.letscode.moviesbattle.domain.exceptionhandler.TotalErrorsException;
import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.model.Movie;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.User;
import br.com.letscode.moviesbattle.domain.model.enums.RoundStatusEnum;
import br.com.letscode.moviesbattle.domain.repository.GameRepository;
import br.com.letscode.moviesbattle.domain.repository.MovieRepository;
import br.com.letscode.moviesbattle.domain.repository.RoundRepository;
import br.com.letscode.moviesbattle.domain.repository.UserRepository;
import br.com.letscode.moviesbattle.domain.service.RoundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static br.com.letscode.moviesbattle.domain.constants.DomainConstants.*;

@Slf4j
@Service
public class RoundServiceImpl implements RoundService {

    @Value("${moviesbattle.app.totalErrors}")
    private int TOTAL_ERRORS_ALLOWED;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    public Round initializeRound(Game gameEntity) {
        int numberRound = getNumberRound(gameEntity);
        Round roundEntity = new Round();

        boolean isNotValidPair = true;
        while (isNotValidPair) {
            Movie leftMovieEntity = movieRepository.getRandomMovie();
            Movie rightMovieEntity = movieRepository.getRandomMovie();
            if (isRoundValid(gameEntity, leftMovieEntity, rightMovieEntity).isEmpty()) {
                isNotValidPair = false;
                setMovieData(gameEntity, numberRound, roundEntity, leftMovieEntity, rightMovieEntity);
            }
        }
        return saveRound(roundEntity);
    }

    @Override
    public RoundValidateResponse processRound(LoggedInUser loggedInUser, Long id,
                                              ChoiceMovieEnum userChoice) {
        Round roundEntity = roundRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Entity %s of type %s not found", id, Game.class.getName())));

        if (isRoundPlayed(roundEntity)) {
            throw new RoundPlayedException("The roundEntity has already been played");
        }

        validateSelectedMovieInRound(userChoice, roundEntity);

        updateGameTotalErrors(roundEntity);

        updateUserInformationWithRoundResult(roundEntity);

        saveRound(roundEntity);

        return ConvertToRoundValidateResponse.fromEntity(roundEntity);
    }

    private boolean isRoundPlayed(Round round) {
        return round.getStatus() == RoundStatusEnum.PLAYED;
    }

    private void validateSelectedMovieInRound(ChoiceMovieEnum choice, Round round) {
        ChoiceMovieEnum choiceCorrect = getCorrectOption(round);
        round.setCorrect(choiceCorrect == choice);
        round.setChoice(choiceCorrect);
        round.setStatus(RoundStatusEnum.PLAYED);
    }

    private void updateUserInformationWithRoundResult(Round round) {
        User user = round.getGame().getUser();

        int totalCorrectRounds = user.getTotalCorrectRounds();
        int totalRoundsPlayed = user.getTotalRoundsPlayed();
        if (round.isCorrect()) {
            user.setTotalCorrectRounds(getTotalCorrectRounds(totalCorrectRounds));
        }
        user.setTotalRoundsPlayed(getTotalRoundsPlayed(totalRoundsPlayed));

        user.setScore(getScoreUser(user));
        userRepository.save(user);
    }

    private int getTotalCorrectRounds(int totalCorrectRounds) {
        return totalCorrectRounds + ADD_ANOTHER_CORRECT_ROUND;
    }

    private int getTotalRoundsPlayed(int totalRoundsPlayed) {
        return totalRoundsPlayed + ADD_ONE_MORE_ROUND_PLAYED;
    }

    private double getScoreUser(User user) {
        int totalCorrectRounds = user.getTotalCorrectRounds();
        double totalRoundsPlayed = user.getTotalRoundsPlayed();
        double correctPercentage = getUserAccuracyPercentage(totalCorrectRounds, totalRoundsPlayed);
        return correctPercentage * totalRoundsPlayed;
    }

    private double getUserAccuracyPercentage(int totalCorrectRounds, double totalRoundsPlayed) {
        return totalCorrectRounds * 100 / totalRoundsPlayed;
    }

    private void updateGameTotalErrors(Round roundEntity) {
        Game gameEntity = roundEntity.getGame();

        if (ruleTotalErrors(roundEntity, gameEntity)) {
            throw new TotalErrorsException("The maximum amount of errors has been reached");

        } else if (isNotCorrect(roundEntity)) {
            int numberErrors = gameEntity.getTotalErrors();
            gameEntity.setTotalErrors(numberErrors + 1);
        }
        gameRepository.save(gameEntity);
    }

    private boolean isNotCorrect(Round roundEntity) {
        return !roundEntity.isCorrect();
    }

    private boolean ruleTotalErrors(Round roundEntity, Game gameEntity) {
        return isNotCorrect(roundEntity) &&
                gameEntity.getTotalErrors() == TOTAL_ERRORS_ALLOWED;
    }

    private ChoiceMovieEnum getCorrectOption(Round roundEntity) {
        if (roundEntity.getLeftMovie().getTotalPoints() >
                roundEntity.getRightMovie().getTotalPoints()) {
            return ChoiceMovieEnum.LEFT;
        }
        return ChoiceMovieEnum.RIGHT;
    }

    private void setMovieData(Game gameEntity, int numberRound, Round roundEntity,
                              Movie leftMovieEntity, Movie rightMovieEntity) {
        roundEntity.setLeftMovie(leftMovieEntity);
        roundEntity.setRightMovie(rightMovieEntity);
        roundEntity.setStatus(RoundStatusEnum.NOT_PLAYED);
        roundEntity.setGame(gameEntity);
        roundEntity.setNumberRound(numberRound);
    }

    private int getNumberRound(Game gameEntity) {
        List<Round> roundsEntity = roundRepository.findRoundsByGame(gameEntity);
        int numberRound = ADD_ANOTHER_ROUND;
        if (!roundsEntity.isEmpty()) {
            numberRound = addRound(roundsEntity);
        }
        return numberRound;
    }

    private int addRound(List<Round> roundsEntity) {
        return roundsEntity.size() + ADD_ANOTHER_ROUND;
    }

    private Round saveRound(Round roundEntity) {
        return roundRepository.save(roundEntity);
    }

    private Optional<Round> isRoundValid(Game gameEntity, Movie leftMovieEntity, Movie rightMovieEntity) {
        return roundRepository.validMoviesRound(gameEntity.getId(),
                leftMovieEntity.getId(), rightMovieEntity.getId());
    }
}
