package ru.grinin.friendy.back.service.api;

import ru.grinin.friendy.back.dto.ProfileRegistrationDto;
import ru.grinin.friendy.back.exception.EmailCollisionException;
import ru.grinin.friendy.back.exception.ValidException;

import java.util.UUID;

public interface RegistrationService {

    UUID save(ProfileRegistrationDto dto) throws EmailCollisionException, ValidException;

}
