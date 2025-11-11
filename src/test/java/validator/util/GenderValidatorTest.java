package validator.util;

import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.validator.util.DateValidator;
import ru.grinin.friendy.back.validator.util.GenderValidator;

import java.util.Locale;

public class GenderValidatorTest {
    public static void main(String[] args) {

        System.out.println(GenderValidator.getINSTANCE().validate(Gender.MALE).getErrors());
    }
}
