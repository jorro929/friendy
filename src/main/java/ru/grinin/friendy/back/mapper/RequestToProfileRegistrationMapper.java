package ru.grinin.friendy.back.mapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import ru.grinin.friendy.back.dto.ProfileRegistrationDto;
import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.validator.util.DateValidator;

import java.time.LocalDate;

import static ru.grinin.friendy.back.util.GenderUtils.isGender;
import static ru.grinin.friendy.back.util.StringUtils.isBlank;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestToProfileRegistrationMapper implements Mapper<HttpServletRequest, ProfileRegistrationDto>{

    @Getter
    private static final RequestToProfileRegistrationMapper INSTANCE = new RequestToProfileRegistrationMapper();;

    @Override
    public ProfileRegistrationDto mapTo(HttpServletRequest req) {

        var session = req.getSession();

        ProfileRegistrationDto dto = (ProfileRegistrationDto)session.getAttribute("profile");

        dto.setName(req.getParameter("name"));
        dto.setSurname(req.getParameter("surname"));


        dto.setBirthDate(req.getParameter("date-birth"));

        if(!isBlank(req.getParameter("about"))){
            dto.setAbout(req.getParameter("about"));
        }

        if(!isBlank(req.getParameter("gender")) &&
        isGender(req.getParameter("gender"))){
            dto.setGender(Gender.valueOf(req.getParameter("gender")));
        }

        return dto;
    }
}
