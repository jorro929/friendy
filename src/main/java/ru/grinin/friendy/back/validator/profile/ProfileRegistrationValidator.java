package ru.grinin.friendy.back.validator.profile;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.dto.ProfileRegistrationDto;
import ru.grinin.friendy.back.validator.Validator;
import ru.grinin.friendy.back.validator.ValidatorResult;
import ru.grinin.friendy.back.validator.util.DateValidator;
import ru.grinin.friendy.back.validator.util.EmailValidator;
import ru.grinin.friendy.back.validator.util.GenderValidator;
import ru.grinin.friendy.back.validator.util.string.NameValidator;
import ru.grinin.friendy.back.validator.util.string.PasswordValidator;
import ru.grinin.friendy.back.validator.util.string.SimpleStringValidator;
import ru.grinin.friendy.back.validator.util.string.SurnameValidator;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileRegistrationValidator implements Validator<ProfileRegistrationDto> {

    @Getter
    private static final ProfileRegistrationValidator INSTANCE = new ProfileRegistrationValidator();

    private final EmailValidator emailValidator = EmailValidator.getINSTANCE();

    private final PasswordValidator passwordValidator = new PasswordValidator();
    private final NameValidator nameValidator = new NameValidator();
    private final SurnameValidator surnameValidator = new SurnameValidator();
    private final GenderValidator genderValidator = GenderValidator.getINSTANCE();
    private final DateValidator dateValidator = DateValidator.getINSTANCE();

    @Override
    public ValidatorResult validate(ProfileRegistrationDto dto) {
        ValidatorResult result = new ValidatorResult();

        log.trace("Start profile validation");

        if (dto == null) {
            result.addError(201, "Profile is not be null");
            return result;
        }

        result.addError(emailValidator.validate(dto.getEmail()));
        result.addError(passwordValidator.validate(dto.getPassword()));
        result.addError(nameValidator.validate(dto.getName()));
        result.addError(surnameValidator.validate(dto.getSurname()));
        result.addError(dateValidator.validate(dto.getBirthDate()));
        result.addError(genderValidator.validate(dto.getGender()));


        log.trace("End profile validation");
        return result;
    }
}
