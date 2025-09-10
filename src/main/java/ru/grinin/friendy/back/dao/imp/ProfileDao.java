package ru.grinin.friendy.back.dao.imp;

import ru.grinin.friendy.back.dao.api.AbstractProfileDao;
import ru.grinin.friendy.back.model.Profile;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class ProfileDao implements AbstractProfileDao {

    private final ConcurrentMap<UUID, Profile> storage;



    public ProfileDao() {
        this.storage = new ConcurrentHashMap<>();

    }

    public Profile save(Profile profile){
        UUID id = generateId();
        profile.setId(id);
        storage.put(id, profile);
        return profile;
    }

    public Optional<Profile> findById(UUID id){
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public boolean delete(UUID id) {
        if(!storage.containsKey(id)) return false;
        storage.remove(id);
        return true;
    }

    @Override
    public void update(UUID id, Profile profile) {
        if(!storage.containsKey(id)) return;
        storage.put(id, profile);
    }

    @Override
    public List<Profile> findAll() {
        return  new ArrayList<>(storage.values());
    }

    private UUID generateId(){
        UUID id;
        do{
            id = UUID.randomUUID();
        }while (!storage.containsKey(id));
        return id;
    }




}
