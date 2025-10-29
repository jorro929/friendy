package ru.grinin.friendy.back.service.imp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grinin.friendy.back.dao.imp.ProfileDao;
import ru.grinin.friendy.back.dto.ProfileGetDto;
import ru.grinin.friendy.back.dto.ProfilePutDto;
import ru.grinin.friendy.back.dto.ProfileStatusDto;
import ru.grinin.friendy.back.exception.EmailCollisionException;
import ru.grinin.friendy.back.exception.NotFoundException;
import ru.grinin.friendy.back.exception.ProfileNotFoundException;
import ru.grinin.friendy.back.mapper.ProfileGetDtoMapper;
import ru.grinin.friendy.back.mapper.ProfilePutDtoMapper;
import ru.grinin.friendy.back.model.Profile;
import ru.grinin.friendy.back.service.api.AbstractProfileService;

import java.util.*;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileService implements AbstractProfileService {

    @Getter
    private static final ProfileService INSTANCE = new ProfileService();

    private final ProfileDao dao = ProfileDao.getINSTANCE();

    private final ProfileGetDtoMapper getDtoMapper = ProfileGetDtoMapper.getINSTANCE();
    private final ProfilePutDtoMapper putDtoMapper = ProfilePutDtoMapper.getINSTANCE();


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
    public void update(ProfilePutDto profileDto) throws ProfileNotFoundException, EmailCollisionException {

        Profile oldProfile = dao.findById(profileDto.getId()).orElseThrow(ProfileNotFoundException::new);
        log.debug("Profile exists");
        checkProfile(oldProfile.getEmail(), profileDto.getEmail());
        log.debug("{} is not busy", profileDto.getEmail());

        if(profileDto.getPassword() == null || profileDto.getPassword().isBlank()){
            log.debug("Dto doesn't change password");
            profileDto.setPassword(oldProfile.getPassword());
        }
        Profile newProfile = putDtoMapper.mapTo(profileDto);
        newProfile.setStatus(oldProfile.getStatus());
        dao.update(profileDto.getId(), newProfile);

    }

    @Override
    public void updateStatus(ProfileStatusDto dto) throws ProfileNotFoundException {

        Profile profile = dao.findById(dto.id()).orElseThrow(ProfileNotFoundException::new);
        log.debug("Profile exists");
        profile.setStatus(dto.status());
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
            if (set.contains(newEmail)) throw new EmailCollisionException(newEmail);
        }
    }

}
