package Networking;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

/** 0x00 0xFF
 *  Packet types
 *  0x00: NULL
 *  0x01: CLOCK / PING
 *  0x02: ADVANCE TURN
 *  0x03: GAME END
 *  0x04: TABLE UPDATE
 */ 


//at this time this class is a test that checks the ping between two systems
public class OutHandler implements Runnable {
    DataOutputStream outstream;

    //the world's lamest constructor
    OutHandler(DataOutputStream dis){
        dis = outstream;
    }

    //checks for new packet in a while loop
    //to be quite honest i have no idea if this will work properly
    public void run(){
        try {
            while(true){
                byte[] packet = outstream.readAllBytes();
                processPacket(new ByteArrayInputStream(packet));
            }
        } catch (IOException e) {

        }
    }

    //processes a packet and checks the first two bytes
    //if any error is recorded, the error is printed to the error stream ig
    void processPacket(ByteArrayInputStream datain) throws IOException{
        if(datain.read() != 0x00 && datain.read() != 0xFF) {
            System.err.println("UNVERIFIED PACKET");
            return;
        }

        int type = datain.read();

        switch(type){
            case 0:
                break;
            case 1:
                //i wanted to get rid of the variable but it's here now for readability
                long servertime = ByteBuffer.wrap(datain.readNBytes(8)).getLong();
                System.out.println(System.currentTimeMillis() - servertime);
                break;
            default:
                System.err.println("UNRECOGNIZED PACKET");
        }
    }
}
