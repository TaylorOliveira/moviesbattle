package br.com.letscode.moviesbattle.api.config.handler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.letscode.moviesbattle.domain.config.exception.GeneralException;
import br.com.letscode.moviesbattle.api.config.handler.response.ResponseData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ApiControllerHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    ResponseEntity<?> handleRoundPlayedException(HttpServletRequest request, Exception ex) {
        GeneralException exception = (GeneralException) ex;
        ResponseData responseData = ResponseData.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .title(exception.getTitle())
                .detail(exception.getDetail())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<?> handleException(HttpServletRequest request, Exception ex) {
        ResponseData responseData = ResponseData.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .title(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .detail(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
    }
}
