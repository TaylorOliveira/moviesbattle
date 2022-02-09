package br.com.letscode.moviesbattle.domain.service;

import br.com.letscode.moviesbattle.api.model.payload.response.RoundValidateResponse;
import br.com.letscode.moviesbattle.core.security.service.LoggedInUser;
import br.com.letscode.moviesbattle.api.model.enums.ChoiceMovieEnum;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.Game;

public interface RoundService {

    Round initializeRound(Game game);

    RoundValidateResponse processRound(LoggedInUser loggedInUser, Long roundId, ChoiceMovieEnum choice);
}
