package br.com.letscode.moviesbattle.domain.repository;

import br.com.letscode.moviesbattle.domain.model.enums.GameStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM Game g WHERE g.id = :id AND g.user = :user")
    Optional<Game> findByIdAndUser(@Param("id") Long id, @Param("user") User user);

    @Query("SELECT g FROM Game g WHERE g.user = :user AND g.status = :status")
    Optional<Game> findByUserAndStatusRunning(@Param("user") User user, @Param("status") GameStatusEnum status);
}
