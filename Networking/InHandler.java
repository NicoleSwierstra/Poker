package Networking;

import java.io.*;

import utils.ByteUtils;

/** 0x00 0xFF
 *  Packet types
 *  0x00: NULL
 *  0x01: CLOCK / PING
 *  0x02: ADVANCE TURN
 *  0x03: GAME END
 *  0x04: TABLE UPDATE
 *  0x05: USER PROFILE
 */ 

//at this time this class is a test that checks the ping between two systems
public class InHandler implements Runnable {
    DataInputStream instream;

    //the world's lamest constructor
    public InHandler(DataInputStream dis){
        instream = dis;
        //new Thread(this).start(); //does the run method or whatever
        while(true){
            try {
                processPacket(new ByteArrayInputStream(instream.readAllBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //checks for new packet in a while loop
    //to be quite honest i have no idea if this will work properly
    public void run(){
       //try {
       //    while(true){
       //        processPacket(new ByteArrayInputStream(instream.readAllBytes()));
       //    }
       //} catch (IOException e) {

       //}
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
            case 0: // null
                break;
            case 1: // ping time
                System.out.println(System.currentTimeMillis() - ByteUtils.bytesToLong(datain.readNBytes(8)));
                break;
            default:
                System.err.println("UNRECOGNIZED PACKET");
        }
    }
}
