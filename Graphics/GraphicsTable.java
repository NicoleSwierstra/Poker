package Graphics;

import GameLogic.*;
import java.awt.Graphics;

public class GraphicsTable extends Table {
    TableRenderer tr;
    GUI gui;
    Window win;
    boolean showCards = true;

    GraphicsTable(Window win, GUI gui){
        super();
        tr = new TableRenderer(win);
        this.win = win;
        this.gui = gui;
    }

    void start(){
        startGame();
    }

    void Render(Graphics g){
        tr.Render(g, pot, community, players, com_turnover, showCards ? turn : -1, end);
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
        
    }
}
