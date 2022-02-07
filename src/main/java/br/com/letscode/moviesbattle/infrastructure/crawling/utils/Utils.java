package br.com.letscode.moviesbattle.infrastructure.crawling.utils;

import br.com.letscode.moviesbattle.infrastructure.crawling.config.handle.exception.ConvertToLongException;
import br.com.letscode.moviesbattle.infrastructure.crawling.config.handle.exception.RemoveParenthesisException;
import org.springframework.stereotype.Service;

@Service
public class Utils {

    public Double convertToDouble(String s) {
        return Double.parseDouble(s);
    }

    public Long convertToLong(String s) {
        try {
            return Long.valueOf(s);
        } catch (NumberFormatException exception) {
            throw new ConvertToLongException(exception.getLocalizedMessage());
        }
    }

    public String removeParenthesis(String s) {
        try {
            s = s.replace("(","");
            s = s.replace(")","");
            return s;
        } catch (Exception exception) {
            throw new RemoveParenthesisException(exception.getLocalizedMessage());
        }
    }
}
