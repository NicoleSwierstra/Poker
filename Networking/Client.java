package Networking;

import java.io.IOException;
import java.net.*;
import java.io.*;

public class Client {
    Socket socket;
    InHandler inHandler;
    OutHandler outHandler;

    Client(String ip){
        try {
            System.out.println("WAITING FOR SERVER CONNECTION");
            socket = new Socket(ip, 1332);
            System.out.println("CLIENT CONNECTED TO SERVER");
            outHandler = new OutHandler(new DataOutputStream(socket.getOutputStream()), false);
            inHandler = new InHandler(new DataInputStream(socket.getInputStream()), outHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
