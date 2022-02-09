package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.service.UserRoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.letscode.moviesbattle.factory.RoundFactory;
import br.com.letscode.moviesbattle.factory.UserFactory;
import br.com.letscode.moviesbattle.domain.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@SpringBootTest
class UserRoundServiceTest {

    @Autowired
    UserRoundService userRoundService;

    @Test
    public void testSuccessUpdateUserInformationWithRoundResult() {
        Round roundFactory = RoundFactory.fromType();

        User userActual = UserFactory.fromType();
        userActual.setTotalRoundsPlayed(1);

        User userExpected = userRoundService
                .updateUserWithRoundResult(roundFactory);

        Assertions.assertThat(userExpected)
                .isEqualTo(userActual);
    }
}