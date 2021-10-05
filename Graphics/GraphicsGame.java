package Graphics;

public class GraphicsGame {
    Window w;

    public void menu(){
        w.gui.queueButton(
            "Start", 
            0.45f, 0.45f, 0.55f, 0.55f,
            () -> {
                w.g.renderGame = true; 
                w.gui.applyQueue();
            }
        );
        w.gui.queueButton(
            "Quit", 
            0.45f, 0.60f, 0.55f, 0.70f,
            () -> {
                w.mainwindow.setVisible(false);
                System.exit(0);
            }
        );
        w.gui.applyQueue();
    }

    public GraphicsGame(){
        w = new Window();
        w.tr.addPlayer("Player 1");
        w.tr.addPlayer("Player 2");
        w.tr.addPlayer("Player 3");
        menu();
        new Thread(() -> w.start()).start();
        w.tr.game();
    }
}
