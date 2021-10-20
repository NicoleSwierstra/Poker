package Networking;

import Graphics.GUI.*;

public class ConnectGUI {
    static Thread main;
    static Server server;
    static Client client;
    public static void showNetworkGUI(GUI gui){
        main = Thread.currentThread();

        gui.queueButton("Create", 0.5f, 0.4f, 0.1f, 0.08f, ()->{
            CreateMenu(gui);
        });
        gui.queueButton("Join",   0.5f, 0.5f, 0.1f, 0.08f, ()->{
            JoinMenu(gui);
        });
        gui.queueButton("Back",   0.5f, 0.6f, 0.1f, 0.08f, () -> {main.interrupt();});
        gui.applyQueue();
        try {
            Thread.sleep(Long.MAX_VALUE); //sleeps for 292.5 billion years
        } catch (InterruptedException e) {}
    }

    static void CreateMenu(GUI gui){
        server = new Server();
        gui.saveGUI();
        gui.queueText("Have others connect through this ip adress:", 0.5f, 0.4f, 0.0f, 0.05f);
        gui.queueText(server.getIp(), 0.5f, 0.5f, 0.0f, 0.1f);
        gui.queueButton("Back", 0.5f, 0.6f, 0.1f, 0.08f, ()->{gui.applyOld();});
        gui.applyQueue();
        server.init();
    }

    static void JoinMenu(GUI gui){
        gui.saveGUI();
        gui.queueText("Enter the IP to join:", 0.5f, 0.4f, 0.0f, 0.05f);
        TextBox tb = gui.queueTextBox("", 0.5f, 0.5f, 0.3f, 0.1f);
        gui.queueButton("Back", 0.475f, 0.6f, 0.045f, 0.08f, ()->{gui.applyOld();});
        gui.queueButton("Join", 0.525f, 0.6f, 0.045f, 0.08f, ()->{client = new Client(tb.field);});
        gui.applyQueue();
    }
}
