package br.com.letscode.moviesbattle.factory;

import br.com.letscode.moviesbattle.domain.model.Movie;

public abstract class MovieFactory {

    public static Movie fromTypeMovie1() {
        return Movie.builder()
                .id(1L)
                .debutYear("2022")
                .imdb(10.0)
                .totalPoints(1000)
                .totalVotes(100)
                .build();
    }

    public static Movie fromTypeMovie2() {
        return Movie.builder()
                .id(2L)
                .debutYear("2021")
                .imdb(5.0)
                .totalPoints(500)
                .totalVotes(50)
                .build();
    }

    public static Movie fromTypeMovie3() {
        return Movie.builder()
                .id(3L)
                .debutYear("2020")
                .imdb(2.5)
                .totalPoints(250)
                .totalVotes(25)
                .build();
    }
}
