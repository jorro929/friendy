package ru.grinin.friendy.back.validator.util.string;

import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.validator.Validator;
import ru.grinin.friendy.back.validator.ValidationError;
import ru.grinin.friendy.back.validator.ValidationResult;

@Slf4j
public class SimpleStringValidator implements Validator<String>{


    public SimpleStringValidator() {
        this(102, "String");
    }

    private final int code;

    private final String name;

    protected SimpleStringValidator(int code, String name){
        this.code = code;
        this.name = name;
    }

    @Override
    public ValidationResult validate(String string){
        ValidationResult result = new ValidationResult();
        log.trace("Start " + name + " validation");
        if(string == null || string.isBlank()){
            result.addError(new ValidationError(code, name + " is not valid"));
        }
        log.trace("End " + name + " validation");
        return result;
    }
}
