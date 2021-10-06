package Graphics;

public class GraphicsGame {
    Window w;

    public void menu(){
        w.gui.queueText("POKER", 0.5f, 0.3f, 0.2f, 0.2f);

        w.gui.queueButton(
            "Start", 
            0.5f, 0.5f, 0.1f, 0.1f,
            () -> {
                w.g.renderGame = true; 
                w.gui.applyQueue();
            }
        );
        w.gui.queueButton(
            "Quit", 
            0.5f, 0.65f, 0.1f, 0.1f,
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
        w.tr.addPlayer("Player 4");
        w.tr.addPlayer("Player 5");
        w.tr.addPlayer("Player 6");
        menu();
        new Thread(() -> w.start()).start();
        while(!w.g.renderGame) System.out.println(w.g.renderGame);
        while(true) w.tr.game();
    }
}
