package ru.grinin.friendy.back.validator.util;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.validator.Validator;
import ru.grinin.friendy.back.validator.ValidationResult;
import ru.grinin.friendy.back.validator.util.string.SimpleStringValidator;

import java.time.LocalDate;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateValidator implements Validator<String> {

    @Getter
    private static final DateValidator INSTANCE = new DateValidator();

    private final SimpleStringValidator stringValidator = new SimpleStringValidator();

    @Override
    public ValidationResult validate(String date) {
        ValidationResult result = new ValidationResult();
        log.trace("Start date validation");
        if (!stringValidator.validate(date).isValid()) {
            result.addError(106, "Date is not valid");
        } else {
            try {
                LocalDate date1 = LocalDate.parse(date);
                if(date1.isAfter(LocalDate.now())) result.addError(126, "Date can not be more than current  date");
            } catch (Exception e) {
                result.addError(116, "Date is not valid");
            }
        }
        log.trace("End date validation");

        return result;
    }
}
