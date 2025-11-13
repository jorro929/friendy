package ru.grinin.friendy.back.util;

import lombok.experimental.UtilityClass;
import ru.grinin.friendy.back.exception.ValidException;
import ru.grinin.friendy.back.validator.ValidationResult;
import ru.grinin.friendy.back.validator.Validator;

@UtilityClass
public class Validation {
    public static <T> void validate(Validator<T> validator, T value) throws ValidException {
        ValidationResult result = validator.validate(value);
        if(!result.isValid()) throw new ValidException(result);
    }
}
