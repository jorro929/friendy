package ru.grinin.friendy.back.controller;

import lombok.RequiredArgsConstructor;
import ru.grinin.friendy.back.controller.api.Controller;
import ru.grinin.friendy.back.model.Profile;
import ru.grinin.friendy.back.service.imp.ProfileService;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ProfileController implements Controller{

    private final ProfileService service;

    public String work(String request){
        String response = "response";
        return response;
    }

    public String save(String request){

        String[] params = request.split(", ");
        if(params.length != 4) return "Bad request";
        Profile profile = new Profile();
        profile.setEmail(params[0]);
        profile.setName(params[1]);
        profile.setSurname(params[2]);
        profile.setAbout(params[3]);
        service.save(profile);


        return profile.toString();
    }

    public String findById(UUID id){
        if(service.findById(id).isEmpty()) return "Not profile";
        return service.findById(id).get().toString();
    }

    public String update(String request){
        String[] params = request.split(", ");
        if(params.length != 5) return "Bad request";
        Profile profile = new Profile();

        if(findById(UUID.fromString(params[0])).isEmpty()) return "Not profile";

        profile.setId(UUID.fromString(params[0]));
        profile.setEmail(params[1]);
        profile.setName(params[2]);
        profile.setSurname(params[3]);
        profile.setAbout(params[4]);
        service.update(profile.getId(), profile);

        return "successful";
    }

    public String delete(UUID id){
        if(service.delete(id)) return "successful";
        return "fail";
    }

    public String findAll(){
        return service.findAll().toString();
    }
}
