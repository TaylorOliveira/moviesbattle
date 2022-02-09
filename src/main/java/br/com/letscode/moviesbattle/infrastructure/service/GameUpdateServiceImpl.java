package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.config.exception.ErrorException;
import br.com.letscode.moviesbattle.domain.repository.GameRepository;
import br.com.letscode.moviesbattle.domain.service.GameUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.Game;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import static br.com.letscode.moviesbattle.domain.config.exception.enums.ExceptionEnum.GAME_TOTAL_ERRORS;

@Slf4j
@Service
public class GameUpdateServiceImpl implements GameUpdateService {

    @Value("${moviesbattle.app.totalErrors}")
    private int TOTAL_ERRORS_ALLOWED;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game updateGameTotalErrors(Round roundEntity) {
        Game gameEntity = roundEntity.getGame();
        int totalRounds = gameEntity.getTotalRounds();
        gameEntity.setTotalRounds(totalRounds + 1);

        if (ruleTotalErrors(roundEntity, gameEntity)) {
            throw new ErrorException(GAME_TOTAL_ERRORS);

        } else if (isNotCorrect(roundEntity)) {
            int numberErrors = gameEntity.getTotalErrors();
            gameEntity.setTotalErrors(numberErrors + 1);
        }

        return gameEntity;
    }

    @Override
    public void save(Game gameEntity) {
        gameRepository.save(gameEntity);
    }

    private boolean ruleTotalErrors(Round roundEntity, Game gameEntity) {
        return isNotCorrect(roundEntity) &&
                gameEntity.getTotalErrors() == TOTAL_ERRORS_ALLOWED;
    }

    private boolean isNotCorrect(Round roundEntity) {
        return !roundEntity.isCorrect();
    }
}
