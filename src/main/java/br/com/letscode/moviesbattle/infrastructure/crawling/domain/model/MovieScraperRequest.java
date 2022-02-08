package br.com.letscode.moviesbattle.infrastructure.crawling.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieScraperRequest {

    private String name;

    private Double imdb;

    private String debutYear;

    private Double totalVotes;
}
