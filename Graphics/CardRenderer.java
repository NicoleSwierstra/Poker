package Graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CardRenderer {

    static BufferedImage[] sprites;

    //loads the atlas into an array
    CardRenderer(){
        BufferedImage atlas;
        try {
            atlas = ImageIO.read(new File("res/cards.png"));
            System.out.println(atlas.getHeight());
        } catch (IOException e) {
            atlas = null;
            System.out.println("FAIL");
        }

        int w = atlas.getWidth(), h = atlas.getHeight();

        sprites = new BufferedImage[53];
        for(int s = 0; s < 4; s++){
            for(int n = 0; n < 13; n++){
                float fx = 0.125f * (n % 8), 
                    fy = s * 0.25f + ((n < 8) ? 0.0f : 0.125f);
                int x = (int)(fx * w), y = (int)(fy * h);
                
                sprites[(s * 13) + n] = atlas.getSubimage(x, y, (int)(w * 0.125f), (int)(h * 0.125f));
            }
        }

        sprites[53] = atlas.getSubimage((int)(w * 0.875f), (int)(h * 0.875f), (int)(w * 0.125f), (int)(h * 0.125f));

        //TODO:depreciate
        //try {
        //    ImageIO.write(sprites[0], "PNG", new File("temp.png"));
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
    }

    //draws a card
    static void drawCard(Graphics g, int x, int y, int suit, int num, int scale){
        if(sprites == null) {
            return;
        };
        g.drawImage(sprites[(suit * 13) + num], x, y, (int)scale, (int)(scale * (7.0f/5.0f)), null);
    }
}
