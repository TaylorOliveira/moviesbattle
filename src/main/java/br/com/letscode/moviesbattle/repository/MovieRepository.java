package br.com.letscode.moviesbattle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.letscode.moviesbattle.entity.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
