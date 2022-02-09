package br.com.letscode.moviesbattle.factory;

import br.com.letscode.moviesbattle.domain.model.User;

public abstract class UserFactory {

    public static User fromType() {

        return User.builder()
                .id(1L)
                .username("user_test")
                .email("user_test@email.com")
                .password("password_test")
                .totalRoundsPlayed(0)
                .totalCorrectRounds(0)
                .score(0)
                .build();
    }
}
