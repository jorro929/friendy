package ru.grinin.friendy.back.service.imp;

import lombok.RequiredArgsConstructor;
import ru.grinin.friendy.back.dao.imp.ProfileDao;
import ru.grinin.friendy.back.model.Profile;
import ru.grinin.friendy.back.service.api.AbstractProfileService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ProfileService implements AbstractProfileService {

    private final ProfileDao dao;

    public Profile save(Profile profile){
        return dao.save(profile);
    }

    public Optional<Profile> findById(UUID id){
        if(id == null) return Optional.empty();
        return dao.findById(id);
    }

    @Override
    public boolean delete(UUID id) {
        if(id == null) return false;
        return dao.delete(id);
    }

    @Override
    public void update(UUID id, Profile profile) {
        if(id == null || profile == null) return;
        dao.update(id, profile);
    }

    @Override
    public List<Profile> findAll() {
        return dao.findAll();
    }
}
