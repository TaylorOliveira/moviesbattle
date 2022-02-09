package br.com.letscode.moviesbattle.api.model.payload.convert;

import br.com.letscode.moviesbattle.api.model.payload.response.RankingResponse;
import br.com.letscode.moviesbattle.domain.model.User;
import java.util.List;

public abstract class ConvertToRankingResponse {

    public static RankingResponse fromResponse(List<User> users) {
        return RankingResponse.builder()
                .ranking(ConvertToUserResponse.buildList(users))
                .build();
    }
}
