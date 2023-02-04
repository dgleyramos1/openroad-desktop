package com.openroad.api.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe que extende RunTimeException para informar que o usuário não foi
 * encontrado
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    /**
     * @param id
     *           Cosntrutor da classe, recebe como parametro o id do usuário que não
     *           foi encontrado
     */
    public UserNotFoundException(String id) {
        super("User not found with id " + id);
    }

}
