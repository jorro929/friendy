package ru.grinin.friendy.back.exception;

import ru.grinin.friendy.back.model.Profile;

public class ProfileNotFoundException extends NotFoundException{
    public ProfileNotFoundException() {
        super("This profile");
    }
}
