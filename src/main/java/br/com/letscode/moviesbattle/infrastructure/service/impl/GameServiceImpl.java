package br.com.letscode.moviesbattle.infrastructure.service.impl;

import br.com.letscode.moviesbattle.api.exceptionhandler.exception.UserNotFoundException;
import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.User;
import br.com.letscode.moviesbattle.domain.repository.GameRepository;
import br.com.letscode.moviesbattle.domain.repository.UserRepository;
import br.com.letscode.moviesbattle.domain.service.GameService;
import br.com.letscode.moviesbattle.domain.service.RoundService;
import br.com.letscode.moviesbattle.infrastructure.service.convert.ConvertToGame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoundService roundService;

    public Game initializeGame(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("USER_NOT_FOUND"));

        Game game = createGame(user);
        Round round = roundService.initializeRound(game);
        return game;
    }

    private Game createGame(User user) {
        return gameRepository.save(ConvertToGame.fromEntity(user));
    }

    private Game getGame(Long gameId) {
        return gameRepository.getById(gameId);
    }
}
