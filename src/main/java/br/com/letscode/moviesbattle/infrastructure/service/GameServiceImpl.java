package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.exceptionhandler.UserNotFoundException;
import br.com.letscode.moviesbattle.api.model.convert.ConvertToGameResponse;
import br.com.letscode.moviesbattle.api.model.convert.ConvertToRoundGameResponse;
import br.com.letscode.moviesbattle.api.model.response.GameResponse;
import br.com.letscode.moviesbattle.api.model.response.RoundGameResponse;
import br.com.letscode.moviesbattle.core.security.service.LoggedInUser;
import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.User;
import br.com.letscode.moviesbattle.domain.model.enums.GameStatusEnum;
import br.com.letscode.moviesbattle.domain.repository.GameRepository;
import br.com.letscode.moviesbattle.domain.repository.UserRepository;
import br.com.letscode.moviesbattle.domain.service.GameService;
import br.com.letscode.moviesbattle.domain.service.RoundService;
import br.com.letscode.moviesbattle.domain.model.convert.ConvertToGame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoundService roundService;

    public RoundGameResponse initializeGame(LoggedInUser loggedInUser) {
        User user = userRepository.findById(loggedInUser.getId())
                .orElseThrow(() -> new UserNotFoundException("USER_NOT_FOUND"));

        Game game = saveGame(user);
        Round round = roundService.initializeRound(game);
        return ConvertToRoundGameResponse.fromEntity(game, round);
    }

    @Override
    public GameResponse finalizeGame(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        game.setStatus(GameStatusEnum.FINALIZED);
        return ConvertToGameResponse.fromEntity(gameRepository.save(game));
    }

    private Game saveGame(User user) {
        return gameRepository.save(ConvertToGame.fromEntity(user));
    }
}
