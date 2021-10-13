package Graphics;

import Networking.PlayerProfiles.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.filechooser.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProfileEditor {
    static PlayerProfile ppf;

    static void PlayerProfileEdit(GUI gui, GraphicsGame graphicsGame) {
        Thread t = Thread.currentThread();
        ppf = graphicsGame.currentPlayer;
        GUI.TextBox namebox = gui.queueTextBox(ppf.username, 0.35f, 0.3f, 0.3f, 0.1f);
        GUI.Texture avitex = gui.queueTexture(ppf.avatar, 0.65f, 0.35f, 0.2f, 0.2f);
        gui.queueText("Edit profile:", 0.35f, 0.15f, 0.2f, 0.1f);

        gui.queueButton("Set AVI", 0.65f, 0.15f, 0.1f, 0.1f, ()->{
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Images", "jpg", "gif", "png", "bmp");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(Window.g);
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
        gui.queueButton("Save", 0.25f, 0.6f, 0.1f, 0.1f, () -> {
            ppf.username = namebox.field;
            ppf.saveToFile();
            graphicsGame.currentPlayer = ppf;
            t.interrupt();
        });
        gui.queueButton("Cancel", 0.4f, 0.6f, 0.1f, 0.1f, () -> {t.interrupt();});

        gui.queueButton("Load", 0.25f, 0.45f, 0.1f, 0.1f, () -> {
            JFileChooser chooser = new JFileChooser("res/profiles");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Player Profile Files", "ppf");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(Window.g);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                ppf = new PlayerProfile(chooser.getSelectedFile().getPath());
                namebox.field = ppf.username;
                avitex.bi = ppf.avatar;
            }
        });
        gui.queueButton("SetDefault", 0.4f, 0.45f, 0.1f, 0.1f, () -> {
            try {
                FileWriter os = new FileWriter(new File("res/svdat/default.txt"));
                os.write(ppf.username);
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        gui.applyQueue();
        try {
            Thread.sleep(Long.MAX_VALUE); //sleeps for 292.5 billion years
        } catch (InterruptedException e) {}
    }
}
