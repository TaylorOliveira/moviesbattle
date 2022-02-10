package br.com.letscode.moviesbattle.api.model.payload.convert;

import br.com.letscode.moviesbattle.api.model.payload.response.RoundGameResponse.GameResponse;
import br.com.letscode.moviesbattle.domain.model.Game;

public abstract class ConvertToGameResponse {

    public static GameResponse fromResponse(Game game) {
        return GameResponse.builder()
                .id(game.getId())
                .status(game.getStatus())
                .totalErrors(game.getTotalErrors())
                .build();
    }
}
