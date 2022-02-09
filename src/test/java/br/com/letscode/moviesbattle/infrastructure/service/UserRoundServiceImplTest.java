package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

        assertEquals(1,1);
    }
}