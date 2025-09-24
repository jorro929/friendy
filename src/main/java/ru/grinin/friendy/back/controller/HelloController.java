package ru.grinin.friendy.back.controller;

import ru.grinin.friendy.back.controller.api.Controller;

import java.util.UUID;

public class HelloController implements Controller {
    @Override
    public String work(String request) {
        return "hello work";
    }

    @Override
    public String save(String request) {
        return "hello save";
    }

    @Override
    public String findById(UUID id) {
        return "hello findById";
    }

    @Override
    public String update(String request) {
        return "hello update";
    }

    @Override
    public String delete(UUID id) {
        return "hello delete";
    }

    @Override
    public String findAll() {
        return "hello findAll";
    }
}
