package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.api.model.payload.convert.ConvertToRoundValidateResponse;
import br.com.letscode.moviesbattle.api.model.payload.response.RoundValidateResponse;
import br.com.letscode.moviesbattle.domain.config.exception.EntityNotFoundException;
import br.com.letscode.moviesbattle.domain.config.exception.ErrorException;
import br.com.letscode.moviesbattle.core.security.service.LoggedInUser;
import br.com.letscode.moviesbattle.domain.model.enums.RoundStatusEnum;
import br.com.letscode.moviesbattle.domain.repository.MovieRepository;
import br.com.letscode.moviesbattle.domain.repository.RoundRepository;
import br.com.letscode.moviesbattle.api.model.enums.ChoiceMovieEnum;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.letscode.moviesbattle.domain.model.Movie;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.User;
import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.service.*;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;
import java.util.List;

import static br.com.letscode.moviesbattle.domain.config.exception.enums.ExceptionEnum.ENTITY_NOT_FOUND;
import static br.com.letscode.moviesbattle.domain.config.exception.enums.ExceptionEnum.ROUND_PLAYED;

@Slf4j
@Service
public class RoundServiceImpl implements RoundService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private RoundChoiceService roundChoiceService;

    @Autowired
    private GameUpdateService gameUpdateService;

    @Autowired
    private UserRoundService userRoundService;

    @Override
    public Round initializeRound(Game gameEntity) {
        int numberRound = getNumberRound(gameEntity);
        Round roundEntity = new Round();

        boolean isNotValidPair = true;
        while (isNotValidPair) {
            Movie leftMovieEntity = movieRepository.getRandomMovie();
            Movie rightMovieEntity = movieRepository.getRandomMovie();

            if (isRoundValid(gameEntity, leftMovieEntity, rightMovieEntity).isEmpty()) {
                isNotValidPair = false;

                roundEntity.setLeftMovie(leftMovieEntity);
                roundEntity.setRightMovie(rightMovieEntity);
                roundEntity.setStatus(RoundStatusEnum.NOT_PLAYED);
                roundEntity.setGame(gameEntity);
                roundEntity.setNumberRound(numberRound);
            }
        }
        return roundRepository.save(roundEntity);
    }

    @Override
    public RoundValidateResponse processRound(LoggedInUser loggedInUser, Long id,
                                              ChoiceMovieEnum userChoice) {
        Round roundEntity = roundRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Round.class, id));

        if (isRoundPlayed(roundEntity)) {
            throw new ErrorException(ROUND_PLAYED);
        }

        roundEntity = roundChoiceService
                .validateSelectedMovieInRound(userChoice, roundEntity);

        Game gameEntity = gameUpdateService
                .updateGameTotalErrors(roundEntity);

        User userEntity =  userRoundService
                .updateUserInformationWithRoundResult(roundEntity);

        roundRepository.save(roundEntity);

        gameUpdateService.save(gameEntity);

        userRoundService.save(userEntity);

        return ConvertToRoundValidateResponse.fromResponse(roundEntity);
    }

    @Override
    public Round getRoundNotPayed(Game gameEntity) {
        return roundRepository.findRoundByGameAndStatusNotPlayed(RoundStatusEnum.NOT_PLAYED, gameEntity)
                .orElse(null);
    }

    private boolean isRoundPlayed(Round round) {
        return round.getStatus() == RoundStatusEnum.PLAYED;
    }

    private int getNumberRound(Game gameEntity) {
        List<Round> roundsEntity = roundRepository.findRoundsByGame(gameEntity);

        int numberRound = 1;
        if (!roundsEntity.isEmpty()) {
            numberRound = addRound(roundsEntity);
        }
        return numberRound;
    }

    private int addRound(List<Round> roundsEntity) {
        int numberRound = roundsEntity.size();
        return numberRound++;
    }

    private Optional<Round> isRoundValid(Game gameEntity, Movie leftMovieEntity,
                                         Movie rightMovieEntity) {
        return roundRepository.validMoviesRound(gameEntity.getId(),
                leftMovieEntity.getId(), rightMovieEntity.getId());
    }

    private String getDetail(Class clazz, Long id) {
        return String.format(ENTITY_NOT_FOUND.getDescription(), "id", id, clazz.getName());
    }
}
