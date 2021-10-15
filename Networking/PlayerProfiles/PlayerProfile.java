package Networking.PlayerProfiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;

/** Player profile file:
 *  x50 x50 x46 x00 | x00 x00 x00 x00 <- Header and spare bytes
 *  id, 4 bytes     | Name ->>>>> x00
 *  number of wins  | pfp bytes
 *  EOF
 */

public class PlayerProfile {
    //"PPF01000"
    static byte[] headerstr = {0x50, 0x50, 0x46, 0x30, 0x31, 0x30, 0x30, 0x30};

    public BufferedImage avatar;
    public String username;
    int lifetimeChips;
    int id;
    File saveFile;

    //Default constructor.
    public PlayerProfile(BufferedImage avi, String name, int id, int lifeChips){
        avatar = avi;
        lifetimeChips = lifeChips;
        username = name;
        this.id = id;
    }

    //alternate constructor, probably useful later
    public PlayerProfile(final String location){
        load(location);
    }

    private void copyTo(PlayerProfile ppf){
        avatar = ppf.avatar;
        lifetimeChips = ppf.lifetimeChips;
        username = ppf.username;
        this.id = ppf.id;
    }
    
    //loads from a file
    void load(final String location){
        try {
            saveFile = new File(location);
            copyTo(loadNewFromFile(saveFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void save(){
        try{
            if(saveFile == null) saveNewToFile(this);
            else saveOldToFile(this, saveFile);
        }
        catch(IOException h){h.printStackTrace();}
    }

    public static PlayerProfile newDefault(){
        BufferedImage buff;
        try {
            buff = ImageIO.read(new File("res/profiles/avatars/default.png"));
        } catch (IOException e) {
            buff = new BufferedImage(256, 256, 2);
            e.printStackTrace();
        }
        PlayerProfile ppf = new PlayerProfile(buff, "Guest", 0, 0);
        return ppf;
    }

    //loading from file
    static PlayerProfile loadNewFromFile(File filein) throws IOException{
        FileInputStream in = new FileInputStream(filein);

        String name = "";
        int id;
        int chips;

        byte[] header = in.readNBytes(8);
        for(int i = 0; i < 8; i++){
            if(header[i] != headerstr[i]) {
                in.close();
                throw new IOException("Not a PPF file, header is: " +
                    header[0] + " " + header[1] + " " + header[2] + " " + header[3] + " " +
                    header[4] + " " + header[5] + " " + header[6] + " " + header[7]    
                );
            }   
        }
        
        id = byteArrayToInt(in.readNBytes(4));
        int i = 0;
        while((i = in.read()) != 0){
            name += (char)i;
        }
        chips = byteArrayToInt(in.readNBytes(4));

        in.close();
        PlayerProfile pp = new PlayerProfile(ImageIO.read(new File("res/profiles/avatars/" + name + ".png")), name, id, chips);
        return pp;
    }
    
    //saves to a new file
    static void saveOldToFile(PlayerProfile pp, File old) throws IOException {
        old.delete();
        saveNewToFile(pp);
    }
    
    //saves to a new file
    static void saveNewToFile(PlayerProfile pp) throws IOException {
        File output = new File("res/profiles/" + pp.username + ".ppf");
        FileOutputStream os = new FileOutputStream(output);

        os.write(headerstr);
        os.write(intToByteArray(pp.id));
        os.write(pp.username.getBytes(Charset.forName("ASCII")));
        
        os.write(0x00);
        os.write(intToByteArray(pp.lifetimeChips));
        ImageIO.write(pp.avatar, "png", new File("res/profiles/avatars/" + pp.username + ".png"));
        os.close();
    }

    //converts an int to highest first 4 byte array
    public static byte[] intToByteArray(int data){
        return new byte[]{
            (byte)((data >>> 24) & 0xff),
            (byte)((data >> 16) & 0xff),
            (byte)((data >> 8)  & 0xff),
            (byte)((data >> 0)  & 0xff),
        };
    }

    //converts an int to highest first 4 byte array
    public static int byteArrayToInt(byte[] data){
        return ((int)data[0] << 24) | ((int)data[1] << 16) | ((int)data[2] << 8) | (int)data[3];
    }

    //lol
    public static int[] byteArrayToIntArray(byte[] data){
        int[] intarr = new int[data.length / 4];
        for(int i = 0; i < intarr.length; i++){
            int l = i * 4;
            intarr[i] = byteArrayToInt(new byte[]{
                data[l], data[l + 1], data[l + 2], data[l + 3],
            });
        }
        return intarr;
    }
}
