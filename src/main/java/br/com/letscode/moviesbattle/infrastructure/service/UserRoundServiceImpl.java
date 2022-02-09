package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.domain.repository.UserRepository;
import br.com.letscode.moviesbattle.domain.service.UserRoundService;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.User;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import static br.com.letscode.moviesbattle.domain.config.constants.BusinessRuleConstants.ADD_ANOTHER_CORRECT_ROUND;
import static br.com.letscode.moviesbattle.domain.config.constants.BusinessRuleConstants.ADD_ONE_MORE_ROUND_PLAYED;

@Slf4j
@Service
public class UserRoundServiceImpl implements UserRoundService {

    private final UserRepository userRepository;

    public UserRoundServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
