package ru.grinin.friendy.back.mapper;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.grinin.friendy.back.dto.ProfilePutDto;
import ru.grinin.friendy.back.model.Profile;
import ru.grinin.friendy.back.model.supportclass.ProfileStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfilePutDtoMapper implements Mapper <ProfilePutDto, Profile> {
    @Getter
    private static final ProfilePutDtoMapper INSTANCE = new ProfilePutDtoMapper();;

    @Override
    public Profile mapTo(ProfilePutDto dto) {
         Profile profile = new Profile(
                dto.getName(),
                dto.getSurname(),
                dto.getEmail(),
                dto.getPassword() == null ? "" : dto.getPassword(),
                dto.getBirthDate(),
                dto.getAbout(),
                dto.getGender(),
                ProfileStatus.ACTIVE);
         if(dto.getId() != null) profile.setId(dto.getId());
         return profile;
    }
}
