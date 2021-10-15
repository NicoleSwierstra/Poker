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

    public void deleteProfile(PlayerProfile pp){
        profiles.remove(pp);
        File ppf = new File("res/profiles/" + pp.username + ".ppf");
        ppf.delete();
        File avi = new File("res/profiles/avatars/" + pp.username + ".png");
        avi.delete();
    }

    public PlayerProfile loadDefault(){
        String name = "";
        try {
            FileInputStream fin = new FileInputStream(new File("res/svdat/default.txt"));
            Scanner s = new Scanner(fin);
            name = s.nextLine();
            s.close();
            System.out.println(name);
            for(PlayerProfile p : profiles){
                System.out.println(p.username);
                if(p.username.startsWith(name))
                    return p;
            }
            return PlayerProfile.newDefault();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return PlayerProfile.newDefault();
        }
    }
}
