package br.com.letscode.moviesbattle.infrastructure.crawling.domain.config.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CrawlingTemplateLogConstants {

    public static final String CRAWLING_FAIL = "CRAWLING_FAIL: CODE = {}, TITLE = {}, DETAIL = {}";
}
