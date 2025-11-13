package ru.grinin.friendy.back.dto;

import ru.grinin.friendy.back.model.supportclass.ProfileStatus;

import java.util.UUID;

public record ProfileUpdateEmailDto (UUID id,
                                    String email){
}
