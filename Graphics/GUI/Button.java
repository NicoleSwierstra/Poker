package Graphics.GUI;

import java.awt.*;

public class Button extends GUIElement {
    String label;
    ButtonInterface bi;

    Button(String label, float x, float y, float width, float height, ButtonInterface bi){
        super(x, y, width, height);
        this.label = label;
        this.bi = bi;
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
        
        Font font = new Font("Comic Sans MS", 0, bh/3);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = xmin + (bw - metrics.stringWidth(label)) / 2;
        int y = ymin + ((bh - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(label, x, y);
    }
}
