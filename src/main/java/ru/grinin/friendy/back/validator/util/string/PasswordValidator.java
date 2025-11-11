package ru.grinin.friendy.back.validator.util.string;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordValidator extends SimpleStringValidator {
    public PasswordValidator() {
        super(103, "Password");
    }
}
