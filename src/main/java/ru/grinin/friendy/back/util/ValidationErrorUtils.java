package ru.grinin.friendy.back.util;

import lombok.experimental.UtilityClass;
import ru.grinin.friendy.back.validator.ValidatorError;

import java.util.List;

@UtilityClass
public class ValidationErrorUtils {

    public static String toStringList(List<ValidatorError> errors){
        StringBuilder builder = new StringBuilder();
        for(ValidatorError error: errors){
            builder.append(error.message()).append(";");
        }
        return builder.toString();
    }
}
