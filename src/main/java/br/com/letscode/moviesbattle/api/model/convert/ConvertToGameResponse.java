package br.com.letscode.moviesbattle.api.model.convert;

import br.com.letscode.moviesbattle.api.model.response.GameResponse;
import br.com.letscode.moviesbattle.domain.model.Game;

public abstract class ConvertToGameResponse {

    public static GameResponse fromEntity(Game game) {
        return GameResponse.builder()
                .id(game.getId())
                .status(game.getStatus())
                .totalErrors(game.getTotalErrors())
                .totalGamePoint(game.getTotalGamePoint())
                .build();
    }
}
