package ru.grinin.friendy.back.mapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.grinin.friendy.back.dto.ProfilePutDto;
import ru.grinin.friendy.back.dto.ProfileRegistrationDto;
import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.model.supportclass.ProfileStatus;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestToProfileRegistrationMapper implements Mapper<HttpServletRequest, ProfileRegistrationDto>{

    @Getter
    private static final RequestToProfileRegistrationMapper INSTANCE = new RequestToProfileRegistrationMapper();;

    @Override
    public ProfileRegistrationDto mapTo(HttpServletRequest req) {
        return new ProfileRegistrationDto(req.getParameter("email"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("surname"),
                LocalDate.parse(req.getParameter("date-birth")),
                req.getParameter("about"),
                Gender.valueOf(req.getParameter("gender")));
    }
}
