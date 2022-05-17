package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.infrastructure.factory.RoundFactory;
import br.com.letscode.moviesbattle.domain.model.enums.RoundStatusEnum;
import br.com.letscode.moviesbattle.api.model.enums.ChoiceMovieEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.letscode.moviesbattle.domain.model.Round;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RoundChoiceServiceTest {

    @InjectMocks
    RoundChoiceServiceImpl roundChoiceService;

    @Test
    @DisplayName("Test to validate the movie that was selected in the round")
    void successTest_ValidateSelectedMovieInRound() {
        Round roundFactory = RoundFactory.fromType();
        roundFactory.setStatus(RoundStatusEnum.PLAYED);
        assertThat(roundChoiceService
                .validateSelectedMovieInRound(ChoiceMovieEnum.LEFT, roundFactory))
                .isEqualTo(roundFactory);
    }
}