package ru.grinin.friendy.back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class FriendyBackRunner {
    public static void main(String[] args) throws IOException {

        try(Socket socket = new Socket("localhost", 8080);
            DataInputStream rsStream = new DataInputStream(socket.getInputStream());
            DataOutputStream rqStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            ){

            while (scanner.hasNextLine()){
                String request = scanner.nextLine();
                rqStream.writeUTF(request);
                String response = rsStream.readUTF();
                System.out.println(response);
            }
        }
    }
}
