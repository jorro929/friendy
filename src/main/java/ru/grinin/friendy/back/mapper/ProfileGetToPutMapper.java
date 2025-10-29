package ru.grinin.friendy.back.mapper;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.grinin.friendy.back.dto.ProfileGetDto;
import ru.grinin.friendy.back.dto.ProfilePutDto;
import ru.grinin.friendy.back.model.Profile;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileGetToPutMapper implements Mapper <ProfileGetDto, ProfilePutDto>{

    @Getter
    private static final ProfileGetToPutMapper INSTANCE = new ProfileGetToPutMapper();;

    @Override
    public ProfilePutDto mapTo(ProfileGetDto dto) {
        return new ProfilePutDto(dto.getName(),
                dto.getSurname(),
                dto.getEmail(),
                dto.getBirthDate(),
                dto.getAbout(),
                dto.getGender());
    }
}
