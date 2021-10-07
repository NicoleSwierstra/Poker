package Graphics;

public class GraphicsGame {
    Window w;
    int playerNumber;

    public void menu(){
        Thread t = Thread.currentThread();

        w.gui.queueText("POKER", 0.5f, 0.3f, 0.2f, 0.2f);

        w.gui.queueButton(
            "Start", 
            0.5f, 0.5f, 0.1f, 0.1f,
            () -> {
                w.gui.applyQueue();
                t.interrupt();
            }
        );
        w.gui.queueButton(
            "Quit", 
            0.5f, 0.65f, 0.1f, 0.1f,
            () -> {
                w.mainwindow.setVisible(false);
                t.interrupt();
                System.exit(0);
            }
        );
        w.gui.applyQueue();
        try {
            Thread.currentThread().sleep(Long.MAX_VALUE); //sleeps for 292.5 billion years
        } catch (InterruptedException e) {}
    }

    //if anyone touches this i am defenestrating them
    void playerMenu(){
        Thread t = Thread.currentThread();

        w.gui.queueText("# of players", 0.5f, 0.3f, 0.2f, 0.2f);
        w.gui.queueButton(
            "2", 
            0.5f, 0.49f, 0.05f, 0.1f,
            () -> {playerNumber = 2; t.interrupt();}
        );
        w.gui.queueButton(
            "3", 
            0.6f, 0.51f, 0.05f, 0.1f,
            () -> {playerNumber = 3; t.interrupt();}
        );
        w.gui.queueButton(
            "4", 
            0.4f, 0.66f, 0.08f, 0.1f,
            () -> {playerNumber = 4; t.interrupt();}
        );
        w.gui.queueButton(
            "5", 
            0.5f, 0.64f, 0.1f, 0.1f,
            () -> {playerNumber = 5; t.interrupt();}
        );
        w.gui.queueButton(
            "6", 
            0.6f, 0.67f, 0.15f, 0.1f,
            () -> {playerNumber = 6; t.interrupt();}
        );
        w.gui.applyQueue();
        try {
            Thread.currentThread().sleep(Long.MAX_VALUE); //sleeps for 292.5 billion years
        } catch (InterruptedException e) {}
    }

    public GraphicsGame(){
        w = new Window();
        new Thread(() -> w.start()).start();
        menu();
        playerMenu();
        for(int i = 0; i < playerNumber; i++){
            w.tr.addPlayer("Player " + (i + 1));
        }
        w.g.renderGame = true; 
        while(true) w.tr.game();
    }
}
