package Networking.PlayerProfiles;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import utils.ByteUtils;

/**
 * In theory this should work
 * 
 * Online manager reads profile information and stores the profile locally
 */

public class OnlineManager {
    static List<PlayerProfile> onlinePlayers;

    //adds a profile from a byte array
    public static void AddProfile(byte[] playerBytes) throws IOException{
        onlinePlayers.add(
            PlayerProfile.loadFromStream(new ByteArrayInputStream(playerBytes))
        );
    }
}
