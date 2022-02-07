package br.com.letscode.moviesbattle.domain.service;

import br.com.letscode.moviesbattle.domain.model.Game;

public interface GameService {

    Game initializeGame(Long userId);
}
