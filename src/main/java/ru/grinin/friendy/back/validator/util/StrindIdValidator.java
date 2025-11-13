package ru.grinin.friendy.back.validator.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.validator.ValidationResult;
import ru.grinin.friendy.back.validator.Validator;
import ru.grinin.friendy.back.validator.ValidationError;
import ru.grinin.friendy.back.validator.util.string.SimpleStringValidator;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StrindIdValidator implements Validator<String> {
    @Getter
    private static final StrindIdValidator INSTANCE = new StrindIdValidator();
    private final SimpleStringValidator stringValidator = new SimpleStringValidator();
    @Override
    public ValidationResult validate(String id) {
        ValidationResult result = new ValidationResult();
        log.trace("Start id validation");
        if (!stringValidator.validate(id).isValid()) {
            result.addError(108, "Id is not valid");
        } else if (!id.matches("^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$")) {
            result.addError(new ValidationError(118, "Id is not valid"));
        }
        log.trace("End id validation");
        return result;
    }
}