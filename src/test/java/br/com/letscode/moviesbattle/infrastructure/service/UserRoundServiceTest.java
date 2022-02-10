package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.service.UserRoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.letscode.moviesbattle.factory.RoundFactory;
import br.com.letscode.moviesbattle.factory.UserFactory;
import br.com.letscode.moviesbattle.domain.model.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRoundServiceTest {

    @Autowired
    UserRoundService userRoundService;

    @Test
    public void testSuccess_UpdateUserInformationWithRoundResult() {
        Round roundFactory = RoundFactory.fromType();

        User userActual = UserFactory.fromType();
        userActual.setTotalRoundsPlayed(1);

        User userExpected = userRoundService
                .updateUserWithRoundResult(roundFactory);

        assertThat(userExpected)
                .isEqualTo(userActual);
    }
}