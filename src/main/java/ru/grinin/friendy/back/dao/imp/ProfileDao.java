package ru.grinin.friendy.back.dao.imp;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grinin.friendy.back.dao.api.AbstractProfileDao;
import ru.grinin.friendy.back.model.Profile;
import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.model.supportclass.ProfileStatus;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;


public class ProfileDao implements AbstractProfileDao {

    private static final Logger log = LoggerFactory.getLogger(ProfileDao.class);

    private final ConcurrentMap<UUID, Profile> storage;

    @Getter
    private static final ProfileDao INSTANCE = new ProfileDao();

    private ProfileDao() {
        this.storage = new ConcurrentHashMap<>();
        log.trace("Set first profile");
        storage.put(UUID.fromString("70e4e64a-fc05-441a-88f9-00363e46a3be"),
                new Profile("Vasiliy", "Grinin", "grinin@mail.com", "12344321", LocalDate.parse("2002-06-09"), "I am Java developer", Gender.MALE, ProfileStatus.ACTIVE));
        storage.get(UUID.fromString("70e4e64a-fc05-441a-88f9-00363e46a3be")).setId(UUID.fromString("70e4e64a-fc05-441a-88f9-00363e46a3be"));
        storage.put(UUID.fromString("d093c0bd-5b86-4799-a0a5-54120572c3b4"),
                new Profile("Filip", "Tityshin", "jopa@example.ru", "12345678", LocalDate.parse("1998-03-23"), "I am cool man", Gender.MALE, ProfileStatus.BLOCKED));
        storage.get(UUID.fromString("d093c0bd-5b86-4799-a0a5-54120572c3b4")).setId(UUID.fromString("d093c0bd-5b86-4799-a0a5-54120572c3b4"));
        storage.put(UUID.fromString("8ae88986-264c-4db4-ac6b-607ac6406f1e"),
                new Profile("Larisa", "Dvorina", "kyricha@bk.ru", "87654321", LocalDate.parse("1992-08-31"), "I am QA", Gender.FEMALE, ProfileStatus.ACTIVE));
        storage.get(UUID.fromString("8ae88986-264c-4db4-ac6b-607ac6406f1e")).setId(UUID.fromString("8ae88986-264c-4db4-ac6b-607ac6406f1e"));

    }

    public UUID save(Profile profile){
        UUID id = generateId();
        profile.setId(id);
        storage.put(id, profile);
        log.debug("Profile was successful save");
        return profile.getId();
    }

    public Optional<Profile> findById(UUID id){
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public boolean delete(UUID id) {
        if(!storage.containsKey(id)) return false;
        storage.remove(id);
        log.debug("Profile was successful delete");
        return true;
    }

    @Override
    public void update(UUID id, Profile profile) {
        if(!storage.containsKey(id)) return;
        storage.put(id, profile);
        log.debug("Profile was successful update");
    }

    @Override
    public List<Profile> findAll() {
        return  new ArrayList<>(storage.values());
    }

    private UUID generateId(){
        UUID id;
        do{
            id = UUID.randomUUID();
        }while (storage.containsKey(id));
        return id;
    }


    @Override
    public Set<String> findAlLEmails() {
        return storage.values().stream().map(Profile::getEmail).collect(Collectors.toSet());
    }
}
