package Graphics.GUI;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.Graphics;

public class Texture extends GUIElement{
    public BufferedImage bi;
    
    Texture(BufferedImage bi, float x, float y, float width, float height){
        super(x, y, width, height);
        this.bi = bi;
    }

    Texture(String img, float x, float y, float width, float height){
        super(x, y, width, height);
        try {
            bi = ImageIO.read(new File(img));
        } catch (IOException e) {
            System.out.println("FAIL on TEXTURE");
            e.printStackTrace();
        }
    }

    void Render(Graphics g, GUI gui, int scw, int sch, float xmouse, float ymouse){
        int xmin = (int)(x * scw) - (int)(width * scw)/2, 
            ymin = (int)(y * sch) - (int)(height * sch)/2, 
            size = (int)(width * scw);
        g.drawImage(bi, xmin, ymin, size, size, null);
    }
};