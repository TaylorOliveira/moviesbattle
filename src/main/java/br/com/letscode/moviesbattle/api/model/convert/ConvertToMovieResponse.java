package br.com.letscode.moviesbattle.api.model.convert;

import br.com.letscode.moviesbattle.api.model.response.RoundGameResponse;
import br.com.letscode.moviesbattle.domain.model.Movie;

public abstract class ConvertToMovieResponse {

    public static RoundGameResponse.MovieResponse fromEntity(Movie game) {
        return RoundGameResponse.MovieResponse.builder()
                .id(game.getId())
                .name(game.getName())
                .build();
    }
}
