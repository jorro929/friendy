package ru.grinin.friendy.back.service.api;

import ru.grinin.friendy.back.dto.ProfileGetDto;
import ru.grinin.friendy.back.dto.ProfilePutDto;
import ru.grinin.friendy.back.dto.ProfileStatusDto;
import ru.grinin.friendy.back.dto.ProfileUpdateEmailDto;
import ru.grinin.friendy.back.exception.EmailCollisionException;
import ru.grinin.friendy.back.exception.ProfileNotFoundException;
import ru.grinin.friendy.back.exception.ValidException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileService {

    Optional<ProfileGetDto> findById(UUID id);

    boolean delete(UUID id);

    void update(ProfilePutDto dto) throws ProfileNotFoundException, ValidException;

    void updateEmail(ProfileUpdateEmailDto dto) throws ProfileNotFoundException, EmailCollisionException, ValidException;

    void updateStatus(ProfileStatusDto dto) throws ProfileNotFoundException, ValidException;

    List<ProfileGetDto> findAll();
}
