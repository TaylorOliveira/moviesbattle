package br.com.letscode.moviesbattle.api.model.convert;

import br.com.letscode.moviesbattle.api.model.response.RoundGameResponse;
import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.model.Round;

public abstract class ConvertToRoundGameResponse {

    public static RoundGameResponse fromEntity(Game game, Round round) {
        return RoundGameResponse.builder()
                .id(game.getId())
                .game(ConvertToGameResponse.fromEntity(game))
                .leftMovie(ConvertToMovieResponse.fromEntity(round.getLeftMovie()))
                .rightMovie(ConvertToMovieResponse.fromEntity(round.getRightMovie()))
                .round(round.getNumberRound())
                .build();
    }
}
