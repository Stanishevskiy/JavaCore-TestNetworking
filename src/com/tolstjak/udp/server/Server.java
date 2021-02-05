package com.tolstjak.udp.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {

    public static void main(String[] args) {

        try (DatagramSocket datagramSocket = new DatagramSocket(5000)) {

            while (true) {
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(packet);
                System.out.println("Text received is: " + new String(buffer, 0, buffer.length));

                String returnString = "echo: " + new String(buffer, 0, packet.getLength());
                byte[] buffer2 = returnString.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buffer2, buffer2.length, address, port);
                datagramSocket.send(packet);
            }

        } catch (SocketException e) {
            System.out.println("Socket Exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
