package br.com.letscode.moviesbattle.controller.payload.response;

import lombok.Builder;
import java.util.List;
import lombok.Data;

@Data
@Builder
public class JwtResponse {

    private String token;

    private Long id;

    private String username;

    private String email;

    private List<String> roles;
}
