package br.com.letscode.moviesbattle.api.model.convert;

import br.com.letscode.moviesbattle.api.model.response.RoundGameResponse;
import br.com.letscode.moviesbattle.domain.model.Game;

public abstract class ConvertToGameResponse {

    public static RoundGameResponse.GameResponse fromEntity(Game game) {
        return RoundGameResponse.GameResponse.builder()
                .id(game.getId())
                .status(game.getStatus())
                .totalErrors(game.getTotalErrors())
                .totalGamePoint(game.getTotalGamePoint())
                .build();
    }
}
