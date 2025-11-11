package ru.grinin.friendy.back.util;

import lombok.experimental.UtilityClass;
import ru.grinin.friendy.back.model.supportclass.Gender;

@UtilityClass
public class GenderUtils {

    public static boolean isGender(String gender){
        try{
            Gender.valueOf(gender);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }
}
