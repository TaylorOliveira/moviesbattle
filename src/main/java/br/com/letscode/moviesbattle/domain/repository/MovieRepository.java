package br.com.letscode.moviesbattle.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.letscode.moviesbattle.domain.model.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM TBL_MOVIES ORDER BY random() LIMIT 1")
    Movie getRandomMovie();
}
