package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.api.model.enums.ChoiceMovieEnum;
import br.com.letscode.moviesbattle.core.security.service.LoggedInUser;
import br.com.letscode.moviesbattle.domain.exception.EntityNotFoundException;
import br.com.letscode.moviesbattle.domain.model.Role;
import br.com.letscode.moviesbattle.domain.model.User;
import br.com.letscode.moviesbattle.domain.model.enums.ERole;
import br.com.letscode.moviesbattle.domain.repository.MovieRepository;
import br.com.letscode.moviesbattle.domain.repository.RoundRepository;
import br.com.letscode.moviesbattle.infrastructure.factory.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.letscode.moviesbattle.infrastructure.factory.RoundFactory;
import br.com.letscode.moviesbattle.infrastructure.factory.MovieFactory;
import br.com.letscode.moviesbattle.infrastructure.factory.GameFactory;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.Game;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoundServiceTest {

    @InjectMocks
    private RoundServiceImpl roundService;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private RoundRepository roundRepository;

    @Test
    @DisplayName("Test the boot of a round")
    void successTest_InitializeRound() {
        Round roundFactory = RoundFactory.fromType();
        when(roundRepository.save(any(Round.class)))
                .thenReturn(roundFactory);
        when(movieRepository.getRandomMovie())
                .thenReturn(MovieFactory.fromTypeMovie1());
        when(movieRepository.getRandomMovie())
                .thenReturn(MovieFactory.fromTypeMovie2());
        Game gameActual = GameFactory.fromType();
        Round roundActual = RoundFactory.fromType();
        assertThat(roundService.initializeRound(gameActual))
                .usingRecursiveComparison()
                    .ignoringFields("id", "leftMovie", "rightMovie", "numberRound")
                .isEqualTo(roundActual);
    }

    @Test
    @DisplayName("Test to run unplayed round fetch flow without throwing exception")
    void successTest_GetRoundNotPayed() {
        when(roundRepository.findRoundByGameAndStatusNotPlayed(any(), any()))
                .thenReturn(Optional.empty());
        assertDoesNotThrow(() -> roundService.getRoundNotPayed(GameFactory.fromType()));
    }

    @Test
    @DisplayName("Test to process invalid round returning entity not found")
    void exceptionTest_idInvalid_ProcessRound() {
        when(roundRepository.findById(anyLong()))
                .thenThrow(EntityNotFoundException.class);
        LoggedInUser loggedInUser = LoggedInUser.build(getUserFactory());
        assertThatThrownBy(() -> roundService.processRound(loggedInUser, 100L, ChoiceMovieEnum.LEFT))
                .isInstanceOf(EntityNotFoundException.class);
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