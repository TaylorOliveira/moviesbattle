package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import br.com.letscode.moviesbattle.infrastructure.factory.RoundFactory;
import br.com.letscode.moviesbattle.infrastructure.factory.UserFactory;
import br.com.letscode.moviesbattle.domain.model.*;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRoundServiceTest {

    @InjectMocks
    private UserRoundServiceImpl userRoundService;
    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("Test to update user information with data from a round")
    public void testSuccess_UpdateUserInformationWithRoundResult() {
        Round roundFactory = RoundFactory.fromType();
        User userActual = UserFactory.fromType();
        userActual.setTotalRoundsPlayed(1);
        when(userRepository.save(any()))
                .thenReturn(any());
        User userExpected = userRoundService
                .updateUserWithRoundResult(roundFactory);
        assertThat(userExpected)
                .isEqualTo(userActual);
    }
}