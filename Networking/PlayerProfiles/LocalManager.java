package Networking.PlayerProfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LocalManager {
    public List<PlayerProfile> profiles;

    public LocalManager(){
        profiles = new ArrayList<PlayerProfile>();
        File dir = new File("res/profiles");
        File [] files = dir.listFiles();

        for(File f : files){
            if(!f.getName().endsWith(".ppf")) continue;
            System.out.println("Added " + f.getName() + " to profiles");
            profiles.add(new PlayerProfile(f.getPath()));
        }
    }

    public PlayerProfile LoadDefault(){
        String name = "";
        try {
            FileInputStream fin = new FileInputStream(new File("res/svdat/default.txt"));
            Scanner s = new Scanner(fin);
            name = s.nextLine();
            s.close();
            for(PlayerProfile p : profiles){
                if(p.username.startsWith(name))
                    return p;
            }
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
