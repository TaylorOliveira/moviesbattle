package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.api.model.payload.response.RoundGameResponse.GameResponse;
import br.com.letscode.moviesbattle.domain.exceptionhandler.UserNotFoundException;
import br.com.letscode.moviesbattle.api.model.payload.convert.ConvertToGameResponse;
import br.com.letscode.moviesbattle.api.model.payload.convert.ConvertToRoundGameResponse;
import br.com.letscode.moviesbattle.api.model.payload.response.RoundGameResponse;
import br.com.letscode.moviesbattle.core.security.service.LoggedInUser;
import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.User;
import br.com.letscode.moviesbattle.domain.model.enums.GameStatusEnum;
import br.com.letscode.moviesbattle.domain.model.enums.RoundStatusEnum;
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
        User userEntity = getUser(loggedInUser);

        Game gameEntityRunning = checkIsGameRunning(userEntity);

        if (gameEntityRunning != null) {
            Round roundEntityNotPayed = getRoundNotPayed(gameEntityRunning);

            if (roundEntityNotPayed != null) {
                return ConvertToRoundGameResponse
                        .fromEntity(gameEntityRunning, roundEntityNotPayed);
            } else {
                Round newEntityRound = roundService.initializeRound(gameEntityRunning);
                return ConvertToRoundGameResponse
                        .fromEntity(gameEntityRunning, newEntityRound);
            }
        }
        Game gameEntity = createGame(userEntity);
        Round roundEntity = roundService.initializeRound(gameEntity);

        return ConvertToRoundGameResponse.fromEntity(gameEntity, roundEntity);
    }

    private User getUser(LoggedInUser loggedInUser) {
        return userRepository.findById(loggedInUser.getId())
                .orElseThrow(() -> new UserNotFoundException(String
                        .format("User %s not found", loggedInUser.getUsername())));
    }

    private Round getRoundNotPayed(Game gameRunning) {
        return gameRunning.getRounds()
                .stream()
                .filter(round -> round.getStatus() == RoundStatusEnum.NOT_PLAYED)
                .findFirst()
                .orElse(null);
    }

    private Game checkIsGameRunning(User userEntity) {
        return gameRepository
                .findByUserAndStatusRunning(userEntity, GameStatusEnum.RUNNING)
                .orElse(null);
    }

    @Override
    public GameResponse finalizeGame(LoggedInUser loggedInUser, Long id) {
        User userEntity = getUser(loggedInUser);

        Game gameEntity = gameRepository.findByIdAndUser(id, userEntity)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Entity %s of type %s not found", id, Game.class.getName())));
        gameEntity.setStatus(GameStatusEnum.FINALIZED);

        return ConvertToGameResponse.fromEntity(gameRepository.save(gameEntity));
    }

    private Game createGame(User userEntity) {
        return gameRepository.save(ConvertToGame.fromEntity(userEntity));
    }
}
