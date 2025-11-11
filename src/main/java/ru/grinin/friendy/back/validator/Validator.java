package ru.grinin.friendy.back.validator;

import jakarta.mail.internet.AddressException;

public interface Validator <T>{

    ValidatorResult validate(T object);
}
