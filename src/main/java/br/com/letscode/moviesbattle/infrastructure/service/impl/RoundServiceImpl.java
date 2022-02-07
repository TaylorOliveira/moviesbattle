package br.com.letscode.moviesbattle.infrastructure.service.impl;

import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.model.Movie;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.enums.StatusRound;
import br.com.letscode.moviesbattle.domain.repository.MovieRepository;
import br.com.letscode.moviesbattle.domain.repository.RoundRepository;
import br.com.letscode.moviesbattle.domain.service.RoundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class RoundServiceImpl implements RoundService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RoundRepository roundRepository;

    public Round initializeRound(Game game) {
        Round round = Round.builder().build();
        round.setGame(game);

        boolean isValid = true;

        while (isValid) {
            Movie movieX = movieRepository.getRandomMovie();
            Movie movieY = movieRepository.getRandomMovie();
            if (isRoundValid(game, movieX, movieY).isEmpty()) {
                isValid = false;
                round.setMovieX(movieX);
                round.setMovieY(movieY);
                round.setStatus(StatusRound.NOT_PLAYED);
            }
        }
        return round;
    }

    private Optional<Round> isRoundValid(Game game, Movie movieX, Movie movieY) {
        return roundRepository.validRound(game.getId(),
                movieX.getId(), movieY.getId());
    }
}
