package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.repository.UserRepository;
import br.com.letscode.moviesbattle.domain.service.UserRoundService;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.User;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserRoundServiceImpl implements UserRoundService {

    private final UserRepository userRepository;

    public UserRoundServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User updateUserInformationWithRoundResult(Round roundEntity) {
        User userEntity = roundEntity.getGame().getUser();

        int totalCorrectRounds = userEntity.getTotalCorrectRounds();
        int totalRoundsPlayed = userEntity.getTotalRoundsPlayed();
        if (roundEntity.isCorrect()) {
            userEntity.setTotalCorrectRounds(getTotalCorrectRounds(totalCorrectRounds));
        }

        userEntity.setTotalRoundsPlayed(getTotalRoundsPlayed(totalRoundsPlayed));
        userEntity.setScore(getScoreUser(userEntity));

        return userRepository.save(userEntity);
    }

    @Override
    public void save(User userEntity) {
        userRepository.save(userEntity);
    }

    private int getTotalCorrectRounds(int totalCorrectRounds) {
        return totalCorrectRounds + 1;
    }

    private int getTotalRoundsPlayed(int totalRoundsPlayed) {
        return totalRoundsPlayed + 1;
    }

    private double getScoreUser(User userEntity) {
        int totalCorrectRounds = userEntity.getTotalCorrectRounds();
        double totalRoundsPlayed = userEntity.getTotalRoundsPlayed();
        double correctPercentage = getUserAccuracyPercentage(totalCorrectRounds, totalRoundsPlayed);
        return correctPercentage * totalRoundsPlayed;
    }

    private double getUserAccuracyPercentage(int totalCorrectRounds, double totalRoundsPlayed) {
        return totalCorrectRounds * 100 / totalRoundsPlayed;
    }
}
