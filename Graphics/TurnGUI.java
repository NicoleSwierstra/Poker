package Graphics;

import GameLogic.*;
import Graphics.GUI.ButtonInterface;

public class TurnGUI {
    Player player;
    boolean endturn = false;

    public TurnGUI(Player p) {
        player = p;
    }
    
    //to queue the turn GUI
    void turnGui(GUI gui){
        gui.queueButton(
            "Fold",
            0.30f, 0.45f, 0.40f, 0.55f,
            () -> {
                player.playing = false;
                this.endturn = true;
            }
        );
        gui.queueButton(
            "Bet " + player.deficit,
            0.45f, 0.45f, 0.55f, 0.55f,
            () -> {
                player.bet(player.deficit);
                this.endturn = true;
            }
        );
        gui.queueButton(
            "+1",
            0.575f, 0.45f, 0.625f, 0.55f,
            () -> {
                player.bet(player.deficit + 1);
                this.endturn = true;
            }
        );
        gui.queueButton(
            "+2",
            0.65f, 0.45f, 0.70f, 0.55f,
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
            0.35f, 0.45f, 0.65f, 0.55f,
            () -> {gt.showCards = true; turnGui(gui);}
        );
        gui.applyQueue();
        while(!endturn){
            System.out.println(endturn);
        }
        gt.showCards = false;
    }
}
