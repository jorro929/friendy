package ru.grinin.friendy.back.dao.api;

import ru.grinin.friendy.back.model.Profile;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface AbstractProfileDao {
    UUID save(Profile profile);

    Optional<Profile> findById(UUID id);

    boolean delete(UUID id);

    void update(UUID id, Profile profile);

    List<Profile> findAll();

    Set<String> findAlLEmails();
}
