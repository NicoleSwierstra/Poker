package Networking.PlayerProfiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import GameLogic.Player;

/** Player profile file:
 *  name of avatar in ascii, 
 * 
 * 
 * 
 * 
 * 
 */

public class PlayerProfile {
    BufferedImage avatar;
    String username;
    int lifetimeChips;

    //Default constructor.
    PlayerProfile(BufferedImage avi, String name, int lifeChips){
        avatar = avi;
        lifetimeChips = lifeChips;
        username = name;
    }

    //alternate constructor, probably useful later
    PlayerProfile(final String location){
        loadFromFile(location);
    }
    
    //loads from a file
    void loadFromFile(final String location){
        File in = new File(location);
        try {
            FileInputStream fis = new FileInputStream(in);
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
