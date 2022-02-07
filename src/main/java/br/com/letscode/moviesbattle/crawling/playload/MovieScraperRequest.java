package br.com.letscode.moviesbattle.crawling.playload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieScraperRequest {

    private String name;

    private Double imdb;

    private String year;

    private Double totalVotes;
}
