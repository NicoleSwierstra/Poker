package Networking;

import java.io.IOException;
import java.net.*;
import java.io.*;


public class Server {
    ServerSocket sSocket;
    Socket socket;
    InHandler inHandler;
    OutHandler outHandler;

    Server(){
        try {
            sSocket = new ServerSocket(1332);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void init(){
        try {
            System.out.println("WAITING FOR CONNECTION ON " + getIp());
            socket = sSocket.accept();
            System.out.println("CONNECTION ACCEPTED");
            outHandler = new OutHandler(new DataOutputStream(socket.getOutputStream()), true);
            inHandler = new InHandler(new DataInputStream(socket.getInputStream()), outHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getIp(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "NO IP FOUND";
        }
    }
}
