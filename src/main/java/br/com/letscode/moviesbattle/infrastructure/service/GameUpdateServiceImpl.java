package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.exception.ErrorException;
import br.com.letscode.moviesbattle.domain.repository.GameRepository;
import br.com.letscode.moviesbattle.domain.service.GameUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.Game;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import static br.com.letscode.moviesbattle.domain.exception.enums.ExceptionEnum.GAME_TOTAL_ERRORS;

@Slf4j
@Service
public class GameUpdateServiceImpl implements GameUpdateService {

    @Value("${moviesbattle.app.totalErrors}")
    private int TOTAL_ERRORS_ALLOWED;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game updateGameWithRoundResult(Round roundEntity) {
        Game gameEntity = roundEntity.getGame();
        int totalRounds = gameEntity.getTotalRounds();
        gameEntity.setTotalRounds(++totalRounds);

        if (isLessThanTheTotalNumberOfErrorsAllowed(roundEntity, gameEntity)) {
            throw new ErrorException(GAME_TOTAL_ERRORS);

        } else if (itNotCorrect(roundEntity)) {
            int numberErrors = gameEntity.getTotalErrors();
            gameEntity.setTotalErrors(++numberErrors);
        }
        return gameRepository.save(gameEntity);
    }

    private boolean isLessThanTheTotalNumberOfErrorsAllowed(Round roundEntity, Game gameEntity) {
        return itNotCorrect(roundEntity) &&
                gameEntity.getTotalErrors() == TOTAL_ERRORS_ALLOWED;
    }

    private boolean itNotCorrect(Round roundEntity) {
        return !roundEntity.isCorrect();
    }
}
