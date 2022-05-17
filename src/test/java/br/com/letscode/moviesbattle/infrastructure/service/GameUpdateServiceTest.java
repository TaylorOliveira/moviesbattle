package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.exception.ErrorException;
import br.com.letscode.moviesbattle.domain.repository.GameRepository;
import br.com.letscode.moviesbattle.domain.service.GameUpdateService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.letscode.moviesbattle.infrastructure.factory.RoundFactory;
import br.com.letscode.moviesbattle.infrastructure.factory.GameFactory;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.Game;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GameUpdateServiceTest {

    @InjectMocks
    private GameUpdateServiceImpl gameUpdateService;
    @Mock
    private GameRepository gameRepository;

    @Test
    void testSuccess_WithErrorRound_UpdateGameWithRoundResult() {
        Round roundFactory = RoundFactory.fromType();
        Game gameActual = GameFactory.fromType();
        gameActual.setTotalRounds(1);
        gameActual.setTotalErrors(1);
        Game gameExpected = gameUpdateService.updateGameWithRoundResult(roundFactory);
        assertThat(gameExpected)
                .isEqualTo(gameActual);
    }

    @Test
    void testSuccess_WithCorrectRound_UpdateGameWithRoundResult() {
        Round roundFactory = RoundFactory.fromType();
        roundFactory.setCorrect(true);
        Game gameActual = GameFactory.fromType();
        gameActual.setTotalRounds(1);
        Game gameExpected = gameUpdateService.updateGameWithRoundResult(roundFactory);
        assertThat(gameExpected)
                .isEqualTo(gameActual);
    }

    @Test
    void testException_UpdateGameWithRoundResult() {
        Round roundFactory = RoundFactory.fromType();
        roundFactory.getGame().setTotalErrors(3);
        assertThrows(ErrorException.class,
                () -> gameUpdateService.updateGameWithRoundResult(roundFactory));
    }
}