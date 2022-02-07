package br.com.letscode.moviesbattle.domain.model;

import br.com.letscode.moviesbattle.domain.model.enums.StatusRound;
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
@Table(name = "TBL_ROUNDS")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROUND_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "MOVIE_X_ID")
    private Movie movieX;

    @ManyToOne
    @JoinColumn(name = "MOVIE_Y_ID")
    private Movie movieY;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StatusRound status;
}
