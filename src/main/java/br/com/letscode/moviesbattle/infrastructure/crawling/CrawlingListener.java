package br.com.letscode.moviesbattle.infrastructure.crawling;

import br.com.letscode.moviesbattle.infrastructure.crawling.domain.service.ScraperService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import br.com.letscode.moviesbattle.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CrawlingListener {

    @Autowired
    private ScraperService scraperService;

    @Autowired
    private MovieService movieService;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        movieService.createMovies(scraperService
                .crawlingMoviesImdb());
    }
}
