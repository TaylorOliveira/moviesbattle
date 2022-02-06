package br.com.letscode.moviesbattle.crawling.convert;

import br.com.letscode.moviesbattle.crawling.playload.MovieScraperRequest;
import br.com.letscode.moviesbattle.entity.Movie;
import java.util.stream.Collectors;
import java.util.List;

public abstract class ConvertToMovie {

    public static Movie fromEntity(MovieScraperRequest movieScraperRequest) {
        return Movie.builder()
                .name(movieScraperRequest.getName())
                .year(movieScraperRequest.getYear())
                .imdb(movieScraperRequest.getImdb())
                .build();
    }

    public static List<Movie> buildMovies(List<MovieScraperRequest>
                                                moviesScraperRequest) {
        return moviesScraperRequest.stream().map(ConvertToMovie::fromEntity)
                .collect(Collectors.toList());
    }
}
