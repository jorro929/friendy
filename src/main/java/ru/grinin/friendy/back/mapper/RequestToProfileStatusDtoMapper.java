package ru.grinin.friendy.back.mapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.grinin.friendy.back.dto.ProfileStatusDto;
import ru.grinin.friendy.back.model.supportclass.ProfileStatus;

import java.util.UUID;

import static ru.grinin.friendy.back.util.StringUtils.isBlank;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestToProfileStatusDtoMapper implements Mapper <HttpServletRequest, ProfileStatusDto> {

    @Getter
    private static final RequestToProfileStatusDtoMapper INSTANCE = new RequestToProfileStatusDtoMapper();;

    @Override
    public ProfileStatusDto mapTo(HttpServletRequest req) {
        ProfileStatus status = isBlank(req.getParameter("status")) ? null : ProfileStatus.valueOf(req.getParameter("status"));
        return new ProfileStatusDto(UUID.fromString(req.getParameter("id")),
                status);
    }
}
