package br.com.letscode.moviesbattle.factory;

import br.com.letscode.moviesbattle.domain.model.Movie;

public abstract class MovieFactory {

    public static Movie fromType() {
        return Movie.builder().build();
    }
}
