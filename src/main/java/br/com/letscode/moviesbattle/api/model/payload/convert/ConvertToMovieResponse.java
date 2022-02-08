package br.com.letscode.moviesbattle.api.model.payload.convert;

import br.com.letscode.moviesbattle.api.model.payload.response.RoundGameResponse.MovieResponse;
import br.com.letscode.moviesbattle.domain.model.Movie;

public abstract class ConvertToMovieResponse {

    public static MovieResponse fromEntity(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .name(movie.getName())
                .debutYear(movie.getDebutYear())
                .build();
    }
}
