package ru.grinin.friendy.back.validator.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.validator.Validator;
import ru.grinin.friendy.back.validator.ValidationResult;

import java.util.Objects;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenderValidator implements Validator<Gender> {

    @Getter
    private static final GenderValidator INSTANCE = new GenderValidator();


    @Override
    public ValidationResult validate(Gender gender) {
        log.trace("Start gender validator");
        ValidationResult result = new ValidationResult();
       if(Objects.isNull(gender)){
           result.addError(107, "Gender is not valid");
       }
        log.trace("End gender validator");
        return result;
    }
}
