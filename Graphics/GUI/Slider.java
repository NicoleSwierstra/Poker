package Graphics.GUI;

import java.awt.Color;
import java.awt.Graphics;

public class Slider extends GUIElement {
    float value;
    float max;
    float min;

    Slider(float def, float max, float min, float x, float y, float width, float height){
        super(x, y, width, height);
        value = def;
    }

    void Render(Graphics g, GUI gui, int scw, int sch, float xmouse, float ymouse){
        int xmin = (int)(x * scw) - (int)(width * scw)/2, 
            ymin = (int)(y * sch) - (int)(height * sch)/2, 
            bw = (int)(width * scw), 
            bh = (int)(height * sch); 
        g.setColor(new Color(55, 55, 55));
        g.fillRect(xmin, (int)(sch * height) - 5, bw, 10);
    }

    void set(float xpos){
        float xmin = x - width/2;
        float xmax = x + width/2;
        if(xpos > xmax) {value = max; return;} else
        if(xpos < xmin) {value = min; return;}
        float untransformed = (xpos - xmin) / (xmax - xmin);
        value = min + untransformed * (max - min);
    }
}
