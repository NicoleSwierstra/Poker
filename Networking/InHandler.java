package Networking;

import java.io.*;

import utils.ByteUtils;

/** 0x00 0xFF
 *  Packet types
 *  0x00: NULL
 *  0x01: CLOCK / PING
 *  0x02: ADVANCE TURN
 *  0x03: USER PROFILE
 *  0x04: TABLE UPDATE
 */ 

//at this time this class is a test that checks the ping between two systems
public class InHandler implements Runnable {
    boolean isserver;
    DataInputStream instream;
    OutHandler outhandler;

    //the world's lamest constructor
    public InHandler(DataInputStream dis, OutHandler out){
        instream = dis;
        outhandler = out;
        //new Thread(this).start(); //does the run method or whatever
        while(instream != null){
            try {
                System.out.println("WAITING FOR PACKET");
                processPacket();
                System.out.println("GOT PACKET");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //checks for new packet in a while loop
    //to be quite honest i have no idea if this will work properly
    public void run(){
        try {
            while(true){
                processPacket();
            }
        } catch (IOException e) {

        }
    }

    //processes a packet and checks the first two bytes
    //if any error is recorded, the error is printed to the error stream ig
    void processPacket() throws IOException{
        if(instream.read() != 0x00) {
            System.err.println("CANT FIND PACKET START");
            return;
        }
        if(instream.read() != 0xFF){
            System.err.println("UNVERIFIED PACKET");
            return;
        }

        int type = instream.read();

        switch(type){
            case 0: // null
                break;
            case 1: // ping time
                if(isserver){
                    System.out.println(System.currentTimeMillis() - ByteUtils.bytesToLong(instream.readNBytes(8)));
                }
                else outhandler.sendPing(instream.readNBytes(8));
                break;
            case 2: // advance
                break;
            case 3: // profile
                break;
            case 4: // table
                break;
            default:
                System.err.println("UNRECOGNIZED PACKET");
        }
    }
}
