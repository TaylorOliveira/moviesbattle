package br.com.letscode.moviesbattle.infrastructure.crawling.domain.config.exception.enums;

import lombok.Getter;

@Getter
public enum CrawlingExceptionEnum {

    CONVERT_TO_LONG_TITLE("CONVERT_TO_LONG"),
    CONVERT_TO_LONG_DETAIL("Failed to convert scrape data to Long."),

    CONVERT_TO_DOUBLE_TITLE("CONVERT_TO_DOUBLE"),
    CONVERT_TO_DOUBLE_DETAIL("Failed to convert scrape data to Double."),

    REMOVE_PARENTHESIS_TITLE("REMOVE_PARENTHESIS"),
    REMOVE_PARENTHESIS_DETAIL("Failed to remove parentheses from scrape data.");

    private final String description;

    CrawlingExceptionEnum(String description) {
        this.description = description;
    }
}
