package br.com.letscode.moviesbattle.crawling;

import br.com.letscode.moviesbattle.crawling.service.ScraperService;
import br.com.letscode.moviesbattle.entity.Movie;
import br.com.letscode.moviesbattle.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

@Component
public class CrawlingListener {

    @Autowired
    private ScraperService scraperService;

    @Autowired
    private MovieService movieService;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws IOException {
        List<Movie> movies = scraperService.crawlingMoviesImdb();
        movieService.createMovies(movies);
    }
}
