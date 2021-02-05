package com.tolstjak.tcp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoServer extends Thread {

    Socket socket;

    public EchoServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client Connected");
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String echoString = input.readLine();
                if (echoString.equals("exit")) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " Client said: " + echoString);
                output.println("Echo from server: " + echoString);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
