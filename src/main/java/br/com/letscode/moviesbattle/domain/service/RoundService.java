package br.com.letscode.moviesbattle.domain.service;

import br.com.letscode.moviesbattle.api.model.enums.ChoiceMovieEnum;
import br.com.letscode.moviesbattle.core.security.service.LoggedInUser;
import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.model.Round;

public interface RoundService {

    Round initializeRound(Game game);

    void processRound(LoggedInUser loggedInUser, Long roundId, ChoiceMovieEnum choice);
}
