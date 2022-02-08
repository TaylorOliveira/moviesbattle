package br.com.letscode.moviesbattle.domain.model;

import br.com.letscode.moviesbattle.api.model.enums.ChoiceMovieEnum;
import br.com.letscode.moviesbattle.domain.model.enums.RoundStatusEnum;
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
    @JoinColumn(name = "LEFT_MOVIE_ID")
    private Movie leftMovie;

    @ManyToOne
    @JoinColumn(name = "RIGHT_MOVIE_ID")
    private Movie rightMovie;

    private int numberRound;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ChoiceMovieEnum choice;

    private boolean isCorrect;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoundStatusEnum status;
}
