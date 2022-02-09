package br.com.letscode.moviesbattle.factory;

import br.com.letscode.moviesbattle.domain.model.enums.ERole;
import br.com.letscode.moviesbattle.domain.model.Role;

public abstract class RoleFactory {

    public static Role fromType() {
        return Role.builder()
                .id(1L)
                .role(ERole.ROLE_USER)
                .build();
    }
}
