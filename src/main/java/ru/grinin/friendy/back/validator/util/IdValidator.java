package ru.grinin.friendy.back.validator.util;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.validator.ValidationError;
import ru.grinin.friendy.back.validator.ValidationResult;
import ru.grinin.friendy.back.validator.Validator;

import java.util.UUID;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdValidator implements Validator<UUID> {

    @Getter
    private final static IdValidator INSTANCE = new IdValidator();

    @Override
    public ValidationResult validate(UUID id) {
        ValidationResult result = new ValidationResult();
        log.trace("Start id validation");
        if (id == null) {
            result.addError(new ValidationError(109, "Id is not valid"));
        }
        log.trace("End id validation");
        return result;
    }
}