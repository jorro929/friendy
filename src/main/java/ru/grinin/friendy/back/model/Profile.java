package ru.grinin.friendy.back.model;

import lombok.*;
import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.model.supportclass.Identifiable;
import ru.grinin.friendy.back.model.supportclass.ProfileStatus;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@ToString
@EqualsAndHashCode(of = {"email", "name", "surname"})
@RequiredArgsConstructor
@NoArgsConstructor
public class Profile implements Identifiable<UUID> {

    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private LocalDate birthDate;

    @NonNull
    private String about;

    @NonNull
    private Gender gender;

    @NonNull
    private ProfileStatus status;

    private String photo;

}
