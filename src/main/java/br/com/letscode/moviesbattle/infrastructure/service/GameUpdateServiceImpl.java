package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.exceptionhandler.TotalErrorsException;
import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.repository.GameRepository;
import br.com.letscode.moviesbattle.domain.service.GameUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

        if (ruleTotalErrors(roundEntity, gameEntity)) {
            throw new TotalErrorsException("The maximum amount of errors has been reached");

        } else if (isNotCorrect(roundEntity)) {
            int numberErrors = gameEntity.getTotalErrors();
            gameEntity.setTotalErrors(numberErrors + 1);
        }
        return gameRepository.save(gameEntity);
    }

    private boolean ruleTotalErrors(Round roundEntity, Game gameEntity) {
        return isNotCorrect(roundEntity) &&
                gameEntity.getTotalErrors() == TOTAL_ERRORS_ALLOWED;
    }

    private boolean isNotCorrect(Round roundEntity) {
        return !roundEntity.isCorrect();
    }
}
