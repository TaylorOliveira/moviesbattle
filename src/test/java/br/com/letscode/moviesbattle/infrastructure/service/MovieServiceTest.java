package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.repository.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MovieServiceTest {

    @InjectMocks
    private MovieServiceImpl movieService;
    @Mock
    private MovieRepository movieRepository;

    @Test
    @DisplayName("Test the movies creating")
    public void testSuccess_CreateMovies() {
        when(movieRepository.saveAll(anyCollection())).thenReturn(anyList());
        assertDoesNotThrow(() -> movieService.createMovies(Collections.emptyList()));
    }
}