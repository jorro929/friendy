package ru.grinin.friendy.back.dto;

import lombok.*;
import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.model.supportclass.ProfileStatus;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProfileGetDto {

    private UUID id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String email;
    @NonNull
    private LocalDate birthDate;
    @NonNull
    private int age;
    @NonNull
    private String about;
    @NonNull
    private Gender gender;
    @NonNull
    private ProfileStatus status;
}
