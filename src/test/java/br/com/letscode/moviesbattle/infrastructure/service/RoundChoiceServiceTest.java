package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.infrastructure.factory.RoundFactory;
import br.com.letscode.moviesbattle.domain.model.enums.RoundStatusEnum;
import br.com.letscode.moviesbattle.domain.service.RoundChoiceService;
import br.com.letscode.moviesbattle.api.model.enums.ChoiceMovieEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.letscode.moviesbattle.domain.model.Round;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RoundChoiceServiceTest {

    @Autowired
    RoundChoiceService roundChoiceService;

    @Test
    void successTest_ValidateSelectedMovieInRound() {
        Round roundFactory = RoundFactory.fromType();
        roundFactory.setStatus(RoundStatusEnum.PLAYED);
        assertThat(roundChoiceService.validateSelectedMovieInRound(ChoiceMovieEnum.LEFT, roundFactory))
                .isEqualTo(roundFactory);
    }
}