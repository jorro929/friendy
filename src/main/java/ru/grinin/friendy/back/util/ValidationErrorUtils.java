package ru.grinin.friendy.back.util;

import lombok.experimental.UtilityClass;
import ru.grinin.friendy.back.validator.ValidationError;

import java.util.List;

@UtilityClass
public class ValidationErrorUtils {

    public static String toStringList(List<ValidationError> errors){
        StringBuilder builder = new StringBuilder();
        for(ValidationError error: errors){
            builder.append(error.message()).append(";");
        }
        return builder.toString();
    }
}
