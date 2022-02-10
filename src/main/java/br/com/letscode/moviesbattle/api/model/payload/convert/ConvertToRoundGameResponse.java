package br.com.letscode.moviesbattle.api.model.payload.convert;

import br.com.letscode.moviesbattle.api.model.payload.response.RoundGameResponse;
import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.model.Round;

public abstract class ConvertToRoundGameResponse {

    public static RoundGameResponse fromResponse(Game game, Round round) {
        return RoundGameResponse.builder()
                .roundId(round.getId())
                .game(ConvertToGameResponse.fromResponse(game))
                .leftMovie(ConvertToMovieResponse.fromResponse(round.getLeftMovie()))
                .rightMovie(ConvertToMovieResponse.fromResponse(round.getRightMovie()))
                .round(round.getNumberRound())
                .build();
    }
}
