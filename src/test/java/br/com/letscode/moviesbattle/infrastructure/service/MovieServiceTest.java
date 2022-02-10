package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    MovieService movieService;

    @Test
    public void testSuccess_CreateMovies() {
        assertDoesNotThrow(() -> movieService.createMovies(Collections.emptyList()));
    }
}