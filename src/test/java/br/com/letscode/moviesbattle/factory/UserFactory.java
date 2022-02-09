package br.com.letscode.moviesbattle.factory;

import br.com.letscode.moviesbattle.domain.model.User;

public abstract class UserFactory {

    public static User fromType() {
        return User.builder().build();
    }
}
