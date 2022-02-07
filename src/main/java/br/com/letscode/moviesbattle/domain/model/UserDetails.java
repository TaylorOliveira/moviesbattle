package br.com.letscode.moviesbattle.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBL_USERDETAILS")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USERDETAILS_ID")
    private Long id;

    private Long totalPoints;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
