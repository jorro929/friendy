package ru.grinin.friendy.back.service.imp;


import lombok.Getter;

public class HelloService {
    @Getter
    private static final HelloService service = new HelloService();;

    private HelloService(){
    }

    public String save(String hello){
        return "save " + hello;
    }

    public String update(String hello){
        return "update " + hello;
    }

    public String findById(String hello){
        return "findById " + hello;
    }

    public String findAll(){
        return "findAll hello";
    }

    public String delete(String hello){
        return "delete " + hello;
    }
}
