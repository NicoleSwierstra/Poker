package Graphics;

import GameLogic.*;
import java.awt.Graphics;

public class GraphicsTable extends Table {
    TableRenderer tr;
    GUI gui;
    Window win;

    GraphicsTable(Window win){
        super();
        tr = new TableRenderer(win);
        this.win = win;
    }

    void start(){
        startGame();
    }

    void Render(Graphics g){
        tr.Render(g, pot, community, players, com_turnover, turn);
    }

    @Override
    protected void round(int num){
        
        com_turnover = comt[num];
        do {
            for(int i = 0; i < players.size(); i++){
                if(players.get(i).playing){
                    playerTurn(i);
                }
            }
        } while(!allAdvance());
    }
}
