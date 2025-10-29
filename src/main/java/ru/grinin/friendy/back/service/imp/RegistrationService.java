package ru.grinin.friendy.back.service.imp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grinin.friendy.back.controller.RegistrationController;
import ru.grinin.friendy.back.dao.imp.ProfileDao;
import ru.grinin.friendy.back.dto.ProfileGetDto;
import ru.grinin.friendy.back.dto.ProfileRegistrationDto;
import ru.grinin.friendy.back.exception.EmailCollisionException;
import ru.grinin.friendy.back.mapper.ProfileRegistrationMapper;
import ru.grinin.friendy.back.model.Profile;
import ru.grinin.friendy.back.model.supportclass.ProfileStatus;
import ru.grinin.friendy.back.service.api.AbstractRegistrationService;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationService implements AbstractRegistrationService {



    @Getter
    private static final RegistrationService INSTANCE = new RegistrationService();


    private final ProfileDao dao = ProfileDao.getINSTANCE();

    private final ProfileRegistrationMapper registrationMapper = ProfileRegistrationMapper.getINSTANCE();
    @Override
    public UUID save(ProfileRegistrationDto dto) throws EmailCollisionException {
        Set<String> emails = dao.findAlLEmails();
        if(emails.contains(dto.getEmail())) throw  new EmailCollisionException(dto.getEmail());
        log.debug("{} not busy with anyone", dto.getEmail());
        Profile profile = registrationMapper.mapTo(dto);
        profile.setStatus(ProfileStatus.ACTIVE);
        return dao.save(profile);
    }

}
