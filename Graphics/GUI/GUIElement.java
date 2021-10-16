package Graphics.GUI;

import java.awt.Graphics;

public abstract class GUIElement{
    float x, y, width, height;

    GUIElement(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    abstract void Render(Graphics g, GUI gui, int scw, int sch, float xmouse, float ymouse);
}
