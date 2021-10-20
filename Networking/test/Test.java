package Networking.test;

import java.util.*;

import Networking.*;

import java.io.IOException;
import java.net.*;
import java.io.*;

public class Test {
    public static void main(String[] args){
        new Test(false);
    }

    Test(boolean send){
        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);

        try {
            ServerSocket ss = new ServerSocket(1332);
            Socket s = send ? ss.accept() : new Socket(sc.nextLine(), 1332);
            new InHandler(new DataInputStream(s.getInputStream()), new OutHandler(new DataOutputStream(s.getOutputStream()), send));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        sc.close();

        while(true);
    }

    void listAllAdresses(){
        Enumeration e;
        try {
            e = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e1) {
            e = null;
            e1.printStackTrace();
        }
        while(e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();
                System.out.println(i.getHostAddress());
            }
        }
    }
}
