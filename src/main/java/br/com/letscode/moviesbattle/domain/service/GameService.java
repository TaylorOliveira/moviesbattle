package br.com.letscode.moviesbattle.domain.service;

import br.com.letscode.moviesbattle.api.model.response.GameResponse;
import br.com.letscode.moviesbattle.api.model.response.RoundGameResponse;
import br.com.letscode.moviesbattle.core.security.service.LoggedInUser;

public interface GameService {

    RoundGameResponse initializeGame(LoggedInUser loggedInUser);

    GameResponse finalizeGame(Long id);
}
