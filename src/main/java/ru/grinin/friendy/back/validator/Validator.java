package ru.grinin.friendy.back.validator;

public interface Validator <T>{

    ValidationResult validate(T object);
}
