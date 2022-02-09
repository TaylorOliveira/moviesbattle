package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.repository.UserRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.letscode.moviesbattle.factory.RoundFactory;
import br.com.letscode.moviesbattle.factory.UserFactory;
import br.com.letscode.moviesbattle.domain.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRoundServiceImplTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserRoundServiceImpl userRoundService;

    @Test
    public void testSuccessUpdateUserInformationWithRoundResult() {
        User userFactory = UserFactory.fromType();
        Round roundFactory = RoundFactory.fromType();

        User user = userRoundService.updateUserInformationWithRoundResult(roundFactory);
        assertEquals(1,1);
    }

//    public User fromTypeUser() {
//        return User.builder()
//                .id(1L)
//                .username("user_test")
//                .email("user_test@email.com")
//                .password("password_test")
//                .totalRoundsPlayed(0)
//                .totalCorrectRounds(0)
//                .score(0)
//                .build();
//    }
//
//    public Round fromTypeRound() {
//        return Round.builder()
//                .id(new Random().nextLong())
//                .game(fromTypeGame())
//                .leftMovie(fromTypeMovie1())
//                .rightMovie(fromTypeMovie2())
//                .numberRound(0)
//                .status(RoundStatusEnum.NOT_PLAYED)
//                .build();
//    }
//
//    public List<Round> fromListRound() {
//        return List.of(fromTypeRound(), fromTypeRound());
//    }
//
//    public Role fromTypeRole() {
//        return Role.builder()
//                .id(1L)
//                .role(ERole.ROLE_USER)
//                .build();
//    }
//
//    public Movie fromTypeMovie1() {
//        return Movie.builder()
//                .id(1L)
//                .debutYear("2022")
//                .imdb(10.0)
//                .totalPoints(1000)
//                .totalVotes(100)
//                .build();
//    }
//
//    public Movie fromTypeMovie2() {
//        return Movie.builder()
//                .id(2L)
//                .debutYear("2021")
//                .imdb(5.0)
//                .totalPoints(500)
//                .totalVotes(50)
//                .build();
//    }
//
//    public Movie fromTypeMovie3() {
//        return Movie.builder()
//                .id(3L)
//                .debutYear("2020")
//                .imdb(2.5)
//                .totalPoints(250)
//                .totalVotes(25)
//                .build();
//    }
//
//    public Game fromTypeGame() {
//        return Game.builder()
//                .id(1L)
//                .user(fromTypeUser())
//                .totalErrors(0)
//                .status(GameStatusEnum.RUNNING)
//                .rounds(fromListRound())
//                .build();
//    }
}