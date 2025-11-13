package ru.grinin.friendy.back.service.imp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.dao.imp.ProfileDao;
import ru.grinin.friendy.back.dto.ProfileRegistrationDto;
import ru.grinin.friendy.back.exception.EmailCollisionException;
import ru.grinin.friendy.back.exception.ValidException;
import ru.grinin.friendy.back.mapper.ProfileRegistrationMapper;
import ru.grinin.friendy.back.model.Profile;
import ru.grinin.friendy.back.model.supportclass.ProfileStatus;
import ru.grinin.friendy.back.service.api.RegistrationService;
import ru.grinin.friendy.back.validator.ValidationResult;
import ru.grinin.friendy.back.validator.profile.ProfileRegistrationValidator;

import java.util.Set;
import java.util.UUID;

import static ru.grinin.friendy.back.util.Validation.validate;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StandardRegistrationService implements RegistrationService {



    @Getter
    private static final StandardRegistrationService INSTANCE = new StandardRegistrationService();


    private final ProfileDao dao = ProfileDao.getINSTANCE();

    private final ProfileRegistrationMapper registrationMapper = ProfileRegistrationMapper.getINSTANCE();

    private final ProfileRegistrationValidator validator = ProfileRegistrationValidator.getINSTANCE();
    @Override
    public UUID save(ProfileRegistrationDto dto) throws EmailCollisionException, ValidException {
        validate(validator, dto);

        Set<String> emails = dao.findAlLEmails();
        if(emails.contains(dto.getEmail())) throw  new EmailCollisionException(dto.getEmail());

        log.debug("{} not busy with anyone", dto.getEmail());
        Profile profile = registrationMapper.mapTo(dto);
        profile.setStatus(ProfileStatus.ACTIVE);
        return dao.save(profile);
    }

}
