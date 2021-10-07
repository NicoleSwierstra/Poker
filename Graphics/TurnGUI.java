package Graphics;

import GameLogic.*;

public class TurnGUI {
    Player player;
    Thread main;

    public TurnGUI(Player p) {
        player = p;
        main = Thread.currentThread();
    }
    
    //to queue the turn GUI
    void turnGui(GUI gui){
        gui.queueText("Actions:", 0.5f, 0.3f, 0.02f, 0.1f);
        gui.queueButton(
            "Fold",
            0.375f, 0.5f, 0.1f, 0.1f,
            () -> {
                player.playing = false;
                main.interrupt();
            }
        );
        gui.queueButton(
            "Bet " + player.deficit,
            0.5f, 0.5f, 0.1f, 0.1f,
            () -> {
                player.bet(player.deficit);
                main.interrupt();
            }
        );
        gui.queueButton(
            "+1",
            0.6f, 0.5f, 0.05f, 0.1f,
            () -> {
                player.bet(player.deficit + 1);
                main.interrupt();
            }
        );
        gui.queueButton(
            "+2",
            0.675f, 0.5f, 0.05f, 0.1f,
            () -> {
                player.bet(player.deficit + 2);
                main.interrupt();
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
        try {
            main.sleep(Long.MAX_VALUE); //sleeps for 292.5 billion years
        } catch (InterruptedException e) {}
        gt.showCards = false;
    }
}
