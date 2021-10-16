package Graphics.GUI;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;

public class IconButton extends Button {
    BufferedImage icon;

    IconButton(String label, String icon, float x, float y, float width, float height, ButtonInterface bi){
        super(label, x, y, width, height, bi);
        try {
            this.icon = ImageIO.read(new File(icon));
        } catch (IOException e) {
            System.out.println("FAIL");
        }
    }

    void Render(Graphics g, GUI gui, int scw, int sch, float xmouse, float ymouse){
        int xmin = (int)(x * scw) - (int)(width * scw)/2, 
            ymin = (int)(y * sch) - (int)(height * sch)/2, 
            bw = (int)(width * scw), 
            bh = (int)(height * sch);
        
        if(gui.checkIntersect(this, false, xmouse, ymouse)) 
            g.setColor(new Color(255, 0, 0, 125));
        else g.setColor(new Color(255, 255, 255, 125));
        
        ((Graphics2D)g).fillRoundRect(xmin, ymin, bw, bh, 10, 10);
        g.setColor(new Color(0, 0, 0));
        ((Graphics2D)g).drawRoundRect(xmin, ymin, bw, bh, 10, 10);

        Font font = new Font("Comic Sans MS", 0, bh/10);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = xmin + (bw - metrics.stringWidth(label)) / 2;
        int y = ymin + metrics.getAscent() + ((5 * bh)/6);
        g.setFont(font);
        g.drawString(label, x, y);
        g.drawImage(icon, xmin, ymin, bw, (7 * bh)/8, null);
    }
}
