package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.api.model.enums.ChoiceMovieEnum;
import br.com.letscode.moviesbattle.api.model.payload.convert.ConvertToRoundValidateResponse;
import br.com.letscode.moviesbattle.api.model.payload.response.RoundValidateResponse;
import br.com.letscode.moviesbattle.core.security.service.LoggedInUser;
import br.com.letscode.moviesbattle.domain.config.exception.EntityNotFoundException;
import br.com.letscode.moviesbattle.domain.model.Role;
import br.com.letscode.moviesbattle.domain.model.User;
import br.com.letscode.moviesbattle.domain.model.enums.ERole;
import br.com.letscode.moviesbattle.domain.repository.MovieRepository;
import br.com.letscode.moviesbattle.domain.repository.RoundRepository;
import br.com.letscode.moviesbattle.domain.service.GameUpdateService;
import br.com.letscode.moviesbattle.domain.service.RoundChoiceService;
import br.com.letscode.moviesbattle.domain.service.RoundService;
import br.com.letscode.moviesbattle.domain.service.UserRoundService;
import br.com.letscode.moviesbattle.infrastructure.factory.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.letscode.moviesbattle.infrastructure.factory.RoundFactory;
import br.com.letscode.moviesbattle.infrastructure.factory.MovieFactory;
import br.com.letscode.moviesbattle.infrastructure.factory.GameFactory;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.Game;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class RoundServiceTest {

    @Autowired
    RoundService roundService;

    @Test
    void successTest_InitializeRound() {
        RoundRepository mockRoundRepository = mock(RoundRepository.class);
        MovieRepository mockMovieRepository = mock(MovieRepository.class);

        Round roundFactory = RoundFactory.fromType();

        when(mockRoundRepository.save(any(Round.class)))
                .thenReturn(roundFactory);

        when(mockMovieRepository.getRandomMovie())
                .thenReturn(MovieFactory.fromTypeMovie1());

        when(mockMovieRepository.getRandomMovie())
                .thenReturn(MovieFactory.fromTypeMovie2());

        Game gameActual = GameFactory.fromType();
        Round roundActual = RoundFactory.fromType();

        assertThat(roundService.initializeRound(gameActual))
                .usingRecursiveComparison()
                    .ignoringFields("id", "leftMovie", "rightMovie", "numberRound")
                .isEqualTo(roundActual);
    }

    @Test
    void successTest_GetRoundNotPayed() {
        assertDoesNotThrow(() -> roundService.getRoundNotPayed(GameFactory.fromType()));
    }

    @Test
    void exceptionTest_idInvalid_ProcessRound() {
        RoundRepository mockRoundRepository = mock(RoundRepository.class);
        RoundChoiceService mockRoundChoiceService = mock(RoundChoiceService.class);
        GameUpdateService mockGameUpdateService = mock(GameUpdateService.class);
        UserRoundService mockUserRoundService = mock(UserRoundService.class);

        LoggedInUser loggedInUser = LoggedInUser.build(getUserFactory());

        assertThrows(EntityNotFoundException.class,
                () -> roundService.processRound(loggedInUser, 100L, ChoiceMovieEnum.LEFT));
    }

    @Test
    void successTest_ProcessRound() {
        RoundRepository mockRoundRepository = mock(RoundRepository.class);
        RoundChoiceService mockRoundChoiceService = mock(RoundChoiceService.class);
        GameUpdateService mockGameUpdateService = mock(GameUpdateService.class);
        UserRoundService mockUserRoundService = mock(UserRoundService.class);

        LoggedInUser loggedInUser = LoggedInUser.build(getUserFactory());

        Round roundFactory = RoundFactory.fromType();
        when(mockRoundChoiceService.validateSelectedMovieInRound(any(), any()))
                .thenReturn(roundFactory);

        Game gameFactory = GameFactory.fromType();
        when(mockGameUpdateService.updateGameWithRoundResult(any()))
                .thenReturn(gameFactory);

        User userFactory = UserFactory.fromType();
        when(mockUserRoundService.updateUserWithRoundResult(any()))
                .thenReturn(userFactory);

        RoundValidateResponse roundValidateResponse =
                ConvertToRoundValidateResponse.fromResponse(roundFactory);

        assertThrows(EntityNotFoundException.class,
                () -> roundService.processRound(loggedInUser, 100L, ChoiceMovieEnum.LEFT));
    }

    private User getUserFactory() {
        HashSet<Role> roles = new HashSet<>();
        roles.add(getRoleFactory());

        User userFactory = UserFactory.fromType();
        userFactory.setRoles(roles);
        return userFactory;
    }

    private Role getRoleFactory() {
        return Role.builder()
                .id(1L)
                .role(ERole.ROLE_USER)
                .build();
    }
}