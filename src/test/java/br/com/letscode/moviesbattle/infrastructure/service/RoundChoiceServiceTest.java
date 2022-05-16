package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.infrastructure.factory.RoundFactory;
import br.com.letscode.moviesbattle.domain.model.enums.RoundStatusEnum;
import br.com.letscode.moviesbattle.domain.service.RoundChoiceService;
import br.com.letscode.moviesbattle.api.model.enums.ChoiceMovieEnum;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.letscode.moviesbattle.domain.model.Round;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RoundChoiceServiceTest {

    @InjectMocks
    RoundChoiceServiceImpl roundChoiceService;

    @Test
    void successTest_ValidateSelectedMovieInRound() {
        Round roundFactory = RoundFactory.fromType();
        roundFactory.setStatus(RoundStatusEnum.PLAYED);
        assertThat(roundChoiceService
                .validateSelectedMovieInRound(ChoiceMovieEnum.LEFT, roundFactory))
                .isEqualTo(roundFactory);
    }
}