package br.com.letscode.moviesbattle.service.impl;

import br.com.letscode.moviesbattle.entity.Movie;
import br.com.letscode.moviesbattle.repository.MovieRepository;
import br.com.letscode.moviesbattle.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public void createMovies(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }
}
