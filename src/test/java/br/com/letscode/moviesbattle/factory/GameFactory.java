package br.com.letscode.moviesbattle.factory;

import br.com.letscode.moviesbattle.domain.model.Game;

public abstract class GameFactory {

    public static Game fromType() {
        return Game.builder().build();
    }
}
