package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.model.Movie;
import br.com.letscode.moviesbattle.domain.repository.MovieRepository;
import br.com.letscode.moviesbattle.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void createMovies(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }
}
