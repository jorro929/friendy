package ru.grinin.friendy.back;

import ru.grinin.friendy.back.controller.ProfileController;
import ru.grinin.friendy.back.dao.imp.ProfileDao;
import ru.grinin.friendy.back.service.imp.ProfileService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

public class FriendyBackServerRunner {
    public static void main(String[] args) throws IOException {

        ProfileController controller = new ProfileController(new ProfileService(new ProfileDao()));

        try(ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            DataOutputStream rsStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream rqStream = new DataInputStream(socket.getInputStream());
        ){
            String request = rqStream.readUTF();
            String response;

            while (!"stop".equals(request)){

                System.out.println("client write: " + request);

                if(request.split(" ").length < 2){
                    response = "Bad request";
                } else if(request.startsWith("save")){
                    response = controller.save(request.split("save ")[1]);
                } else if (request.startsWith("findById")){
                    response = controller.findById(UUID.fromString(request.split("findById ")[1]));
                } else if (request.startsWith("delete")){
                    response = controller.delete(UUID.fromString(request.split("delete ")[1]));
                } else if (request.startsWith("findAll")){
                    response = controller.findAll();
                } else if (request.startsWith("update")){
                    response = controller.update(request.split("update ")[1]);
                } else{
                    response = "Unsupported commands";
                }
                System.out.println("server write: " + response);


                rsStream.writeUTF(response);
                request = rqStream.readUTF();
            }

        }
    }
}
