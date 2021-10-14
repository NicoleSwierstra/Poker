package Graphics;

import Networking.PlayerProfiles.LocalManager;
import Networking.PlayerProfiles.PlayerProfile;

public class GraphicsGame {
    enum _mode{
        MAIN, GAME, PROFILE, ONLINE
    }
    _mode mode;
    Window w;
    PlayerProfile currentPlayer;

    void playerAvi(){
        w.gui.queueText(currentPlayer.username, 0.05f, 0.2f, 0.1f, 0.03f);
        w.gui.queueTexture(currentPlayer.avatar, 0.05f, 0.05f, 0.08f, 0.08f);
    }

    public void menu(){
        Thread t = Thread.currentThread();

        w.gui.queueText("POKER", 0.5f, 0.3f, 0.2f, 0.2f);
        
        w.gui.queueButton(
            "Local", "res/icon/cards.png",
            0.35f, 0.55f, 0.125f, 0.25f,
            () -> {
                mode = _mode.GAME;
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
                mode = _mode.PROFILE;
                t.interrupt();
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
        playerAvi();

        w.gui.applyQueue();
        try {
            Thread.sleep(Long.MAX_VALUE); //sleeps for 292.5 billion years
        } catch (InterruptedException e) {}
    }

    public GraphicsGame(){
        LocalManager lm = new LocalManager();

        currentPlayer = lm.LoadDefault();
        
        w = new Window();
        mode = _mode.MAIN;
        new Thread(() -> w.start()).start();
        menu();
        
        while(true) {
            w.g.renderGame = false;  
            switch(mode){
                case GAME:
                    PlayerSelect psel = new PlayerSelect();
                    psel.playerMenu(w.gui, lm);
                    if (psel.profiles.size() == 0) {
                        mode = _mode.MAIN;
                        continue;
                    }
                    for(int i = 0; i < psel.profiles.size(); i++){
                        w.tr.addPlayer(psel.profiles.get(i));
                    }
                    w.g.renderGame = true;  
                    while(true) w.tr.game();
                    //break;
                case MAIN:
                    menu();
                    break;
                case ONLINE:
                    break;
                case PROFILE:
                    ProfileEditor.PlayerProfileEdit(w.gui, this, w);
                    mode = _mode.MAIN;
                    break;
                default:
                    break;
            }
        }
    }
}
