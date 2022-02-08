package br.com.letscode.moviesbattle.domain.exceptionhandler;

public class TotalErrorsException extends RuntimeException {

    private final String message;

    public TotalErrorsException(String message) {
        super(message);
        this.message = message;
    }
}
