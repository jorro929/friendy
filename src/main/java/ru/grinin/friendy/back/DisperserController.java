package ru.grinin.friendy.back;

import ru.grinin.friendy.back.controller.api.Controller;

import java.util.UUID;

public class DisperserController {

    public String dispense(Controller controller, IncomingHttpRequest request) {
        try {


            switch (request.method()) {
                case GET -> {
                    if (request.params().containsKey("id"))
                        return controller.findById(UUID.fromString(request.params().get("id")));
                    return controller.findAll();
                }
                case POST -> {
                    return controller.save(request.body());
                }
                case PUT -> {
                    return controller.update(request.body());
                }
                case DELETE -> {
                    return controller.delete(UUID.fromString(request.params().get("id")));
                }
                case null, default -> {
                    return "bad request";
                }
            }
        }catch (Exception e){
            return "bad request";
        }
    }
}
