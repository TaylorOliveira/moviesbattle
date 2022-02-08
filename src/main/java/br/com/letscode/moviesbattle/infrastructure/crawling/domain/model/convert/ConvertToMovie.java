package br.com.letscode.moviesbattle.infrastructure.crawling.domain.model.convert;

import br.com.letscode.moviesbattle.infrastructure.crawling.domain.model.MovieScraperRequest;
import br.com.letscode.moviesbattle.domain.model.Movie;
import java.util.stream.Collectors;
import java.util.List;

public abstract class ConvertToMovie {

    public static Movie fromEntity(MovieScraperRequest movie) {
        return Movie.builder()
                .name(movie.getName())
                .debutYear(movie.getYear())
                .imdb(movie.getImdb())
                .totalVotes(movie.getTotalVotes())
//                .points(getPoints(movie))
                .build();
    }

//    private static double getPoints(MovieScraperRequest movieScraperRequest) {
//        return movieScraperRequest.getImdb() * Float.parseFloat(movieScraperRequest.getTotalVotes());
//    }

    public static List<Movie> buildMovies(List<MovieScraperRequest> movies) {
        return movies.stream().map(ConvertToMovie::fromEntity)
                .collect(Collectors.toList());
    }
}
