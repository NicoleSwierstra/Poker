package Networking.test;

import java.util.*;
import java.io.IOException;
import java.net.*;
import java.io.*;

public class Test {
    public static void main(String[] args){
        new Test();
    }

    Test(){
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

        Scanner sc = new Scanner(System.in);

        String ip = sc.nextLine();

        try {
            Socket s = new Socket(ip, 3333);
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            
            String message = sc.nextLine();
            message += '\0';
            dout.writeBytes(message);

            String recieve = new String();
            char c;
            while((c = (char)din.readByte()) != '\0'){
                recieve += c;
            }
            System.out.println(recieve);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
