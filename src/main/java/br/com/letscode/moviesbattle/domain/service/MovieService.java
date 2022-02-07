package br.com.letscode.moviesbattle.domain.service;

import br.com.letscode.moviesbattle.domain.model.Movie;
import java.util.List;

public interface MovieService {

    void createMovies(List<Movie> movies);

    Movie getMoviesRandom();
}
