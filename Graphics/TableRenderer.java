package Graphics;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.geom.*;

import javax.imageio.ImageIO;

class pPos {
    final static float[][] p = {
        {0.93f, 0.50f, -90.0f}, //right
        {0.67f, 0.13f, 180.0f}, //top r
        {0.50f, 0.13f, 180.0f}, //top c
        {0.33f, 0.13f, 180.0f}, //top l
        {0.07f, 0.50f,  90.0f}, //left
        {0.33f, 0.87f,   0.0f}, //bottom l
        {0.50f, 0.87f,   0.0f}, //bottom c
        {0.67f, 0.87f,   0.0f}  //bottom r
    };

    static int getX(int pos, int width){
        return (int)(width * p[pos][0]);
    }
    static int getY(int pos, int height){
        return (int)(height * p[pos][1]);
    }
    static float getR(int pos){
        return p[pos][2];
    }
}

public class TableRenderer {
    CardRenderer cr;
    BufferedImage chip;
    Window win;
    Random r;

    TableRenderer(Window w){
        r = new Random();
        cr = new CardRenderer();
        win = w;
        try {
            chip = ImageIO.read(new File("res/chip.png"));
        } catch (IOException e) {
            System.out.println("FAIL");
        }
    }

    void Render(Graphics g){
        int h = win.g.paneheight, w = win.g.panewidth;
        renderPlayer((Graphics2D)g, pPos.getX(0, w), pPos.getY(0, h), pPos.getR(0), false);
        renderPlayer((Graphics2D)g, pPos.getX(1, w), pPos.getY(1, h), pPos.getR(1), false);
        renderPlayer((Graphics2D)g, pPos.getX(3, w), pPos.getY(3, h), pPos.getR(3), false);
        renderPlayer((Graphics2D)g, pPos.getX(4, w), pPos.getY(4, h), pPos.getR(4), false);
        renderPlayer((Graphics2D)g, pPos.getX(5, w), pPos.getY(5, h), pPos.getR(5), false);
        renderPlayer((Graphics2D)g, pPos.getX(7, w), pPos.getY(7, h), pPos.getR(7),  true);

        for(int i = 0; i < 5; i++) {
            cr.drawCard(g, true, (int)(w * (0.25f + (0.08f * i))), (int)(h * 0.65f) - 70, (i * 3) % 4, (i * 50) % 13, 100);
        }
        cr.drawCard(g, false, (int)(w * 0.65f), (int)(h * 0.65f) - 70, 0, 0, 100);
        drawPot(g, 40, w, h);
    };

    //renders a player's hand and their chips
    void renderPlayer(Graphics2D g, int x, int y, float r, boolean show){
        AffineTransform bt = g.getTransform();
        g.translate(x, y);
        g.rotate(Math.toRadians(r));
        
        cr.drawCard(g, show, -170, -70, 0, 11, 100);
        cr.drawCard(g, show,  -50, -70, 2, 8, 100);

        for(int i = 0; i < 20; i++){
            drawChip(g, 0, 60 + ((i/7) * 50), -70 + ((i % 7) * 15), 50);
        }
        g.setTransform(bt);
    }

    //draws a poker chip
    void drawChip(Graphics g, int type, int x, int y, int scale){
        //0 = 1 white
        //1 = 2 red
        //2 = 4 blue
        //3 = 8 yellow?
        final Color[] colors = 
            {new Color(255, 255, 255), new Color(255, 0, 0), new Color(0, 0, 125), new Color(200, 150, 0)};
        g.setColor(colors[type]);
        g.fillOval(x, y, scale, scale);
        g.drawImage(chip, x, y, scale, scale, null);
    }

    void drawPot(Graphics g, int amount, int width, int height){
        for(int i = 0; i < amount; i++){
            drawChip(g, 0, (int)(width * 0.25f) + r.nextInt(width/2), (int)(height * 0.29f) + r.nextInt(width/5), 50);
        }
    }
}
