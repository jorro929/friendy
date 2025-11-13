package ru.grinin.friendy.back.validator.profile;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.dto.ProfileUpdateEmailDto;
import ru.grinin.friendy.back.validator.ValidationResult;
import ru.grinin.friendy.back.validator.Validator;
import ru.grinin.friendy.back.validator.util.EmailValidator;
import ru.grinin.friendy.back.validator.util.IdValidator;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileUpdateEmailValidator implements Validator<ProfileUpdateEmailDto> {

    @Getter
    private static final ProfileUpdateEmailValidator INSTANCE = new ProfileUpdateEmailValidator();

    private final EmailValidator emailValidator = EmailValidator.getINSTANCE();
    private final IdValidator idValidator = IdValidator.getINSTANCE();

    @Override
    public ValidationResult validate(ProfileUpdateEmailDto dto) {
        ValidationResult result = new ValidationResult();
        log.trace("Start profile validation");
        if (dto == null) {
            result.addError(201, "Profile is not be null");
        } else {
            result.addError(idValidator.validate(dto.id()));
            result.addError(emailValidator.validate(dto.email()));

        }
        log.trace("End profile validation");
        return result;
    }
}