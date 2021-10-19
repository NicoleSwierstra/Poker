package Networking;

import java.io.*;
import java.util.*;

import utils.ByteUtils;

//at this time this class is a test that checks the ping between two systems
public class OutHandler {
    DataOutputStream outstream;
    Timer pingTimer;

    //the world's lamest constructor
    public OutHandler(DataOutputStream dos){
        outstream = dos;
        //addPingTimer();
        long expectedTime = System.currentTimeMillis() + 1000;
        do{
            expectedTime = System.currentTimeMillis() + 1000;
            try {
                sendPing();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }while(System.currentTimeMillis() < expectedTime);
    }

    //adds the ping timer
    public void addPingTimer(){
        pingTimer = new Timer();
        pingTimer.schedule(new TimerTask(){
            public void run(){
                try {
                    sendPing();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
    }

    //sends a ping
    void sendPing() throws IOException {
        System.out.println("epic");
        outstream.write(0x00); //init
        outstream.write(0xFF); //init 2
        outstream.write(0x01); //this is a clock pulse
        outstream.write(ByteUtils.longToBytes(System.currentTimeMillis()));
        outstream.flush();
    }
}
