package ru.grinin.friendy.back.exception;

import ru.grinin.friendy.back.validator.ValidationResult;

import static ru.grinin.friendy.back.util.ValidationErrorUtils.toStringList;


public class ValidException extends Exception{

    public ValidException(ValidationResult result) {
        super(toStringList(result.getErrors()));
    }


}
