package ru.grinin.friendy.back.validator.util.string;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SurnameValidator extends SimpleStringValidator {
    public SurnameValidator() {
        super(105, "Surname");
    }
}
