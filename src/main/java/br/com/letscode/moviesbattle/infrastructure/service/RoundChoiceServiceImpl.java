package br.com.letscode.moviesbattle.infrastructure.service;

import br.com.letscode.moviesbattle.api.model.enums.ChoiceMovieEnum;
import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.enums.RoundStatusEnum;
import br.com.letscode.moviesbattle.domain.service.RoundChoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoundChoiceServiceImpl implements RoundChoiceService {

    @Override
    public Round validateSelectedMovieInRound(ChoiceMovieEnum choice, Round roundEntity) {
        ChoiceMovieEnum choiceCorrect = getCorrectOption(roundEntity);
        roundEntity.setCorrect(choiceCorrect == choice);
        roundEntity.setChoice(choiceCorrect);
        roundEntity.setStatus(RoundStatusEnum.PLAYED);
        return roundEntity;
    }

    private ChoiceMovieEnum getCorrectOption(Round roundEntity) {
        if (roundEntity.getLeftMovie().getTotalPoints() >
                roundEntity.getRightMovie().getTotalPoints()) {
            return ChoiceMovieEnum.LEFT;
        }
        return ChoiceMovieEnum.RIGHT;
    }
}
