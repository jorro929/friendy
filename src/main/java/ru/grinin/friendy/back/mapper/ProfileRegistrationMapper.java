package ru.grinin.friendy.back.mapper;

import lombok.Getter;
import ru.grinin.friendy.back.dto.ProfileRegistrationDto;
import ru.grinin.friendy.back.model.Profile;
import ru.grinin.friendy.back.model.supportclass.ProfileStatus;

public class ProfileRegistrationMapper implements Mapper<ProfileRegistrationDto, Profile>{

    @Getter
    private static final ProfileRegistrationMapper INSTANCE = new ProfileRegistrationMapper();;

    private ProfileRegistrationMapper(){
    }
    @Override
    public Profile mapTo(ProfileRegistrationDto dto) {
        return new Profile(dto.getName(),
                dto.getSurname(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getBirthDate(),
                dto.getAbout(),
                dto.getGender(),
                ProfileStatus.ACTIVE);
    }
}
