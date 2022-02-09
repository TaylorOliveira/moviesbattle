package br.com.letscode.moviesbattle.api.model.payload.convert;

import br.com.letscode.moviesbattle.api.model.payload.response.RoundValidateResponse.MovieRoundResponse;
import br.com.letscode.moviesbattle.domain.model.Movie;

public abstract class ConvertToMovieRoundResponse {

    public static MovieRoundResponse fromResponse(Movie movie, boolean isCorrect) {
        return MovieRoundResponse.builder()
                .id(movie.getId())
                .name(movie.getName())
                .debutYear(movie.getDebutYear())
                .isCorrect(isCorrect)
                .build();
    }
}
