package Graphics;

public class PlayerSelect {
    static int playerNumber = 0;

    //if anyone touches this i am defenestrating them
    static void playerMenu(GUI gui){
        Thread t = Thread.currentThread();

        gui.queueText("# of players", 0.5f, 0.3f, 0.2f, 0.2f);
        gui.queueButton(
            "2", 
            0.5f, 0.49f, 0.05f, 0.1f,
            () -> {playerNumber = 2; t.interrupt();}
        );
        gui.queueButton(
            "3", 
            0.6f, 0.51f, 0.05f, 0.1f,
            () -> {playerNumber = 3; t.interrupt();}
        );
        gui.queueButton(
            "4", 
            0.4f, 0.66f, 0.08f, 0.1f,
            () -> {playerNumber = 4; t.interrupt();}
        );
        gui.queueButton(
            "5", 
            0.5f, 0.64f, 0.1f, 0.1f,
            () -> {playerNumber = 5; t.interrupt();}
        );
        gui.queueButton(
            "6", 
            0.6f, 0.67f, 0.25f, 0.1f,
            () -> {playerNumber = 6; t.interrupt();}
        );
        gui.applyQueue();
        try {
            Thread.currentThread().sleep(Long.MAX_VALUE); //sleeps for 292.5 billion years
        } catch (InterruptedException e) {}
    }
}
