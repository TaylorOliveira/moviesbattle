package br.com.letscode.moviesbattle.domain.model.enums;

import java.util.stream.Stream;

public enum ERole {

    ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN;

    public static ERole fromERole(String eRole) {
        return Stream.of(ERole.values())
                .filter(e -> e.name().equalsIgnoreCase(eRole))
                .findFirst()
                .orElse(null);
    }
}
