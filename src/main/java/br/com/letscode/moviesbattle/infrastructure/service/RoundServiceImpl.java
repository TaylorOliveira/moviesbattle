package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.model.Movie;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.enums.RoundStatusEnum;
import br.com.letscode.moviesbattle.domain.repository.MovieRepository;
import br.com.letscode.moviesbattle.domain.repository.RoundRepository;
import br.com.letscode.moviesbattle.domain.service.RoundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RoundServiceImpl implements RoundService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RoundRepository roundRepository;

    public Round initializeRound(Game game) {
        int numberRound = getNumberRound(game);
        Round round = new Round();

        boolean isNotValidPair = true;
        while (isNotValidPair) {
            Movie leftMovie = movieRepository.getRandomMovie();
            Movie rightMovie = movieRepository.getRandomMovie();
            if (isRoundValid(game, leftMovie, rightMovie).isEmpty()) {
                isNotValidPair = false;
                round.setMovieLeft(leftMovie);
                round.setRightMovie(rightMovie);
                round.setStatus(RoundStatusEnum.NOT_PLAYED);
                round.setGame(game);
                round.setNumberRound(numberRound);
            }
        }
        return saveRound(round);
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
