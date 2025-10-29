package ru.grinin.friendy.back.mapper;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.grinin.friendy.back.dto.ProfileGetDto;
import ru.grinin.friendy.back.model.Profile;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileGetDtoMapper implements Mapper <Profile, ProfileGetDto> {

    @Getter
    private static final ProfileGetDtoMapper INSTANCE = new ProfileGetDtoMapper();;

    @Override
    public ProfileGetDto mapTo(Profile profile) {
        return new ProfileGetDto(profile.getId(),
                profile.getName(),
                profile.getSurname(),
                profile.getEmail(),
                profile.getBirthDate(),
                Math.toIntExact(ChronoUnit.YEARS.between(profile.getBirthDate(), LocalDate.now())),
                profile.getAbout(),
                profile.getGender(),
                profile.getStatus());
    }
}
