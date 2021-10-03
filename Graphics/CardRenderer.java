package Graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CardRenderer {

    static BufferedImage[] sprites;

    //loads the atlas into an array
    CardRenderer(){
        //loads cards atlas into a buffered image
        BufferedImage atlas;
        try {
            atlas = ImageIO.read(new File("res/cards.png"));
        } catch (IOException e) {
            atlas = null;
            System.out.println("FAIL");
            return;
        }

        int w = atlas.getWidth(), h = atlas.getHeight();

        //loads the atlas into seperate images
        sprites = new BufferedImage[53];
        for(int s = 0; s < 4; s++){
            for(int n = 0; n < 13; n++){
                float fx = 0.125f * (n % 8), 
                    fy = s * 0.25f + ((n < 8) ? 0.0f : 0.125f);
                int x = (int)(fx * w), y = (int)(fy * h);
                
                sprites[(s * 13) + n] = atlas.getSubimage(x, y, (int)(w * 0.125f), (int)(h * 0.125f));
            }
        }

        //adds the last card, the back
        sprites[52] = atlas.getSubimage((int)(w * 0.875f), (int)(h * 0.875f), (int)(w * 0.125f), (int)(h * 0.125f));
    }

    //draws a card
    static void drawCard(Graphics g, boolean shown, int x, int y, int suit, int num, int scale){
        if(sprites == null)return;
        g.drawImage(shown ? sprites[(suit * 13) + num] : sprites[52], x, y, (int)scale, (int)(scale * (7.0f/5.0f)), null);
    }
}
