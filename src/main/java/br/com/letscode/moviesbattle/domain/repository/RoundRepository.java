package br.com.letscode.moviesbattle.domain.repository;

import br.com.letscode.moviesbattle.domain.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {

    @Query(nativeQuery=true,
            value="SELECT * FROM TBL_ROUNDS WHERE GAME_ID = :gameId AND (MOVIE_X_ID = :movieXId AND MOVIE_Y_ID = :movieYId) OR (MOVIE_X_ID = :movieYId AND MOVIE_Y_ID = :movieXId)")
    Optional<Round> validRound(@Param("gameId") Long gameId,
                               @Param("movieXId") Long movieXId,
                               @Param("movieYId") Long movieYId);
}
