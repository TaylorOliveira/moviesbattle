package br.com.letscode.moviesbattle.infrastructure.service;

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
import br.com.letscode.moviesbattle.domain.service.GameService;
import br.com.letscode.moviesbattle.domain.service.RoundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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

    public Round initializeRound(Game game) {
        int numberRound = getNumberRound(game);
        Round round = new Round();

        boolean isNotValidPair = true;
        while (isNotValidPair) {
            Movie leftMovie = movieRepository.getRandomMovie();
            Movie rightMovie = movieRepository.getRandomMovie();
            if (isRoundValid(game, leftMovie, rightMovie).isEmpty()) {
                isNotValidPair = false;
                setMovie(game, numberRound, round, leftMovie, rightMovie);
            }
        }
        return saveRound(round);
    }

    @Override
    public void processRound(LoggedInUser loggedInUser, Long roundId, ChoiceMovieEnum choice) {
        Round round = roundRepository.findById(roundId)
                .orElseThrow(EntityNotFoundException::new);

        if (isRoundPlayed(round)) {
            throw new RoundPlayedException("ROUND_PLAYED");
        }

        checkRound(choice, round);

        updateGameTotalErrors(round);

        updateInfoUser(round);

        saveRound(round);
    }

    private boolean isRoundPlayed(Round round) {
        return round.getStatus() == RoundStatusEnum.PLAYED;
    }

    private void checkRound(ChoiceMovieEnum choice, Round round) {
        ChoiceMovieEnum choiceCorrect = getCorrectOption(round);
        round.setCorrect(choiceCorrect == choice);
        round.setChoice(choiceCorrect);
        round.setStatus(RoundStatusEnum.PLAYED);
    }

    private void updateInfoUser(Round round) {
        User user = round.getGame().getUser();

        int totalCorrectRounds = user.getTotalCorrectRounds();
        int totalRoundsPlayed = user.getTotalRoundsPlayed();
        if (round.isCorrect()) {
            user.setTotalCorrectRounds(totalCorrectRounds + 1);
        }
        user.setTotalRoundsPlayed(totalRoundsPlayed + 1);
        userRepository.save(user);
    }

    private void updateGameTotalErrors(Round round) {
        Game game = round.getGame();
        if (rolesTotalErrors(round, game)) {
            throw new TotalErrorsException("TOTAL_ERRORS");
        } else if (isNotCorrect(round)) {
            long numberFailures = getNumberFailures(game);
            game.setTotalErrors(numberFailures + 1);
        }
        gameRepository.save(game);
    }

    private boolean isNotCorrect(Round round) {
        return !round.isCorrect();
    }

    private long getNumberFailures(Game game) {
        return game.getTotalErrors() == null ? 0 : game.getTotalErrors();
    }

    private boolean rolesTotalErrors(Round round, Game game) {
        return isNotCorrect(round) && game.getTotalErrors() == TOTAL_ERRORS_ALLOWED;
    }

    private ChoiceMovieEnum getCorrectOption(Round round) {
        if (round.getLeftMovie().getTotalPoints() >
                round.getRightMovie().getTotalPoints()) {
            return ChoiceMovieEnum.LEFT;
        }
        return ChoiceMovieEnum.RIGHT;
    }

    private void setMovie(Game game, int numberRound,
                          Round round, Movie leftMovie, Movie rightMovie) {
        round.setLeftMovie(leftMovie);
        round.setRightMovie(rightMovie);
        round.setStatus(RoundStatusEnum.NOT_PLAYED);
        round.setGame(game);
        round.setNumberRound(numberRound);
    }

    private int getNumberRound(Game game) {
        List<Round> rounds = roundRepository.findRoundsByGame(game);
        int numberRound = 1;
        if (!rounds.isEmpty()) {
            numberRound = rounds.size() + 1;
        }
        return numberRound;
    }

    private Round saveRound(Round round) {
        return roundRepository.save(round);
    }

    private Optional<Round> isRoundValid(Game game, Movie leftMovie, Movie rightMovie) {
        return roundRepository.validMoviesRound(game.getId(),
                leftMovie.getId(), rightMovie.getId());
    }
}
