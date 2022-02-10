package br.com.letscode.moviesbattle.infrastructure.crawling.infrastructure.utils;

import br.com.letscode.moviesbattle.infrastructure.crawling.domain.config.exception.GeneralException;
import org.springframework.stereotype.Service;

import static br.com.letscode.moviesbattle.infrastructure.crawling.domain.config.exception.enums.CrawlingExceptionEnum.*;

@Service
public class Utils {

    public Double convertToDouble(String s) {
        return Double.parseDouble(s);
    }

    public Long convertToLong(String s) {
        try {
            return Long.valueOf(s);
        } catch (NumberFormatException exception) {
            throw new GeneralException(CONVERT_TO_DOUBLE_TITLE);
        }
    }

    public String removeParenthesis(String s) {
        try {
            s = s.replace("(","");
            s = s.replace(")","");
            return s;
        } catch (Exception exception) {
            throw new GeneralException(REMOVE_PARENTHESIS_TITLE);
        }
    }

    public boolean validateString(String s) {
        return s != null && !s.equals("");
    }
}
