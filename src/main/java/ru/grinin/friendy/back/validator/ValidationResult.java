package ru.grinin.friendy.back.validator;


import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    private final List<ValidationError> errors;

    public ValidationResult() {
        this.errors = new ArrayList<>();
    }

    public void addError(ValidationError error) {
        errors.add(error);
    }

    public void addError(int errorCode, String messageError) {
        addError(new ValidationError(errorCode, messageError));
    }

    public void addError(ValidationResult result) {
        result.getErrors().forEach(this::addError);
    }

    public List<ValidationError> getErrors(){
        return List.copyOf(errors);
    }

    public boolean isValid(){
        return errors.isEmpty();
    }

}
