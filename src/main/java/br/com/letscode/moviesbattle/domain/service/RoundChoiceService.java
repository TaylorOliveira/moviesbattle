package br.com.letscode.moviesbattle.domain.service;

import br.com.letscode.moviesbattle.api.model.enums.ChoiceMovieEnum;
import br.com.letscode.moviesbattle.domain.model.Round;

public interface RoundChoiceService {

    Round validateSelectedMovieInRound(ChoiceMovieEnum choice, Round roundEntity);
}
