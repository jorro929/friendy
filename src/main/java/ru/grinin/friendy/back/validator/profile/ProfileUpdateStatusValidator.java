package ru.grinin.friendy.back.validator.profile;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.dto.ProfileStatusDto;
import ru.grinin.friendy.back.validator.ValidationResult;
import ru.grinin.friendy.back.validator.Validator;
import ru.grinin.friendy.back.validator.util.IdValidator;
import ru.grinin.friendy.back.validator.util.StatusValidator;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileUpdateStatusValidator implements Validator<ProfileStatusDto> {

    @Getter
    private static final ProfileUpdateStatusValidator INSTANCE = new ProfileUpdateStatusValidator();

    private final StatusValidator statusValidator = StatusValidator.getINSTANCE();
    private final IdValidator idValidator = IdValidator.getINSTANCE();

    @Override
    public ValidationResult validate(ProfileStatusDto dto) {
        ValidationResult result = new ValidationResult();
        log.trace("Start profile validation");
        if (dto == null) {
            result.addError(201, "Profile is not be null");
        } else {
            result.addError(idValidator.validate(dto.id()));
            result.addError(statusValidator.validate(dto.status()));
        }
        log.trace("End profile validation");
        return result;
    }
}