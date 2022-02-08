package br.com.letscode.moviesbattle.api.model.response;

import br.com.letscode.moviesbattle.domain.model.enums.GameStatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameResponse {

    private Long id;
    private Long totalGamePoint;
    private Long totalErrors;
    private GameStatusEnum status;
}
