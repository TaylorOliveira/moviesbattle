package br.com.letscode.moviesbattle.api.payload.response;

import lombok.Builder;
import java.util.List;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String token;

    private Long id;

    private String username;

    private String email;

    private List<String> roles;
}
