package ru.grinin.friendy.back.mapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.grinin.friendy.back.dto.ProfileUpdateEmailDto;
import ru.grinin.friendy.back.model.supportclass.ProfileStatus;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestToProfileEmailDtoMapper implements Mapper <HttpServletRequest, ProfileUpdateEmailDto> {

    @Getter
    private static final RequestToProfileEmailDtoMapper INSTANCE = new RequestToProfileEmailDtoMapper();;

    @Override
    public ProfileUpdateEmailDto mapTo(HttpServletRequest req) {
        return new ProfileUpdateEmailDto(UUID.fromString(req.getParameter("id")),
                req.getParameter("email"));
    }
}
