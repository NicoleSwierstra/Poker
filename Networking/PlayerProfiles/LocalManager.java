package Networking.PlayerProfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LocalManager {
    public static PlayerProfile LoadDefault(){
        String name = "";
        try {
            FileInputStream fin = new FileInputStream(new File("res/svdat/default.txt"));
            Scanner s = new Scanner(fin);
            name = s.nextLine();
            s.close();
            return new PlayerProfile("res/profiles/" + name + ".ppf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
