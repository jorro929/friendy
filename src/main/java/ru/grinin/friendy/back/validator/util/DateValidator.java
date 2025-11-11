package ru.grinin.friendy.back.validator.util;


import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.ParseException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.validator.Validator;
import ru.grinin.friendy.back.validator.ValidatorError;
import ru.grinin.friendy.back.validator.ValidatorResult;
import ru.grinin.friendy.back.validator.util.string.SimpleStringValidator;

import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateValidator implements Validator<String> {

    @Getter
    private static final DateValidator INSTANCE = new DateValidator();

    private final SimpleStringValidator stringValidator = new SimpleStringValidator();

    @Override
    public ValidatorResult validate(String date) {
        ValidatorResult result = new ValidatorResult();
        log.trace("Start date validation");
        if (!stringValidator.validate(date).isValid()) {
            result.addError(106, "Date is not valid");
            return result;
        }
        try {
            LocalDate.parse(date);
        } catch (Exception e) {
            result.addError(116, "Date is not valid");
        }
        log.trace("End date validation");

        return result;
    }
}
