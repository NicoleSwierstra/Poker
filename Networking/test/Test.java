package Networking.test;

import java.util.*;
import java.io.IOException;
import java.net.*;
import java.io.*;

public class Test {
    public static void main(String[] args){
        new Test(true);
    }

    Test(boolean send){
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

        try {
            String ip = sc.nextLine();
            Socket s = send ? new Socket(ip, 1332) : new ServerSocket(1332).accept();
            if(send) Send(s);
            else Recieve(s, sc);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    void Recieve(Socket s, Scanner sc) throws IOException{
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            
        String message = sc.nextLine();
        message += '\0';
        dout.writeBytes(message);
    }

    void Send(Socket s) throws IOException{
        DataInputStream din = new DataInputStream(s.getInputStream());

        String recieve = new String();
        char c;
        while((c = (char)din.readByte()) != '\0'){
            recieve += c;
        }
        System.out.println(recieve);
    }
}
