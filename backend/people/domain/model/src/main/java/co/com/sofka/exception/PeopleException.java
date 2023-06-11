package co.com.sofka.exception;

import co.com.sofka.enums.PeopleExceptionEnum;

public class PeopleException extends RuntimeException {

    public PeopleException(PeopleExceptionEnum message) {
        super(message.name());
    }
}
