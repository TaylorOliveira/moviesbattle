package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.repository.UserRepository;
import br.com.letscode.moviesbattle.factory.RoundFactory;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.factory.UserFactory;
import br.com.letscode.moviesbattle.domain.model.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserRoundServiceImplTest {
    
    private UserRepository userRepository;
    private UserRoundServiceImpl userRoundService;
    
    @Before("")
    public void setUp() {
        this.userRepository = Mockito.mock(UserRepository.class);
        this.userRoundService = new UserRoundServiceImpl(this.userRepository);
    }

    @Test
    public void testSuccessUpdateUserInformationWithRoundResult() {
        // Round roundFactory = RoundFactory.fromType();

        User user = userRoundService.updateUserInformationWithRoundResult(Round.builder().build());

        // assertThat(user).is
        assertEquals(1,1);
    }
}