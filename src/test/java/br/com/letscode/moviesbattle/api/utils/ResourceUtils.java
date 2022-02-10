package br.com.letscode.moviesbattle.api.utils;

import org.springframework.util.StreamUtils;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.InputStream;

public class ResourceUtils {

	public static String getContentFromResource(String resourceName) {
		try {
			InputStream stream = ResourceUtils.class.getResourceAsStream(resourceName);
			return StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}