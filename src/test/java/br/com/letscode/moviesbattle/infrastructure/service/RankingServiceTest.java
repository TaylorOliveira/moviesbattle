package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.api.model.payload.convert.ConvertToRankingResponse;
import br.com.letscode.moviesbattle.api.model.payload.response.RankingResponse;
import br.com.letscode.moviesbattle.domain.repository.UserRepository;
import br.com.letscode.moviesbattle.domain.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.letscode.moviesbattle.infrastructure.factory.UserFactory;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class RankingServiceTest {

    @Autowired
    RankingService rankingService;

    @Test
    void testSuccess_GetRanking() {
        RankingResponse rankingResponseActual =
                ConvertToRankingResponse.fromResponse(Collections.singletonList(UserFactory.fromType()));

        UserRepository mock = Mockito.mock(UserRepository.class);

        when(mock.findAllByOrderByScoreDesc())
                .thenReturn(Collections.singletonList(UserFactory.fromType()));

        assertThat(rankingService.getRanking())
                .isEqualTo(rankingResponseActual);
    }
}