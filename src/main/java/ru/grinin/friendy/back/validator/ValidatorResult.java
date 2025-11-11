package ru.grinin.friendy.back.validator;


import java.util.ArrayList;
import java.util.List;

public class ValidatorResult {

    private final List<ValidatorError> errors;

    public ValidatorResult() {
        this.errors = new ArrayList<>();
    }

    public void addError(ValidatorError error) {
        errors.add(error);
    }

    public void addError(int errorCode, String messageError) {
        addError(new ValidatorError(errorCode, messageError));
    }

    public void addError(ValidatorResult result) {
        result.getErrors().forEach(this::addError);
    }

    public List<ValidatorError> getErrors(){
        return List.copyOf(errors);
    }

    public boolean isValid(){
        return errors.isEmpty();
    }

}
