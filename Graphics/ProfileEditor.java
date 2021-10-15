package Graphics;

import Networking.PlayerProfiles.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.filechooser.*;

import Graphics.GUI.ButtonInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProfileEditor {
    static PlayerProfile ppf;

    //shows a list of all profiles, and "greys out" the selected profile
    static void profileSelect(GUI gui, LocalManager lm){
        gui.saveGUI();
        int i;
        for(i = 0; i < lm.profiles.size(); i++){
            PlayerProfile profile = lm.profiles.get(i);
            if (profile != ppf) {
                gui.queueButton(profile.username, 0.5f, 0.2f + (0.1f * i), 0.1f, 0.08f,
                    () -> {ppf = profile; gui.applyOld();}
                );
            }
            else gui.queueText(ppf.username, 0.5f, 0.2f + (0.1f * i), 0.1f, 0.04f);
        }
        gui.queueText("Select profile", 0.5f, 0.1f, 0.1f, 0.08f);
        gui.queueButton("Cancel", 0.5f, 0.2f + (0.1f * i), 0.1f, 0.08f, () -> {gui.applyOld();});
        gui.applyQueue();
    }

    static void confirmation(GUI gui, PlayerProfile pp, LocalManager lm){
        gui.saveGUI();
        gui.queueText("Are you sure you want to delete " + pp.username + " ?", 0.5f, 0.25f, 0.0f, 0.05f);
        ButtonInterface confirm = () -> {
            lm.deleteProfile(pp);
            gui.applyOld();
        };
        ButtonInterface deny = () -> {
            gui.applyOld();
        };
        gui.queueButton("yes", 0.425f, 0.5f, 0.1f, 0.1f, confirm);
        gui.queueButton("no", 0.575f, 0.5f, 0.1f, 0.1f, deny);
        gui.applyQueue();
    }

    //puts a player profile editor
    static void PlayerProfileEdit(GUI gui, GraphicsGame graphicsGame, Window win) {
        Thread t = Thread.currentThread();
        ppf = graphicsGame.currentPlayer;
        GUI.TextBox namebox = gui.queueTextBox(ppf.username, 0.325f, 0.3f, 0.25f, 0.05f);
        GUI.Texture avitex = gui.queueTexture(ppf.avatar, 0.65f, 0.35f, 0.2f, 0.2f);
        gui.queueText("Edit profile:", 0.35f, 0.15f, 0.2f, 0.1f);

        gui.queueButton("Set AVI", 0.65f, 0.15f, 0.1f, 0.1f, ()->{
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Images", "jpg", "gif", "png", "bmp");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(win.g);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage source = ImageIO.read(chooser.getSelectedFile()),
                        dest = new BufferedImage(256, 256, 2);
                    dest.getGraphics().drawImage(source, 0, 0, 256, 256, null);
                    ppf.avatar = dest;
                    avitex.bi = dest;
                } catch (IOException e) {
                    System.out.println("FAIL");
                }
            }
        });
        gui.queueButton("Save", 0.2375f, 0.6f, 0.075f, 0.1f, () -> {
            ppf.username = namebox.field;
            ppf.save();
            graphicsGame.currentPlayer = ppf;
            try {
                FileWriter os = new FileWriter(new File("res/svdat/default.txt"));
                os.write(ppf.username);
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            t.interrupt();
        });
        gui.queueButton("Load", 0.325f, 0.6f, 0.075f, 0.1f, () -> {profileSelect(gui, graphicsGame.lm);});
        gui.queueButton("Cancel", 0.4125f, 0.6f, 0.075f, 0.1f, () -> {t.interrupt();});
        gui.queueButton("New", 0.4785f, 0.3f, 0.025f, 0.05f, () -> {
            ppf = PlayerProfile.newDefault();
            namebox.field = ppf.username;
            avitex.bi = ppf.avatar;
        });
        gui.queueButton("X", 0.5125f, 0.3f, 0.025f, 0.05f, () -> {
            confirmation(gui, ppf, graphicsGame.lm);
            ppf = PlayerProfile.newDefault();
            namebox.field = ppf.username;
            avitex.bi = ppf.avatar;
        });

        gui.applyQueue();
        try {
            Thread.sleep(Long.MAX_VALUE); //sleeps for 292.5 billion years
        } catch (InterruptedException e) {}
    }
}
