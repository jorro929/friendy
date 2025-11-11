package ru.grinin.friendy.back.exception;

import lombok.Getter;
import ru.grinin.friendy.back.validator.ValidatorResult;

import static ru.grinin.friendy.back.util.ValidationErrorUtils.toStringList;


public class ValidException extends Exception{

    public ValidException(ValidatorResult result) {
        super(toStringList(result.getErrors()));
    }


}
