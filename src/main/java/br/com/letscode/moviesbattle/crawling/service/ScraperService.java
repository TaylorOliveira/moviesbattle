package br.com.letscode.moviesbattle.crawling.service;

import br.com.letscode.moviesbattle.entity.Movie;
import java.io.IOException;
import java.util.List;

public interface ScraperService {

    List<Movie> crawlingMoviesImdb() throws IOException;
}
