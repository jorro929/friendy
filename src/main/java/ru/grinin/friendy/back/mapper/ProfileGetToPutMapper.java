package ru.grinin.friendy.back.mapper;

import lombok.Getter;
import ru.grinin.friendy.back.dto.ProfileGetDto;
import ru.grinin.friendy.back.dto.ProfilePutDto;
import ru.grinin.friendy.back.model.Profile;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProfileGetToPutMapper implements Mapper <ProfileGetDto, ProfilePutDto>{

    @Getter
    private static final ProfileGetToPutMapper INSTANCE = new ProfileGetToPutMapper();;

    private ProfileGetToPutMapper(){
    }
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
