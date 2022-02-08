package br.com.letscode.moviesbattle.infrastructure.crawling.domain.service;

import br.com.letscode.moviesbattle.domain.model.Movie;
import java.io.IOException;
import java.util.List;

public interface ScraperService {

    List<Movie> crawlingMoviesImdb() throws IOException;
}
