package br.com.letscode.moviesbattle.domain.config.exception.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    GAME_PROCESS("Game processing failed."),
    ENTITY_NOT_FOUND("Entity with value %s: %d of type %s not found."),
    ROUND_PLAYED("The round has already been played."),
    GAME_TOTAL_ERRORS("The maximum amount of errors has been reached.");

    private final String description;

    ExceptionEnum(String description) {
        this.description = description;
    }
}
