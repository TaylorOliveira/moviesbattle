package br.com.letscode.moviesbattle.crawling.utils;

import org.springframework.stereotype.Service;

@Service
public class Utils {

    public Double convertToDouble(String s) {
        return Double.parseDouble(s);
    }

    public Long convertToLong(String s) {
        return Long.valueOf(s);
    }

    public String removeParenthesis(String s) {
        s = s.replace("(","");
        s = s.replace(")","");
        return s;
    }
}
