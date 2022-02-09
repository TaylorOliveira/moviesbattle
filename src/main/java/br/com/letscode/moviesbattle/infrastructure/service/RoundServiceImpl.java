package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.api.model.payload.convert.ConvertToRoundValidateResponse;
import br.com.letscode.moviesbattle.api.model.payload.response.RoundValidateResponse;
import br.com.letscode.moviesbattle.domain.exceptionhandler.RoundPlayedException;
import br.com.letscode.moviesbattle.api.model.enums.ChoiceMovieEnum;
import br.com.letscode.moviesbattle.core.security.service.LoggedInUser;
import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.model.Movie;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.enums.RoundStatusEnum;
import br.com.letscode.moviesbattle.domain.repository.MovieRepository;
import br.com.letscode.moviesbattle.domain.repository.RoundRepository;
import br.com.letscode.moviesbattle.domain.service.GameUpdateService;
import br.com.letscode.moviesbattle.domain.service.RoundChoiceService;
import br.com.letscode.moviesbattle.domain.service.RoundService;
import br.com.letscode.moviesbattle.domain.service.UserRoundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import static br.com.letscode.moviesbattle.domain.constants.DomainConstants.*;

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
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Entity %s of type %s not found", id, Game.class.getName())));

        if (isRoundPlayed(roundEntity)) {
            throw new RoundPlayedException("The roundEntity has already been played");
        }

        roundEntity = roundChoiceService
                .validateSelectedMovieInRound(userChoice, roundEntity);

        roundEntity.setGame(gameUpdateService
                .updateGameTotalErrors(roundEntity));

        roundEntity.getGame().setUser(userRoundService
                .updateUserInformationWithRoundResult(roundEntity));

        roundRepository.save(roundEntity);

        return ConvertToRoundValidateResponse.fromResponse(roundEntity);
    }

    private boolean isRoundPlayed(Round round) {
        return round.getStatus() == RoundStatusEnum.PLAYED;
    }

    private int getNumberRound(Game gameEntity) {
        List<Round> roundsEntity = roundRepository
                .findRoundsByGame(gameEntity);
        int numberRound = ADD_ANOTHER_ROUND;
        if (!roundsEntity.isEmpty()) {
            numberRound = addRound(roundsEntity);
        }
        return numberRound;
    }

    private int addRound(List<Round> roundsEntity) {
        return roundsEntity.size() + ADD_ANOTHER_ROUND;
    }

    private Optional<Round> isRoundValid(Game gameEntity, Movie leftMovieEntity,
                                         Movie rightMovieEntity) {
        return roundRepository.validMoviesRound(gameEntity.getId(),
                leftMovieEntity.getId(), rightMovieEntity.getId());
    }
}
