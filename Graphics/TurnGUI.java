package Graphics;

import GameLogic.*;

public class TurnGUI {
    Player player;
    boolean endturn = false;

    public TurnGUI(Player p) {
        player = p;
    }
    
    //to queue the turn GUI
    void turnGui(GUI gui){
        gui.queueText("Actions:", 0.5f, 0.3f, 0.02f, 0.1f);
        gui.queueButton(
            "Fold",
            0.375f, 0.5f, 0.1f, 0.1f,
            () -> {
                player.playing = false;
                this.endturn = true;
            }
        );
        gui.queueButton(
            "Bet " + player.deficit,
            0.5f, 0.5f, 0.1f, 0.1f,
            () -> {
                player.bet(player.deficit);
                this.endturn = true;
            }
        );
        gui.queueButton(
            "+1",
            0.6f, 0.5f, 0.05f, 0.1f,
            () -> {
                player.bet(player.deficit + 1);
                this.endturn = true;
            }
        );
        gui.queueButton(
            "+2",
            0.675f, 0.5f, 0.05f, 0.1f,
            () -> {
                player.bet(player.deficit + 2);
                this.endturn = true;
            }
        );
        gui.applyQueue();
    }

    public void takeTurn(GUI gui, GraphicsTable gt) {
        gui.queueButton(
            "Start " + player.name + "'s turn", 
            0.5f, 0.5f, 0.2f, 0.1f,
            () -> {gt.showCards = true; turnGui(gui);}
        );
        gui.applyQueue();
        while(!endturn){
            System.out.println(endturn);
        }
        gt.showCards = false;
    }
}
