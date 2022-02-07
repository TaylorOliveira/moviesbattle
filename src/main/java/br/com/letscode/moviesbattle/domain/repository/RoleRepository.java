package br.com.letscode.moviesbattle.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.letscode.moviesbattle.domain.model.enums.ERole;
import org.springframework.stereotype.Repository;
import br.com.letscode.moviesbattle.domain.model.Role;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(ERole name);
}
