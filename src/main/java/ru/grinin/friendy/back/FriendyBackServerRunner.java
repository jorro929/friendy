package ru.grinin.friendy.back;

import ru.grinin.friendy.back.controller.HelloController;
import ru.grinin.friendy.back.controller.ProfileController;
import ru.grinin.friendy.back.controller.api.Controller;
import ru.grinin.friendy.back.dao.imp.ProfileDao;
import ru.grinin.friendy.back.service.imp.ProfileService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class FriendyBackServerRunner {
    public static void main(String[] args) throws IOException {

        Controller controller = new ProfileController(new ProfileService(new ProfileDao()));

        Map<String, Controller> controllers = new HashMap<>();
        controllers.put("/", controller);
        controllers.put("/hello", new HelloController());

        FriendyHttpServer server = new FriendyHttpServer(5, new IncomingHttpRequestMapper(), controllers, new DisperserController());
        server.start();

    }
}

