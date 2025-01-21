package com.evilcorp.passoffice.application.exception;

import java.util.UUID;

public class PersonNotFoundWithPersonIdException extends DataNotFoundException {
    public PersonNotFoundWithPersonIdException(UUID personId) {
        super(String.format("Person not found by personId = %s", personId ));
    }
}
