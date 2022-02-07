package br.com.letscode.moviesbattle.api.payload.request;

import lombok.Builder;
import java.util.List;
import lombok.Data;

@Data
@Builder
public class RankingRequest {

    private List<UserRankingRequest> ranking;

    @Data
    @Builder
    private static class UserRankingRequest {

        private Long position;
        private UserRequest userRequest;
    }
}
