package br.com.letscode.moviesbattle.domain.model;

import br.com.letscode.moviesbattle.domain.model.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBL_ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole role;
}
