package Networking;

import java.io.*;
import java.util.*;

import Networking.PlayerProfiles.PlayerProfile;
import utils.ByteUtils;

//at this time this class is a test that checks the ping between two systems
public class OutHandler {
    boolean server;
    DataOutputStream outstream;
    Timer pingTimer;

    //the world's lamest constructor
    public OutHandler(DataOutputStream dos, boolean server){
        outstream = dos;
        this.server = server;
        if(server) addPingTimer();
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

    private void writeHead(int type) throws IOException{
        outstream.write(0x00); //init
        outstream.write(0xFF); //init 2
        outstream.write(type); //type
    }

    //sends a ping
    void sendPing() throws IOException {
        writeHead(0x01); // clock
        outstream.write(ByteUtils.longToBytes(System.currentTimeMillis()));
        outstream.flush();
    }

    //returns a ping for ping measurement
    void sendPing(byte[] inping) throws IOException {
        writeHead(0x01); // clock
        outstream.write(inping);
        outstream.flush();
    }

    //sends the entire profile over the outstream
    public void sendProfile(PlayerProfile ppf) throws IOException{
        writeHead(0x02); // profile
        byte[] ppfbytes = ppf.getAllBytes(true);
        outstream.write(ByteUtils.intToBytes(ppfbytes.length));
        outstream.write(ppfbytes);
        outstream.flush();
    }
}
