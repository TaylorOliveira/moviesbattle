package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.api.model.payload.response.RoundGameResponse.GameResponse;
import br.com.letscode.moviesbattle.api.model.payload.convert.ConvertToRoundGameResponse;
import br.com.letscode.moviesbattle.api.model.payload.convert.ConvertToGameResponse;
import br.com.letscode.moviesbattle.domain.config.exception.EntityNotFoundException;
import br.com.letscode.moviesbattle.api.model.payload.response.RoundGameResponse;
import br.com.letscode.moviesbattle.core.security.service.LoggedInUser;
import br.com.letscode.moviesbattle.domain.model.enums.RoundStatusEnum;
import br.com.letscode.moviesbattle.domain.model.convert.ConvertToGame;
import br.com.letscode.moviesbattle.domain.model.enums.GameStatusEnum;
import br.com.letscode.moviesbattle.domain.repository.GameRepository;
import br.com.letscode.moviesbattle.domain.repository.UserRepository;
import br.com.letscode.moviesbattle.domain.service.RoundService;
import br.com.letscode.moviesbattle.domain.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.model.User;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import static br.com.letscode.moviesbattle.domain.config.exception.enums.ExceptionEnum.ENTITY_NOT_FOUND;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoundService roundService;

    public RoundGameResponse gameProcess(LoggedInUser loggedInUser) {
        User userEntity = getUser(loggedInUser);

        Game gameEntityRunning = checkIsGameRunning(userEntity);
        if (gameEntityRunning != null) {

            Round roundEntityNotPayed = getRoundNotPayed(gameEntityRunning);
            if (roundEntityNotPayed != null) {
                return ConvertToRoundGameResponse
                        .fromResponse(gameEntityRunning, roundEntityNotPayed);
            } else {
                Round newEntityRound = roundService.initializeRound(gameEntityRunning);
                return ConvertToRoundGameResponse
                        .fromResponse(gameEntityRunning, newEntityRound);
            }
        }
        Game gameEntity = createGame(userEntity);
        Round roundEntity = roundService.initializeRound(gameEntity);

        return ConvertToRoundGameResponse.fromResponse(gameEntity, roundEntity);
    }

    private User getUser(LoggedInUser loggedInUser) {
        return userRepository.findById(loggedInUser.getId())
                .orElseThrow(() -> new EntityNotFoundException(Game.class, loggedInUser.getId()));
    }

    private Round getRoundNotPayed(Game gameRunning) {
        return gameRunning.getRounds()
                .stream()
                .filter(round -> round.getStatus() == RoundStatusEnum.NOT_PLAYED)
                .findFirst()
                .orElse(null);
    }

    private Game checkIsGameRunning(User userEntity) {
        return gameRepository.findByUserAndStatusRunning(userEntity, GameStatusEnum.RUNNING)
                .orElse(null);
    }

    @Override
    public GameResponse finalizeGame(LoggedInUser loggedInUser, Long id) {
        User userEntity = getUser(loggedInUser);

        Game gameEntity = gameRepository.findByIdAndUser(id, userEntity)
                .orElseThrow(() -> new EntityNotFoundException(Game.class, id));
        gameEntity.setStatus(GameStatusEnum.FINALIZED);

        return ConvertToGameResponse.fromResponse(gameRepository.save(gameEntity));
    }

    private Game createGame(User userEntity) {
        return gameRepository.save(ConvertToGame.fromEntity(userEntity));
    }

    private String getDetail(Class clazz, Long id) {
        return String.format(ENTITY_NOT_FOUND.getDescription(), "id", id, clazz.getName());
    }
}
