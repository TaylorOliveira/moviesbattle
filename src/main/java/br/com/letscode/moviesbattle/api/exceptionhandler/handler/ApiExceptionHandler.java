package br.com.letscode.moviesbattle.api.exceptionhandler.handler;

import br.com.letscode.moviesbattle.api.exceptionhandler.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(UserNotFoundException exception) {
        return ResponseEntity.status(status).headers(headers).build();
    }
}
