package br.com.letscode.moviesbattle.infrastructure.crawling.domain.exceptionhandler.exception;

public class ConvertToLongException extends RuntimeException {

    private final String message;

    public ConvertToLongException(String message) {
        super(message);
        this.message = message;
    }
}
