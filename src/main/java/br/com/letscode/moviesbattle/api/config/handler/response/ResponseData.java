package br.com.letscode.moviesbattle.api.config.handler.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {

    private Integer code;
    private String title;
    private String detail;
}
