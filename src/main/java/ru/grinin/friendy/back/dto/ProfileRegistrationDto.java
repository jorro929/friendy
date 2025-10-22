package ru.grinin.friendy.back.dto;

import lombok.*;
import ru.grinin.friendy.back.model.supportclass.Gender;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRegistrationDto {

    private String email;

    private String password;

    private String name;

    private String surname;

    private LocalDate birthDate;

    private String about;

    private Gender gender;


}
