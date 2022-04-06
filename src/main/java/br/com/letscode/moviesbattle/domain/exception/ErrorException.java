package br.com.letscode.moviesbattle.domain.exception;

import br.com.letscode.moviesbattle.domain.exception.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class ErrorException extends RuntimeException {

    private final String title;
    private final String detail;

    public ErrorException(ExceptionEnum exceptionEnum) {
        this.title = exceptionEnum.name();
        this.detail = exceptionEnum.getDescription();
    }
}
