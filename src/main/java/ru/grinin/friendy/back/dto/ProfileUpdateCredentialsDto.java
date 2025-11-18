package ru.grinin.friendy.back.dto;

import java.util.UUID;

public record ProfileUpdateCredentialsDto(UUID id,
                                          String email,
                                          String password) {
}
