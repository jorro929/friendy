package ru.grinin.friendy.back.validator.util;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.dto.ProfileRegistrationDto;
import ru.grinin.friendy.back.validator.Validator;
import ru.grinin.friendy.back.validator.ValidatorError;
import ru.grinin.friendy.back.validator.ValidatorResult;
import ru.grinin.friendy.back.validator.profile.ProfileRegistrationValidator;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailValidator implements Validator<String> {

    @Getter
    private static final EmailValidator INSTANCE = new EmailValidator();


    @Override
    public ValidatorResult validate(String email){
        ValidatorResult result = new ValidatorResult();
        log.trace("Start email validation");
        try {
            new InternetAddress(email).validate();
        } catch (AddressException e) {
            result.addError(new ValidatorError(101, "Email is not valid"));
        }
        log.trace("End email validation");
        return result;
    }
}
