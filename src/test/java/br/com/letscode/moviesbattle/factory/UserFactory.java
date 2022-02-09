package br.com.letscode.moviesbattle.factory;

import br.com.letscode.moviesbattle.domain.model.Role;
import br.com.letscode.moviesbattle.domain.model.User;
import java.util.HashSet;

public abstract class UserFactory {

    public static User fromType() {
        HashSet<Role> roles = new HashSet<>();
        roles.add(RoleFactory.fromType());

        return User.builder()
                .id(1L)
                .username("user_test")
                .email("user_test@email.com")
                .roles(roles)
                .password("password_test")
                .totalRoundsPlayed(0)
                .totalCorrectRounds(0)
                .score(0)
                .build();
    }
}
