package Graphics.GUI;
import java.awt.*;

public class Text extends GUIElement {
    String text;

    Text(String t, float x, float y, float width, float height){ 
        super(x, y, width, height);
        text = t;
    }

    void Render(Graphics g, GUI gui, int scw, int sch, float xmouse, float ymouse){
        int xmin = (int)(x * scw) - (int)(width * scw)/2, 
            ymin = (int)(y * sch) - (int)(height * sch)/2, 
            bw = (int)(width * scw), 
            bh = (int)(height * sch);
        
        float time = ((gui.starttime - System.currentTimeMillis()) / 4000.0f);
        Color rainbow = Color.getHSBColor(time % 1.0f, 1.0f, 0.5f);
        Font font = new Font("Comic Sans MS", 0, (int)(bh));
        FontMetrics metrics = g.getFontMetrics(font);
        int x = xmin + (bw - metrics.stringWidth(text)) / 2;
        int y = ymin + ((bh - metrics.getHeight()) / 2) + metrics.getAscent();
    
        g.setFont(font);
        g.setColor(new Color(0, 0, 0, 255));
        g.drawString(text, x + 3, y + 3);
        g.setColor(rainbow);
        g.drawString(text, x, y);
    }
}
