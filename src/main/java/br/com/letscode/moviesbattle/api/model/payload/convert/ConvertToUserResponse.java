package br.com.letscode.moviesbattle.api.model.payload.convert;

import br.com.letscode.moviesbattle.api.model.payload.response.RankingResponse.UserResponse;
import br.com.letscode.moviesbattle.domain.model.User;
import java.util.ArrayList;
import java.util.List;

public abstract class ConvertToUserResponse {

    public static UserResponse fromResponse(User user, int ranking) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .score(user.getScore())
                .ranking(ranking)
                .build();
    }

    public static List<UserResponse> buildList(List<User> users) {
        int count = 1;
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(fromResponse(user, count));
            count = count + 1;
        }
        return userResponses;
    }
}
