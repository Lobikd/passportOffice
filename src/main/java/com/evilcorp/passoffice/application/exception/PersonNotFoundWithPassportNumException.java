package com.evilcorp.passoffice.application.exception;

public class PersonNotFoundWithPassportNumException extends DataNotFoundException {
    public PersonNotFoundWithPassportNumException(String passportNum) {
        super(String.format("Person not found by passport number = %s", passportNum));
    }
}
