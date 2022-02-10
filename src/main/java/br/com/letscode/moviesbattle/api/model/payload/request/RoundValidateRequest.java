package br.com.letscode.moviesbattle.api.model.payload.request;

import br.com.letscode.moviesbattle.api.model.payload.response.RoundGameResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoundValidateRequest {

    private RoundGameResponse roundGame;
}
