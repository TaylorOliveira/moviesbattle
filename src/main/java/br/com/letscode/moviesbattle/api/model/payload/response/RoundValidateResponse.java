package br.com.letscode.moviesbattle.api.model.payload.response;

import br.com.letscode.moviesbattle.domain.model.enums.RoundStatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoundValidateResponse {

    private Long roundId;
    private boolean isCorrectRound;
    private RoundStatusEnum status;
    private int numberRound;
    private MovieRoundResponse leftMovie;
    private MovieRoundResponse rightMovie;

    @Data
    @Builder
    public static class MovieRoundResponse {

        private Long id;
        private String name;
        private String debutYear;
        private boolean isCorrect;
    }
}
