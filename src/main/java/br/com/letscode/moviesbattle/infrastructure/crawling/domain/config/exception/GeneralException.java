package br.com.letscode.moviesbattle.infrastructure.crawling.domain.config.exception;

import br.com.letscode.moviesbattle.infrastructure.crawling.domain.config.exception.enums.CrawlingExceptionEnum;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final String title;
    private final String detail;

    public GeneralException(CrawlingExceptionEnum exceptionEnum) {
        this.title = exceptionEnum.name();
        this.detail = exceptionEnum.getDescription();
    }
}
