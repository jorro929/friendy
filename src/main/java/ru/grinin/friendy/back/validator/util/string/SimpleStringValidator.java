package ru.grinin.friendy.back.validator.util.string;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.validator.Validator;
import ru.grinin.friendy.back.validator.ValidatorError;
import ru.grinin.friendy.back.validator.ValidatorResult;

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
    public ValidatorResult validate(String string){
        ValidatorResult result = new ValidatorResult();
        log.trace("Start " + name + " validation");
        if(string == null || string.isBlank()){
            result.addError(new ValidatorError(code, name + " is not valid"));
        }
        log.trace("End " + name + " validation");
        return result;
    }
}
