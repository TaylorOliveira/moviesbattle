package br.com.letscode.moviesbattle.factory;

import br.com.letscode.moviesbattle.domain.model.enums.GameStatusEnum;
import br.com.letscode.moviesbattle.domain.model.Game;

public abstract class GameFactory {

    public static Game fromType() {
        return Game.builder()
                .id(1L)
                .user(UserFactory.fromType())
                .totalErrors(0)
                .status(GameStatusEnum.RUNNING)
                .rounds(RoundFactory.fromList())
                .build();
    }
}
