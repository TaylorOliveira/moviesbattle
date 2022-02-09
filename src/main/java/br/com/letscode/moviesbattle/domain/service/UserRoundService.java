package br.com.letscode.moviesbattle.domain.service;

import br.com.letscode.moviesbattle.domain.model.Round;
import br.com.letscode.moviesbattle.domain.model.User;

public interface UserRoundService {

    User updateUserInformationWithRoundResult(Round roundEntity);

    void save(User userEntity);
}
