package br.com.letscode.moviesbattle.domain.config.exception;

import br.com.letscode.moviesbattle.domain.config.exception.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final String title;
    private final String detail;

    public GeneralException(ExceptionEnum exceptionEnum) {
        this.title = exceptionEnum.name();
        this.detail = exceptionEnum.getDescription();
    }
}
