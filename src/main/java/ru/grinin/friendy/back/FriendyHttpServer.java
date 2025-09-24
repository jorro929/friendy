package ru.grinin.friendy.back;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsParameters;
import ru.grinin.friendy.back.controller.api.Controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FriendyHttpServer {

    private ExecutorService executorService;

    private final IncomingHttpRequestMapper mapper;

    private final Map<String, Controller> controllers;

    private final DisperserController disperserController;

    public FriendyHttpServer(int poolSize, IncomingHttpRequestMapper mapper, Map<String, Controller> controllers, DisperserController disperserController) {
        this.executorService = Executors.newFixedThreadPool(poolSize);
        this.mapper = mapper;
        this.controllers = controllers;
        this.disperserController = disperserController;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080);) {
            while (true) {
                Socket connection = serverSocket.accept();
                System.out.println("----Client is connection----");
                System.out.println(connection.getInetAddress().getHostAddress());
                executorService.execute(() -> processConnection(connection));
            }
        }

    }

    private void processConnection(Socket connection) {
        try (connection;
             BufferedReader rqReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             DataOutputStream rsStream = new DataOutputStream(connection.getOutputStream());
        ) {

            while (!rqReader.ready()) ;

            StringBuilder builder = new StringBuilder();

            while (rqReader.ready()) {
                builder.append(rqReader.readLine()).append("\n");

                //TODO надо сделать обработчик http запросов и потом прокидывать их в контроллер для направления на обработку
            }
            String request = builder.toString();

            System.out.println(request);

            IncomingHttpRequest completeRequest = mapper.mapRequest(request);
            System.out.println(completeRequest);

            ServerHttpResponse response;

            if (!controllers.containsKey(completeRequest.uri())) response = new ServerHttpResponse(
                    "HTTP/1.1 404 Not found\n".getBytes(),
                    "Content-Type: text/plain\nContent-Length: 0\n\n".getBytes(),
                    "".getBytes()
            );
            else {
                String responseBody = disperserController.dispense(controllers.get(completeRequest.uri()), completeRequest);

                byte[] body = responseBody.getBytes();
                byte[] startLine;
                if (responseBody.equals(responseBody.equalsIgnoreCase("bad request"))) {
                    startLine = "HTTP/1.1 400 bad request\n".getBytes();
                } else {
                    startLine = "HTTP/1.1 200 OK\n".getBytes();
                }
                byte[] headers = "Content-Type: text/plain\nContent-Length: %s\n\n".formatted(body.length).getBytes();

                response = new ServerHttpResponse(startLine, headers, body);
            }

            rsStream.write(response.startLine());
            rsStream.write(response.headers());
            rsStream.write(response.body());


        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread().getName());
            System.out.println("----Client is disconnection----");
        }
    }
}
