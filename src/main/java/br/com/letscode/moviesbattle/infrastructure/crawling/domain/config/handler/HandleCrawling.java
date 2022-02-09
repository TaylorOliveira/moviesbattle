package br.com.letscode.moviesbattle.infrastructure.crawling.domain.config.handler;

import static br.com.letscode.moviesbattle.infrastructure.crawling.domain.config.constants.CrawlingTemplateLogConstants.CRAWLING_FAIL;
import br.com.letscode.moviesbattle.infrastructure.crawling.domain.config.exception.GeneralException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class HandleCrawling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    void handleConvertToLongException(HttpServletRequest request, Exception ex) {
        GeneralException exception = (GeneralException) ex;
        log.info(CRAWLING_FAIL, HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getTitle(), exception.getDetail());
    }
}
