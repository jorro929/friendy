package ru.grinin.friendy.back.service.imp;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HelloService {


    @Getter
    private static final HelloService INSTANCE = new HelloService();;

    public String save(String hello){
        log.trace("hello save");
        return "save " + hello;
    }

    public String update(String hello){
        log.trace("hello update");
        return "update " + hello;
    }

    public String findById(String hello){
        log.trace("hello findById");
        return "findById " + hello;
    }

    public String findAll(){
        log.trace("hello findAll");
        return "findAll hello";
    }

    public String delete(String hello){
        log.trace("hello delete");
        return "delete " + hello;
    }
}
