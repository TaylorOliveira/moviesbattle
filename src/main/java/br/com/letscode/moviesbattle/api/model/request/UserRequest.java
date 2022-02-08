package br.com.letscode.moviesbattle.api.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    private String name;
}
