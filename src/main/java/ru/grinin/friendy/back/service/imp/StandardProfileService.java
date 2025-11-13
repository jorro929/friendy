package ru.grinin.friendy.back.service.imp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.dao.imp.ProfileDao;
import ru.grinin.friendy.back.dto.ProfileGetDto;
import ru.grinin.friendy.back.dto.ProfilePutDto;
import ru.grinin.friendy.back.dto.ProfileStatusDto;
import ru.grinin.friendy.back.dto.ProfileUpdateEmailDto;
import ru.grinin.friendy.back.exception.EmailCollisionException;
import ru.grinin.friendy.back.exception.ProfileNotFoundException;
import ru.grinin.friendy.back.exception.ValidException;
import ru.grinin.friendy.back.mapper.ProfileGetDtoMapper;
import ru.grinin.friendy.back.mapper.ProfilePutDtoMapper;
import ru.grinin.friendy.back.model.Profile;
import ru.grinin.friendy.back.service.api.ProfileService;
import ru.grinin.friendy.back.validator.profile.ProfilePutValidator;
import ru.grinin.friendy.back.validator.profile.ProfileUpdateEmailValidator;
import ru.grinin.friendy.back.validator.profile.ProfileUpdateStatusValidator;
import ru.grinin.friendy.back.validator.util.EmailValidator;

import java.util.*;

import static ru.grinin.friendy.back.util.Validation.validate;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StandardProfileService implements ProfileService {

    @Getter
    private static final StandardProfileService INSTANCE = new StandardProfileService();

    private final ProfileDao dao = ProfileDao.getINSTANCE();

    private final ProfileGetDtoMapper getDtoMapper = ProfileGetDtoMapper.getINSTANCE();
    private final ProfilePutDtoMapper putDtoMapper = ProfilePutDtoMapper.getINSTANCE();

    private final ProfilePutValidator putValidator = ProfilePutValidator.getINSTANCE();
    private final ProfileUpdateStatusValidator statusValidator = ProfileUpdateStatusValidator.getINSTANCE();
    private final ProfileUpdateEmailValidator emailValidator = ProfileUpdateEmailValidator.getINSTANCE();


    @Override
    public Optional<ProfileGetDto> findById(UUID id) {
        if (id == null) return Optional.empty();
        log.debug("id is not null");
        return dao.findById(id).map(getDtoMapper::mapTo);
    }

    @Override
    public boolean delete(UUID id) {
        if (id == null) return false;
        log.debug("id is not null");
        return dao.delete(id);
    }

    @Override
    public void update(ProfilePutDto dto) throws ProfileNotFoundException, ValidException {
        validate(putValidator, dto);

        Profile profile = getProfile(dto.getId());
        Profile newProfile = putDtoMapper.mapTo(dto);
        profile.setName(newProfile.getName());
        profile.setSurname(newProfile.getSurname());
        profile.setBirthDate(newProfile.getBirthDate());
        profile.setGender(newProfile.getGender());

        dao.update(dto.getId(), profile);

    }

    @Override
    public void updateStatus(ProfileStatusDto dto) throws ProfileNotFoundException, ValidException {
        validate(statusValidator, dto);
        Profile profile = getProfile(dto.id());
        profile.setStatus(dto.status());
        dao.update(profile.getId(), profile);
    }

    @Override
    public void updateEmail(ProfileUpdateEmailDto dto) throws ProfileNotFoundException, EmailCollisionException, ValidException {
        validate(emailValidator, dto);

        Profile profile = getProfile(dto.id());
        checkProfile(profile.getEmail(), dto.email());
        log.debug("{} is not busy", dto.email());
        profile.setEmail(dto.email());
        dao.update(profile.getId(), profile);

    }

    @Override
    public List<ProfileGetDto> findAll() {
        return dao.findAll().stream().map(getDtoMapper::mapTo).toList();
    }

    private void checkProfile(String oldEmail, String newEmail) throws EmailCollisionException {
        if (newEmail.isBlank()) return;
        if (!Objects.equals(oldEmail, newEmail)) {
            Set<String> set = dao.findAlLEmails();
            if (set.contains(newEmail)) {
                log.debug("{} is busy", newEmail);
                throw new EmailCollisionException(newEmail);
            }
        }
    }

    private Profile getProfile(UUID id) throws ProfileNotFoundException {
        Profile profile =  dao.findById(id).orElseThrow(ProfileNotFoundException::new);
        log.debug("Profile exists");
        return profile;
    }

}
