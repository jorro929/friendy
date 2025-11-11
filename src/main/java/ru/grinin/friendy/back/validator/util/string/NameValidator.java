package ru.grinin.friendy.back.validator.util.string;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NameValidator extends SimpleStringValidator {
    public NameValidator() {
        super(104, "Name");
    }
}
