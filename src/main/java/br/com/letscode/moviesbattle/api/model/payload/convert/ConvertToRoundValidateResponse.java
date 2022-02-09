package br.com.letscode.moviesbattle.api.model.payload.convert;

import br.com.letscode.moviesbattle.api.model.payload.response.RoundValidateResponse;
import br.com.letscode.moviesbattle.domain.model.Round;

public abstract class ConvertToRoundValidateResponse {

    public static RoundValidateResponse fromEntity(Round round) {
        return RoundValidateResponse.builder()
                .roundId(round.getId())
                .isCorrectRound(round.isCorrect())
                .status(round.getStatus())
                .numberRound(round.getNumberRound())
                .leftMovie(ConvertToMovieRoundResponse
                        .fromEntity(round.getLeftMovie(), isBiggerPointLeft(round)))
                .rightMovie(ConvertToMovieRoundResponse
                        .fromEntity(round.getRightMovie(), isBiggerPointRight(round)))
                .build();
    }

    private static boolean isBiggerPointLeft(Round round) {
        return round.getLeftMovie().getTotalPoints() >
                round.getRightMovie().getTotalPoints();
    }

    private static boolean isBiggerPointRight(Round round) {
        return round.getRightMovie().getTotalPoints() >
                round.getLeftMovie().getTotalPoints();
    }
}
