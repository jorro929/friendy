package ru.grinin.friendy.back.service.api;

import ru.grinin.friendy.back.dto.ProfileGetDto;
import ru.grinin.friendy.back.dto.ProfilePutDto;
import ru.grinin.friendy.back.dto.ProfileStatusDto;
import ru.grinin.friendy.back.exception.EmailCollisionException;
import ru.grinin.friendy.back.exception.ProfileNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileService {

    Optional<ProfileGetDto> findById(UUID id);

    boolean delete(UUID id);

    void update(ProfilePutDto dto) throws ProfileNotFoundException, EmailCollisionException;

    void updateStatus(ProfileStatusDto dto) throws ProfileNotFoundException;

    List<ProfileGetDto> findAll();
}
