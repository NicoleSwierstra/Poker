package Graphics;

import GameLogic.*;
import Networking.PlayerProfiles.PlayerProfile;

import java.awt.Graphics;
import java.util.*;

public class GraphicsTable extends Table {
    TableRenderer tr;
    GUI gui;
    Window win;
    boolean showCards = true;
    List<PlayerProfile> profiles;

    GraphicsTable(Window win, GUI gui){
        super();
        profiles = new ArrayList<PlayerProfile>();
        tr = new TableRenderer(win);
        this.win = win;
        this.gui = gui;
    }

    void start(){
        startGame();
    }

    void Render(Graphics g){
        tr.Render(g, pot, community, players, profiles, com_turnover, showCards ? turn : -1, end);
    }

    public void addPlayer(PlayerProfile pp){
        addPlayer(pp.username);
        profiles.add(pp);
    }

    @Override
    protected void round(int num){
        com_turnover = comt[num];
        do {
            for(int i = 0; i < players.size(); i++){
                if(players.get(i).playing){
                    turn = i;
                    playerTurn(i);
                }
            }
        } while(!allAdvance());
    }

    @Override
    protected void playerTurn(int turnNum){
        showCards = false;
        Player p = players.get(turnNum);
        TurnGUI tg = new TurnGUI(p);
        tg.takeTurn(gui, this);
    }

    @Override
    protected void printEnd(Player winner){
        SoundEngine.playSound("res/aud/ohmygod.wav");
        
        Thread main = Thread.currentThread();
        gui.queueText(winner.name + " wins $" + pot, 0.5f, 0.3f, 0.02f, 0.1f);
        gui.queueButton(
            "Play Again", 
            0.5f, 0.5f, 0.3f, 0.1f,
            () -> { main.interrupt(); gui.applyQueue();}
        );
        gui.queueButton(
            "Quit", 
            0.5f, 0.65f, 0.3f, 0.1f,
            () -> { main.interrupt(); System.exit(0); }
        );
        gui.applyQueue();

        try {
            Thread.sleep(Long.MAX_VALUE); //sleeps for 292.5 billion years
        } catch (InterruptedException e) {}
    }
}
