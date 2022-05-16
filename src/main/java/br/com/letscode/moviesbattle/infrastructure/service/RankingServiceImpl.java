package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.api.model.payload.convert.ConvertToRankingResponse;
import br.com.letscode.moviesbattle.api.model.payload.response.RankingResponse;
import br.com.letscode.moviesbattle.domain.repository.UserRepository;
import br.com.letscode.moviesbattle.domain.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.letscode.moviesbattle.domain.model.User;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Service
public class RankingServiceImpl implements RankingService {

    @Autowired
    private UserRepository userRepository;

    public RankingResponse getRanking() {
        List<User> users = userRepository.findAllByOrderByScoreDesc();
        return ConvertToRankingResponse.fromResponse(users);
    }
}
