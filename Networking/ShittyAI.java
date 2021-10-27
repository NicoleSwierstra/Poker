package Networking;

import java.util.Random;

import GameLogic.Player;

public class ShittyAI {
    /**
     * <b> HOW THIS WORKS: </b>
     * <br></br>
     * <b>Index 0</b> is fold value, below it is a fold and above is a bet
     * <br></br>
     * <b>Index 1</b> is the raise value, above it is raise by 1, below is just bet
     * <br></br>
     * <b>Index 2</b> is the critical raise value, above it is a raise by 2
     */
    final static float[][] percentTurn = {
        {  20.0f,  90.0f,  99.0f }, //hc
        {  10.0f,  85.0f,  90.0f }, //pair
        {   5.0f,  75.0f,  85.0f }, //two pair
        {   4.0f,  70.0f,  80.0f }, //triple
        {   2.0f,  65.0f,  75.0f }, //straight
        {   1.0f,  60.0f,  70.0f }, //flush
        {   0.5f,  55.0f,  65.0f }, //quad
        {   0.2f,  50.0f,  60.0f }, //full
    };

    static Random r = new Random();

    public static void takeAITurn(Player p, int repeated){
        float roll = r.nextFloat();
        roll *= 100;
        roll /= repeated;
        int h = p.handvalue().value;
        System.out.println(roll + " h: " + h);
        if(roll < percentTurn[h][0])
            p.playing = false;
        else if(roll < percentTurn[h][1])
            p.bet(p.deficit);
        else if(roll < percentTurn[h][2])
            p.bet(p.deficit + 1);
        else
            p.bet(p.deficit + 2);
    }
}
