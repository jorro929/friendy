package ru.grinin.friendy.back.mapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import ru.grinin.friendy.back.dto.ProfileStatusDto;
import ru.grinin.friendy.back.model.supportclass.ProfileStatus;

import java.util.UUID;

public class RequestToProfileStatusDtoMapper implements Mapper <HttpServletRequest, ProfileStatusDto> {

    @Getter
    private static final RequestToProfileStatusDtoMapper INSTANCE = new RequestToProfileStatusDtoMapper();;

    private RequestToProfileStatusDtoMapper(){
    }
    @Override
    public ProfileStatusDto mapTo(HttpServletRequest req) {
        return new ProfileStatusDto(UUID.fromString(req.getParameter("id")),
                ProfileStatus.valueOf(req.getParameter("status")));
    }
}
