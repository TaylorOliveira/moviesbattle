package br.com.letscode.moviesbattle.api.model.request.login;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
