package ru.grinin.friendy.back.validator.profile;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.dto.ProfileUpdateCredentialsDto;
import ru.grinin.friendy.back.validator.ValidationResult;
import ru.grinin.friendy.back.validator.Validator;
import ru.grinin.friendy.back.validator.util.EmailValidator;
import ru.grinin.friendy.back.validator.util.IdValidator;
import ru.grinin.friendy.back.validator.util.string.PasswordValidator;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileUpdateCredentialsValidator implements Validator<ProfileUpdateCredentialsDto> {

    @Getter
    private static final ProfileUpdateCredentialsValidator INSTANCE = new ProfileUpdateCredentialsValidator();

    private final EmailValidator emailValidator = EmailValidator.getINSTANCE();

    private final PasswordValidator passwordValidator = new PasswordValidator();
    private final IdValidator idValidator = IdValidator.getINSTANCE();

    @Override
    public ValidationResult validate(ProfileUpdateCredentialsDto dto) {
        ValidationResult result = new ValidationResult();
        log.trace("Start profile validation");
        if (dto == null) {
            result.addError(201, "Profile is not be null");
        } else {
            result.addError(idValidator.validate(dto.id()));
            result.addError(emailValidator.validate(dto.email()));
            result.addError(passwordValidator.validate(dto.password()));

        }
        log.trace("End profile validation");
        return result;
    }
}