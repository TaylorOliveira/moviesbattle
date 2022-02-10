package br.com.letscode.moviesbattle.api.model.payload.response;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RankingResponse {

    private List<UserResponse> ranking;

    @Data
    @Builder
    public static class UserResponse {

        private Long id;
        private String username;
        private int ranking;
        private double score;
    }
}
