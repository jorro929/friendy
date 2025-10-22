package ru.grinin.friendy.back.service.imp;

import lombok.Getter;
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

public class RegistrationService implements AbstractRegistrationService {

    @Getter
    private static final RegistrationService INSTANCE = new RegistrationService(ProfileDao.getINSTANCE());

    private RegistrationService(ProfileDao dao) {
        this.dao = dao;
    }



    private final ProfileDao dao;

    private final ProfileRegistrationMapper registrationMapper = ProfileRegistrationMapper.getINSTANCE();
    @Override
    public UUID save(ProfileRegistrationDto dto) throws EmailCollisionException {
        Set<String> emails = dao.findAlLEmails();
        if(emails.contains(dto.getEmail())) throw  new EmailCollisionException(dto.getEmail());
        Profile profile = registrationMapper.mapTo(dto);
        profile.setStatus(ProfileStatus.ACTIVE);
        return dao.save(profile);
    }

}
