package ru.grinin.friendy.back.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grinin.friendy.back.model.supportclass.Identifiable;

import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode(of = {"email", "name", "surname"})
@NoArgsConstructor
public class Profile implements Identifiable<UUID> {

    private UUID id;

    private String email;

    private String name;

    private String surname;

    private String about;

}
