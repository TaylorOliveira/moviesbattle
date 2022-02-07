package br.com.letscode.moviesbattle.crawling.convert;

import br.com.letscode.moviesbattle.crawling.playload.MovieScraperRequest;
import br.com.letscode.moviesbattle.entity.Movie;
import java.util.stream.Collectors;
import java.util.List;

public abstract class ConvertToMovie {

    public static Movie fromEntity(MovieScraperRequest movie) {
        return Movie.builder()
                .name(movie.getName())
                .year(movie.getYear())
                .imdb(movie.getImdb())
                .totalVotes(movie.getTotalVotes())
                .points(getPoints(movie))
                .build();
    }

    private static double getPoints(MovieScraperRequest movieScraperRequest) {
        return movieScraperRequest.getImdb() * movieScraperRequest.getTotalVotes();
    }

    public static List<Movie> buildMovies(List<MovieScraperRequest> movies) {
        return movies.stream().map(ConvertToMovie::fromEntity)
                .collect(Collectors.toList());
    }
}
