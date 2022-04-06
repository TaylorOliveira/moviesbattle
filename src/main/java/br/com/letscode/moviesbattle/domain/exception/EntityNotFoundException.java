package br.com.letscode.moviesbattle.domain.exception;

import static br.com.letscode.moviesbattle.domain.exception.enums.ExceptionEnum.ENTITY_NOT_FOUND;

public class EntityNotFoundException extends RuntimeException {

    private final String title;
    private final String detail;

    public EntityNotFoundException(Class clazz, Long id) {
        this.title = ENTITY_NOT_FOUND.name();
        this.detail = String.format(ENTITY_NOT_FOUND.getDescription(), "id", id, clazz.getName());
    }
}
