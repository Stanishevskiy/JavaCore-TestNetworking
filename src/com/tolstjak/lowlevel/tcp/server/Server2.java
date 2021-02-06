package com.tolstjak.lowlevel.tcp.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server2 {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {

            while (true) {
                EchoServer echoServer = new EchoServer(serverSocket.accept());
                echoServer.start();
            }

        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
