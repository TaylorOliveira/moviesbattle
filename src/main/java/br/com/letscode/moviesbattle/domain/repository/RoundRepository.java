package br.com.letscode.moviesbattle.domain.repository;

import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {

    @Query(nativeQuery=true, value="SELECT * FROM TBL_ROUNDS WHERE GAME_ID = :gameId " +
            "AND (LEFT_MOVIE_ID = :leftMovieId AND RIGHT_MOVIE_ID = :rightMovieId) " +
            "OR (LEFT_MOVIE_ID = :rightMovieId AND RIGHT_MOVIE_ID = :leftMovieId)")
    Optional<Round> validMoviesRound(@Param("gameId") Long gameId,
                                     @Param("leftMovieId") Long leftMovieId,
                                     @Param("rightMovieId") Long rightMovieId);

    List<Round> findRoundsByGame(Game game);
}
