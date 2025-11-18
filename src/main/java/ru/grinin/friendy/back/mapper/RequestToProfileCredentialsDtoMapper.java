package ru.grinin.friendy.back.mapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.grinin.friendy.back.dto.ProfileUpdateCredentialsDto;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestToProfileCredentialsDtoMapper implements Mapper <HttpServletRequest, ProfileUpdateCredentialsDto> {

    @Getter
    private static final RequestToProfileCredentialsDtoMapper INSTANCE = new RequestToProfileCredentialsDtoMapper();;

    @Override
    public ProfileUpdateCredentialsDto mapTo(HttpServletRequest req) {
        return new ProfileUpdateCredentialsDto(UUID.fromString(req.getParameter("id")),
                req.getParameter("email"),
                req.getParameter("password"));
    }
}
