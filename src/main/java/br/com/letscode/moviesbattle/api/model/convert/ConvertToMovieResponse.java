package br.com.letscode.moviesbattle.api.model.convert;

import br.com.letscode.moviesbattle.api.model.response.MovieResponse;
import br.com.letscode.moviesbattle.domain.model.Movie;

public abstract class ConvertToMovieResponse {

    public static MovieResponse fromEntity(Movie game) {
        return MovieResponse.builder()
                .id(game.getId())
                .name(game.getName())
                .build();
    }
}
