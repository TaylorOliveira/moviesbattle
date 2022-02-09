package br.com.letscode.moviesbattle.infrastructure.crawling.infrastructure.service;

import br.com.letscode.moviesbattle.infrastructure.crawling.domain.config.exception.GeneralException;
import br.com.letscode.moviesbattle.infrastructure.crawling.domain.model.convert.ConvertToMovie;
import br.com.letscode.moviesbattle.infrastructure.crawling.domain.model.MovieScraperRequest;
import br.com.letscode.moviesbattle.infrastructure.crawling.domain.service.ScraperService;
import br.com.letscode.moviesbattle.infrastructure.crawling.infrastructure.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import br.com.letscode.moviesbattle.domain.model.Movie;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import java.text.ParseException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import org.jsoup.Jsoup;
import java.util.List;

import static br.com.letscode.moviesbattle.infrastructure.crawling.domain.config.exception.enums.CrawlingExceptionEnum.CONVERT_TO_LONG_DETAIL;
import static br.com.letscode.moviesbattle.infrastructure.crawling.domain.config.exception.enums.CrawlingExceptionEnum.CONVERT_TO_LONG_TITLE;
import static br.com.letscode.moviesbattle.infrastructure.crawling.domain.config.constants.CrawlingConstants.*;

@Slf4j
@Service
public class ScraperServiceImpl implements ScraperService {

    @Autowired
    private Utils utils;

    @Value("${moviesbattle.app.urlScraping}")
    private String URL;

    @Override
    public List<Movie> crawlingMoviesImdb() {
        try {
            List<MovieScraperRequest> scrapings = new ArrayList<>();

            Document document = Jsoup.connect(URL).get();
            Elements elementsListerItem = document.getElementsByClass(CLASS_LISTER_ITEM);

            for(Element elementLI : elementsListerItem) {
                MovieScraperRequest movie = MovieScraperRequest.builder().build();

                Elements elementsListerItemHeader =
                        elementLI.getElementsByClass(CLASS_LISTER_ITEM_HEADER);
                crawlingNameAndYear(movie, elementsListerItemHeader);

                Elements elementsImdbRating =
                        elementLI.getElementsByClass(CLASS_RATINGS_IMDB_RATING);
                crawlingImdbRanting(movie, elementsImdbRating);

                Elements elementsTextMuted = elementLI.getElementsByClass(CLASS_TEXT_MUTED);
                crawlingTotalVotes(movie, elementsTextMuted);

                if (roleToAdd(movie)) {
                    scrapings.add(movie);
                }
            }
            return ConvertToMovie.buildMovies(scrapings);
        } catch (Exception exception) {
            throw new GeneralException(CONVERT_TO_LONG_TITLE);
        }
    }

    private boolean roleToAdd(MovieScraperRequest movie) {
        return movie.getTotalVotes() != null &&
               movie.getName() != null &&
               movie.getImdb() != null &&
               movie.getDebutYear() != null;
    }

    private void crawlingTotalVotes(MovieScraperRequest movie, Elements elementsTextMuted) {
        movie.setTotalVotes(getTotalVotes(elementsTextMuted));
    }

    private Double getTotalVotes(Elements elementsTextMuted) {
        try {
            String totalVotes = elementsTextMuted.get(3).getAllElements().get(2).text();
            NumberFormat format = NumberFormat.getInstance(Locale.UK);
            return format.parse(totalVotes).doubleValue();
        } catch (IndexOutOfBoundsException | ParseException exception) {
            return null;
        }
    }

    private void crawlingImdbRanting(MovieScraperRequest movie, Elements elementsImdbRating) {
        for(Element elementIR : elementsImdbRating) {
            movie.setImdb(getImdb(elementIR));
        }
    }

    private Double getImdb(Element elementIR) {
        try {
            return utils.convertToDouble(elementIR
                    .getElementsByTag(TAG_STRONG).text());
        } catch (Exception exception) {
            return null;
        }
    }

    private void crawlingNameAndYear(MovieScraperRequest movieScraperRequest,
                                     Elements elementsListerItemHeader) {
        for (Element elementLIH : elementsListerItemHeader) {
            movieScraperRequest.setName(getRoleName(elementLIH));
            movieScraperRequest.setDebutYear(getRoleDebutYear(elementLIH));
        }
    }

    private String getRoleName(Element elementLIH) {
        try {
            String name = elementLIH.getElementsByTag(TAG_A).text();
            return getString(name);
        } catch (Exception exception) {
            return null;
        }
    }

    private String getRoleDebutYear(Element elementLIH) {
        try {
            String debutYear = elementLIH
                    .getElementsByClass(CLASS_LISTER_ITEM_YEAR).text();
            return getString(debutYear);
        } catch (Exception exception) {
            return null;
        }
    }

    private String getString(String s) {
        return utils.validateString(s) ? s : null;
    }
}
