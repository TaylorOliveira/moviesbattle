package br.com.letscode.moviesbattle.infrastructure.crawling.service.impl;

import br.com.letscode.moviesbattle.infrastructure.crawling.convert.ConvertToMovie;
import br.com.letscode.moviesbattle.infrastructure.crawling.modal.MovieScraperRequest;
import br.com.letscode.moviesbattle.infrastructure.crawling.service.ScraperService;
import br.com.letscode.moviesbattle.infrastructure.crawling.utils.Utils;
import br.com.letscode.moviesbattle.domain.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static br.com.letscode.moviesbattle.infrastructure.crawling.config.constants.ScraperConstants.*;

@Slf4j
@Service
public class ScraperServiceImpl implements ScraperService {

    @Autowired
    private Utils utils;

    @Value("${moviesbattle.app.urlScraping}")
    private String URL;

    @Override
    public List<Movie> crawlingMoviesImdb() throws IOException {

        List<MovieScraperRequest> scrapings = new ArrayList<>();

        Document document = Jsoup.connect(URL).get();
        Elements elementsListerItem = document.getElementsByClass(CLASS_LISTER_ITEM);

        for(Element elementLI : elementsListerItem) {
            MovieScraperRequest movie = MovieScraperRequest.builder().build();

            Elements elementsListerItemHeader = elementLI.getElementsByClass(CLASS_LISTER_ITEM_HEADER);
            crawlingNameAndYear(movie, elementsListerItemHeader);

            Elements elementsImdbRating = elementLI.getElementsByClass(CLASS_RATINGS_IMDB_RATING);
            crawlingImdbRanting(movie, elementsImdbRating);

            Elements elementsTextMuted = elementLI.getElementsByClass(CLASS_TEXT_MUTED);
            crawlingTotalVotes(movie, elementsTextMuted);

            scrapings.add(movie);
        }

        return ConvertToMovie.buildMovies(scrapings);
    }

    private void crawlingTotalVotes(MovieScraperRequest movie, Elements elementsTextMuted) {
        movie.setTotalVotes(getTotalVotes(elementsTextMuted));
    }

    private String getTotalVotes(Elements elementsTextMuted) {
        try {
            return elementsTextMuted.get(3).getAllElements().get(2).text();
        } catch (IndexOutOfBoundsException exception) {
            return "VOTES_NOT_COMPUTED";
        }
    }

    private void crawlingImdbRanting(MovieScraperRequest movie, Elements elementsImdbRating) {
        for(Element elementIR : elementsImdbRating) {
            movie.setImdb(getImdb(elementIR));
        }
    }

    private Double getImdb(Element elementIR) {
        return utils.convertToDouble(elementIR.getElementsByTag(TAG_STRONG).text());
    }

    private void crawlingNameAndYear(MovieScraperRequest movieScraperRequest,
                                     Elements elementsListerItemHeader) {
        for (Element elementLIH : elementsListerItemHeader) {
            movieScraperRequest.setName(elementLIH.getElementsByTag(TAG_A).text());
            movieScraperRequest.setYear(getYear(elementLIH));
        }
    }

    private String getYear(Element elementLIH) {
        return elementLIH.getElementsByClass(CLASS_LISTER_ITEM_YEAR).text();
    }
}
