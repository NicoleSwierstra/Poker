package Networking;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

import utils.ByteUtils;

/** 0x00 0xFF
 *  Packet types
 *  0x00: NULL
 *  0x01: CLOCK / PING
 *  0x02: ADVANCE TURN
 *  0x03: GAME END
 *  0x04: TABLE UPDATE
 */ 


//at this time this class is a test that checks the ping between two systems
public class OutHandler {
    DataOutputStream outstream;
    Timer pingTimer;

    //the world's lamest constructor
    public OutHandler(DataOutputStream dos){
        dos = outstream;
        addPingTimer();
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
        outstream.write(0x00);
        outstream.write(0xFF);
        outstream.write(0x01);
        outstream.write(ByteUtils.longToBytes(System.currentTimeMillis()));
        outstream.flush();
    }
}
