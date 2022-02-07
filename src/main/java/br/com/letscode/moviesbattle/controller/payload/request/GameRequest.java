package br.com.letscode.moviesbattle.controller.payload.request;

import lombok.Builder;
import java.util.List;
import lombok.Data;

@Data
@Builder
public class GameRequest {

    private UserRequest userRequest;
    private List<RoundRequest> roundsRequest;

    @Data
    @Builder
    private static class RoundRequest {

        private List<MovieRequest> moviesRequest;
    }

    @Data
    @Builder
    private static class MovieRequest {

        private String name;
        private Long imdb;
    }
}
