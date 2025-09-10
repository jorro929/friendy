package ru.grinin.friendy.back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class FriendyBackServerRunner {
    public static void main(String[] args) throws IOException {

        try(ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            DataOutputStream rsStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream rqStream = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
        ){
            String request = rqStream.readUTF();

            while (!"stop".equals(request)){
                System.out.println("client write: " + request);
                String response = scanner.nextLine();

                rsStream.writeUTF(response);
                request = rqStream.readUTF();
            }

        }
    }
}
