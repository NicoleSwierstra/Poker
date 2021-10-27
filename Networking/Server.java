package Networking;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import java.io.*;


public class Server {
    
    class ServerThread implements Runnable {
        Socket socket;
        ServerSocket sSocket;
        InHandler inHandler;
        OutHandler outHandler;
        boolean accepted = false;
        boolean working = true;

        ServerThread(ServerSocket ss){
            sSocket = ss;
            new Thread(this).start();
        }

        public void run(){
            try {
                socket = sSocket.accept();
                accepted = true;
                outHandler = new OutHandler(new DataOutputStream(socket.getOutputStream()), true);
                inHandler = new InHandler(new DataInputStream(socket.getInputStream()), outHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //TODO: figure out this mess
            while(working){

            }
        }
    }

    ServerSocket sSocket;
    List<ServerThread> connections;
    boolean PlayerSelect;

    Server(){
        connections = new ArrayList<ServerThread>();
        try {
            sSocket = new ServerSocket(1332);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Adds players until the boolean player select is labeled false
    public void run(){
        while(PlayerSelect){
            while(!connections.get(connections.size()-1).accepted && PlayerSelect);
            if(!PlayerSelect) break;
            connections.add(new ServerThread(sSocket));
        }
        connections.remove(connections.size()-1);
    }

    public void delete() {
        for(ServerThread st : connections){
            st.working = false;
        }
        connections.clear();
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
