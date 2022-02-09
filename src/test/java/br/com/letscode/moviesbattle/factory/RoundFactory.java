package br.com.letscode.moviesbattle.factory;

import br.com.letscode.moviesbattle.domain.model.Round;

public abstract class RoundFactory {

    public static Round fromType() {
        return Round.builder().build();
    }
}
