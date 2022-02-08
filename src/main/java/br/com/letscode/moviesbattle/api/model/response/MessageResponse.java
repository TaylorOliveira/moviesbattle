package br.com.letscode.moviesbattle.api.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {

    private String message;
}