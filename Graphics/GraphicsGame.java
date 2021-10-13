package Graphics;

public class GraphicsGame {
    Window w;

    public void menu(){
        Thread t = Thread.currentThread();

        w.gui.queueText("POKER", 0.5f, 0.3f, 0.2f, 0.2f);
        
        w.gui.queueButton(
            "Local", "res/icon/cards.png",
            0.35f, 0.55f, 0.125f, 0.25f,
            () -> {
                w.gui.applyQueue();
                t.interrupt();
            }
        );
        w.gui.queueButton(
            "Online", "res/icon/globe.png",
            0.5f, 0.55f, 0.125f, 0.25f,
            () -> {
                
            }
        );
        w.gui.queueButton(
            "Profile", "res/icon/profile.png",
            0.65f, 0.55f, 0.125f, 0.25f,
            () -> {
                
            }
        );
        w.gui.queueButton(
            "Quit",
            0.5f, 0.75f, 0.3f, 0.1f,
            () -> {
                w.mainwindow.setVisible(false);
                t.interrupt();
                System.exit(0);
            }
        );
        w.gui.queueTextBox(0.5f, 0.9f, 0.3f, 0.1f);
        w.gui.applyQueue();
        try {
            Thread.currentThread().sleep(Long.MAX_VALUE); //sleeps for 292.5 billion years
        } catch (InterruptedException e) {}
    }

    public GraphicsGame(){
        w = new Window();
        new Thread(() -> w.start()).start();
        menu();
        PlayerSelect.playerMenu(w.gui);
        for(int i = 0; i < PlayerSelect.playerNumber; i++){
            w.tr.addPlayer("Player " + (i + 1));
        }
        w.g.renderGame = true; 
        while(true) w.tr.game();
    }
}
