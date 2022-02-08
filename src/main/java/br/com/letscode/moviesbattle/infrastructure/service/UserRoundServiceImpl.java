package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.User;
import br.com.letscode.moviesbattle.domain.repository.UserRepository;
import br.com.letscode.moviesbattle.domain.service.UserRoundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.letscode.moviesbattle.domain.constants.DomainConstants.ADD_ANOTHER_CORRECT_ROUND;
import static br.com.letscode.moviesbattle.domain.constants.DomainConstants.ADD_ONE_MORE_ROUND_PLAYED;

@Slf4j
@Service
public class UserRoundServiceImpl implements UserRoundService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User updateUserInformationWithRoundResult(Round roundEntity) {
        User user = roundEntity.getGame().getUser();

        int totalCorrectRounds = user.getTotalCorrectRounds();
        int totalRoundsPlayed = user.getTotalRoundsPlayed();
        if (roundEntity.isCorrect()) {
            user.setTotalCorrectRounds(getTotalCorrectRounds(totalCorrectRounds));
        }
        user.setTotalRoundsPlayed(getTotalRoundsPlayed(totalRoundsPlayed));

        user.setScore(getScoreUser(user));
        return userRepository.save(user);
    }

    private int getTotalCorrectRounds(int totalCorrectRounds) {
        return totalCorrectRounds + ADD_ANOTHER_CORRECT_ROUND;
    }

    private int getTotalRoundsPlayed(int totalRoundsPlayed) {
        return totalRoundsPlayed + ADD_ONE_MORE_ROUND_PLAYED;
    }

    private double getScoreUser(User user) {
        int totalCorrectRounds = user.getTotalCorrectRounds();
        double totalRoundsPlayed = user.getTotalRoundsPlayed();
        double correctPercentage = getUserAccuracyPercentage(totalCorrectRounds, totalRoundsPlayed);
        return correctPercentage * totalRoundsPlayed;
    }

    private double getUserAccuracyPercentage(int totalCorrectRounds, double totalRoundsPlayed) {
        return totalCorrectRounds * 100 / totalRoundsPlayed;
    }
}
