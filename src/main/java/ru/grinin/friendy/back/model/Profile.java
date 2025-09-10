package ru.grinin.friendy.back.model;

import lombok.*;
import ru.grinin.friendy.back.model.supportclass.Identifiable;

import java.util.UUID;

@Setter
@Getter
@ToString
@EqualsAndHashCode(of = {"email", "name", "surname"})
@NoArgsConstructor
public class Profile implements Identifiable<UUID> {

    private UUID id;

    private String email;

    private String name;

    private String surname;

    private String about;

}
