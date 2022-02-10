package br.com.letscode.moviesbattle.api.model.payload.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    private String name;
}
