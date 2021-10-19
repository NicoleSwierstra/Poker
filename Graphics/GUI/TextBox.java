package Graphics.GUI;

import java.awt.*;

public class TextBox extends GUIElement {
    public String field;

    TextBox(String text, float x, float y, float width, float height){
        super(x, y, width, height);
        field = text;
    }

    void Render(Graphics g, GUI gui, int scw, int sch, float xmouse, float ymouse){
        int xmin = (int)(x * scw) - (int)(width * scw)/2, 
            ymin = (int)(y * sch) - (int)(height * sch)/2, 
            bw = (int)(width * scw), 
            bh = (int)(height * sch); 
        

        if(this == gui.active) 
            g.setColor(new Color(255, 0, 0, 125));
        else if (gui.checkIntersect(this, false, xmouse, ymouse))
            g.setColor(new Color(127, 127, 127, 125));
        else
            g.setColor(new Color(255, 255, 255, 125));

        ((Graphics2D)g).fillRoundRect(xmin, ymin, bw, bh, 10, 10);
        g.setColor(new Color(0, 0, 0));
        ((Graphics2D)g).drawRoundRect(xmin, ymin, bw, bh, 10, 10);
    
        Font font = new Font("Comic Sans MS", 0, (int)(bh * 0.8f));
        FontMetrics metrics = g.getFontMetrics(font);
        int x = xmin + (bw  / 20);
        int y = ymin + (((int)(bh * 0.9f) - metrics.getHeight()) / 2) + metrics.getAscent();

        g.setFont(font);
        g.setColor(new Color(0, 0, 0, 255));
        g.drawString(field, x, y);
    }
}