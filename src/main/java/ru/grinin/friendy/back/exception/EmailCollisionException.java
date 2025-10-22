package ru.grinin.friendy.back.exception;

public class EmailCollisionException extends Exception {

    private String email;

    public EmailCollisionException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return "This email: " + email + " is s already busy. Please, write another email!";
    }
}
