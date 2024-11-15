package br.com.pf.api.exceptions;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

@Getter
@Log4j2
public class GenericException extends RuntimeException{

    private final HttpStatus httpStatus;
    private final String message;

    public GenericException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
