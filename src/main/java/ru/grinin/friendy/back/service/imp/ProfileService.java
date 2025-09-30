package ru.grinin.friendy.back.service.imp;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import ru.grinin.friendy.back.dao.imp.ProfileDao;
import ru.grinin.friendy.back.model.Profile;
import ru.grinin.friendy.back.service.api.AbstractProfileService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProfileService implements AbstractProfileService {

    private static ProfileService service;

    private ProfileService(ProfileDao dao){
        this.dao = dao;
    }

    public static ProfileService getService(){
        if(service == null){
            service = new ProfileService(new ProfileDao());
        }
        return service;
    }

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
