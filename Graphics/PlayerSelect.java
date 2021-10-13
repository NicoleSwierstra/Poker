package Graphics;

import java.util.List;
import java.util.ArrayList;

import Graphics.GUI.ButtonInterface;
import Networking.PlayerProfiles.LocalManager;
import Networking.PlayerProfiles.PlayerProfile;

public class PlayerSelect {
    List<PlayerProfile> profiles;
    GUI.GUIList guilist;

    PlayerSelect(){
        profiles = new ArrayList<PlayerProfile>();
    }

    //shows a list of all players, and "greys out" the already gaming ones
    void profileSelect(GUI gui, LocalManager lm){
        gui.saveGUI();
        int i;
        for(i = 0; i < lm.profiles.size(); i++){
            PlayerProfile ppf = lm.profiles.get(i);
            if (!profiles.contains(ppf)) {
                ButtonInterface bi = () -> {
                    profiles.add(ppf);
                    //guilist.addText("", x, y, width, height);;
                    gui.applyOld();
                };
                gui.queueButton(ppf.username, 0.5f, 0.2f + (0.1f * i), 0.1f, 0.08f, bi);
            }
            else gui.queueText(ppf.username, 0.5f, 0.2f + (0.1f * i), 0.1f, 0.04f);
        }
        gui.queueButton("Back", 0.5f, 0.2f + (0.1f * i), 0.1f, 0.08f, () -> {gui.applyOld();});
        gui.applyQueue();
    }

    //if anyone touches this i am defenestrating them
    void playerMenu(GUI gui, LocalManager lm){
        Thread t = Thread.currentThread();
        guilist = gui.queueList(false, 1, 0.1f, 0.5f, 0.35f, 0.1f, 0.08f);
        
        gui.queueText("Select Players", 0.5f, 0.15f, 0.2f, 0.05f);
        gui.queueButton(
            "+", 
            0.45f, 0.25f, 0.05f, 0.1f,
            () -> { profileSelect(gui, lm);}
        );
        gui.queueButton(
            "accept", 
            0.55f, 0.25f, 0.05f, 0.1f,
            () -> {t.interrupt();}
        );
        gui.applyQueue();
        try {
            Thread.currentThread().sleep(Long.MAX_VALUE); //sleeps for 292.5 billion years
        } catch (InterruptedException e) {}
    }
}
