package br.com.letscode.moviesbattle.domain.service;

import br.com.letscode.moviesbattle.api.model.payload.response.RoundGameResponse.GameResponse;
import br.com.letscode.moviesbattle.api.model.payload.response.RoundGameResponse;
import br.com.letscode.moviesbattle.core.security.service.LoggedInUser;

public interface GameService {

    RoundGameResponse gameProcess(LoggedInUser loggedInUser);

    GameResponse finalizeGame(LoggedInUser loggedInUser, Long id);
}
