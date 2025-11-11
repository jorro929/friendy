package validator.util;

import ru.grinin.friendy.back.dto.ProfileRegistrationDto;
import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.validator.profile.ProfileRegistrationValidator;

import java.time.LocalDate;

public class ProfileRegistrationValidatorTest {
    public static void main(String[] args) {
        ProfileRegistrationDto dto = new ProfileRegistrationDto("erae", " ", " ", " ", LocalDate.now().toString(), " ", Gender.MALE);

        System.out.println(ProfileRegistrationValidator.getINSTANCE().validate(dto).getErrors());
    }
}
