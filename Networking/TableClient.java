package Networking;

import Graphics.GraphicsTable;
import Graphics.Window;
import Graphics.GUI.GUI;
import java.awt.Graphics;

public class TableClient extends GraphicsTable {
    int playerNum = 0;
    Client client;

    protected TableClient(Window win, GUI gui, int player, Client c) {
        super(win, gui);
        playerNum = player;
        client = c;
    }

    @Override
    protected void Render(Graphics g){
        tr.Render(g, pot, community, players, profiles, com_turnover, 0, end);
    }

    @Override
    protected void round(int num){
        com_turnover = comt[num];
        waitForTurn();
        playerTurn(turn);
        turn++;
        waitForRound();
    }

    void waitForTurn(){
        while(turn != playerNum){
            
        }
    }

    void waitForRound(){

    }
}
