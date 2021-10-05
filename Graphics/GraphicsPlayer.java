package Graphics;

import GameLogic.*;

public class GraphicsPlayer extends GameLogic.Player {
    boolean endturn;

    public GraphicsPlayer(String name, Table table) {
        super(name, table);
    }
    
    //to queue the turn GUI
    void turnGui(){

    }

    public void takeTurn(GUI gui) {
        gui.queueButton(
            "Start", 
            0.35f, 0.45f, 0.65f, 0.55f,
            () -> {
                
                gui.applyQueue();
            }
        );
        gui.applyQueue();
        while(true);
    }
}
