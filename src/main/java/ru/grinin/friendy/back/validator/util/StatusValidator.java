package ru.grinin.friendy.back.validator.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.model.supportclass.ProfileStatus;
import ru.grinin.friendy.back.validator.ValidationResult;
import ru.grinin.friendy.back.validator.Validator;

import java.util.Objects;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StatusValidator implements Validator<ProfileStatus> {

    @Getter
    private static final StatusValidator INSTANCE = new StatusValidator();
    @Override
    public ValidationResult validate(ProfileStatus status) {
        ValidationResult result = new ValidationResult();
        log.trace("Start status validator");
       if(Objects.isNull(status)){
           result.addError(109, "Status is not valid");
       }
        log.trace("End status validator");
        return result;
    }
}
