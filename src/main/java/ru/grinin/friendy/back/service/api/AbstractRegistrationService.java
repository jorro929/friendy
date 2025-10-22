package ru.grinin.friendy.back.service.api;

import ru.grinin.friendy.back.dto.ProfileGetDto;
import ru.grinin.friendy.back.dto.ProfileRegistrationDto;
import ru.grinin.friendy.back.exception.EmailCollisionException;

import java.util.UUID;

public interface AbstractRegistrationService {

    UUID save(ProfileRegistrationDto dto) throws EmailCollisionException;

}
