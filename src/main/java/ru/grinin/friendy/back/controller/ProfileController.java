package ru.grinin.friendy.back.controller;

import lombok.RequiredArgsConstructor;
import ru.grinin.friendy.back.service.imp.ProfileService;

@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService service;

    public String work(String request){
        String response = "response";
        return response;
    }
}
