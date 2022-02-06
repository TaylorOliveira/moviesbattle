package br.com.letscode.moviesbattle.controller.payload.request.game;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    private String name;
}
