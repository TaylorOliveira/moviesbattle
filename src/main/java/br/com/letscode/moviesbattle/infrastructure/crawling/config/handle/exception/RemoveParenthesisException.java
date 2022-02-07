package br.com.letscode.moviesbattle.infrastructure.crawling.config.handle.exception;

public class RemoveParenthesisException extends RuntimeException {

    private final String message;

    public RemoveParenthesisException(String message) {
        super(message);
        this.message = message;
    }
}
