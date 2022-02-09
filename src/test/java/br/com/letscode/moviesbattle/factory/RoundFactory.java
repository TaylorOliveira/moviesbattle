package br.com.letscode.moviesbattle.factory;

import br.com.letscode.moviesbattle.domain.model.enums.RoundStatusEnum;
import br.com.letscode.moviesbattle.domain.model.Round;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public abstract class RoundFactory {

    public static Round fromType() {
        return Round.builder()
                .id(new Random().nextLong())
                .game(GameFactory.fromType())
                .leftMovie(MovieFactory.fromTypeMovie1())
                .rightMovie(MovieFactory.fromTypeMovie2())
                .numberRound(0)
                .status(RoundStatusEnum.NOT_PLAYED)
                .build();
    }

    public static List<Round> fromList() {
        List<Round> rounds = new ArrayList<>();
        rounds.add(fromType());
        rounds.add(fromType());
        return rounds;
    }
}
