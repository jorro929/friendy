package ru.grinin.friendy.back.exception;

public class NotFoundException extends Exception{
    public NotFoundException(String whatIsNotFound) {
        super(whatIsNotFound + " with this id is not found");
    }
}
