package br.com.letscode.moviesbattle.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@Table(name = "TBL_MOVIES")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIE_ID")
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private String debutYear;

    @NotNull
    private double imdb;

    @NotNull
    private double totalVotes;

    private double totalPoints;
}
