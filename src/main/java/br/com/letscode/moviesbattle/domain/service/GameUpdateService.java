package br.com.letscode.moviesbattle.domain.service;

import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.Game;

public interface GameUpdateService {

    Game updateGameTotalErrors(Round roundEntity);
}
