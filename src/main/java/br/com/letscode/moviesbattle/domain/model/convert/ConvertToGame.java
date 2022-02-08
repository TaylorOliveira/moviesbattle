package br.com.letscode.moviesbattle.domain.model.convert;

import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.model.User;
import br.com.letscode.moviesbattle.domain.model.enums.GameStatusEnum;

public abstract class ConvertToGame {

    public static Game fromEntity(User user) {
        return Game.builder()
                .user(user)
                .status(GameStatusEnum.RUNNING)
                .build();
    }
}
