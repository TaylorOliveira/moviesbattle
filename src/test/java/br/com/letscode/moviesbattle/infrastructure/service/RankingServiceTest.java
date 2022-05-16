package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.api.model.payload.convert.ConvertToRankingResponse;
import br.com.letscode.moviesbattle.api.model.payload.response.RankingResponse;
import br.com.letscode.moviesbattle.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.letscode.moviesbattle.infrastructure.factory.UserFactory;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RankingServiceTest {

    @InjectMocks
    private RankingServiceImpl rankingService;
    @Mock
    private UserRepository mockUserRepository;

    @Test
    @DisplayName("Returns the ranking of users")
    void testSuccess_GetRanking() {
        RankingResponse rankingResponseActual =
                ConvertToRankingResponse.fromResponse(Collections.singletonList(UserFactory.fromType()));

        when(mockUserRepository.findAllByOrderByScoreDesc())
                .thenReturn(Collections.singletonList(UserFactory.fromType()));

        assertThat(rankingService.getRanking())
                .isEqualTo(rankingResponseActual);
    }
}