package br.com.letscode.moviesbattle.domain.exceptionhandler;

public class RoundPlayedException extends RuntimeException {

    private final String message;

    public RoundPlayedException(String message) {
        super(message);
        this.message = message;
    }
}
