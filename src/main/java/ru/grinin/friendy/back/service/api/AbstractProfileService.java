package ru.grinin.friendy.back.service.api;

import ru.grinin.friendy.back.model.Profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AbstractProfileService {

    Profile save(Profile profile);

    Optional<Profile> findById(UUID id);

    boolean delete(UUID id);

    void update(UUID id, Profile profile);

    List<Profile> findAll();
}
