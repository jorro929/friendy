package ru.grinin.friendy.back.controller.api;



import java.util.UUID;

public interface Controller {
    public String work(String request);

    public String save(String request);

    public String findById(UUID id);

    public String update(String request);

    public String delete(UUID id);
    public String findAll();
}
