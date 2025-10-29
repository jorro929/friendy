package ru.grinin.friendy.back.mapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.grinin.friendy.back.dto.ProfilePutDto;
import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.model.supportclass.ProfileStatus;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestToProfilePutMapper implements Mapper<HttpServletRequest, ProfilePutDto>{

    @Getter
    private static final RequestToProfilePutMapper INSTANCE = new RequestToProfilePutMapper();;

    @Override
    public ProfilePutDto mapTo(HttpServletRequest req) {
        ProfilePutDto dto = new ProfilePutDto(req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("email"),
                LocalDate.parse(req.getParameter("date-birth")),
                req.getParameter("about"),
                Gender.valueOf(req.getParameter("gender")));
        dto.setId(UUID.fromString(req.getParameter("id")));
        return dto;
    }
}
