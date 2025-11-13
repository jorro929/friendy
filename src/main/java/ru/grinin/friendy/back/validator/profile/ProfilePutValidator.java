package ru.grinin.friendy.back.validator.profile;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.dto.ProfilePutDto;
import ru.grinin.friendy.back.validator.ValidationResult;
import ru.grinin.friendy.back.validator.Validator;
import ru.grinin.friendy.back.validator.util.DateValidator;
import ru.grinin.friendy.back.validator.util.GenderValidator;
import ru.grinin.friendy.back.validator.util.IdValidator;
import ru.grinin.friendy.back.validator.util.string.NameValidator;
import ru.grinin.friendy.back.validator.util.string.SurnameValidator;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfilePutValidator implements Validator<ProfilePutDto> {

    @Getter
    private static final ProfilePutValidator INSTANCE = new ProfilePutValidator();
    private final IdValidator idValidator = IdValidator.getINSTANCE();
    private final NameValidator nameValidator = new NameValidator();
    private final SurnameValidator surnameValidator = new SurnameValidator();
    private final GenderValidator genderValidator = GenderValidator.getINSTANCE();
    private final DateValidator dateValidator = DateValidator.getINSTANCE();

    @Override
    public ValidationResult validate(ProfilePutDto dto) {
        ValidationResult result = new ValidationResult();

        log.trace("Start profile validation");

        if (dto == null) {
            result.addError(201, "Profile is not be null");
        }else {
            result.addError(idValidator.validate(dto.getId()));
            result.addError(nameValidator.validate(dto.getName()));
            result.addError(surnameValidator.validate(dto.getSurname()));
            result.addError(dateValidator.validate(dto.getBirthDate()));
            result.addError(genderValidator.validate(dto.getGender()));
        }

        log.trace("End profile validation");
        return result;
    }
}