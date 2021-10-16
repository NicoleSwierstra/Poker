package Graphics.GUI;

import java.util.List;
import java.util.ArrayList;
import java.awt.image.*;
import java.awt.Graphics;

public class GUIList extends GUIElement{
    boolean horizontal;
    int rows;
    float spacing;
    List<GUIElement> list;


    GUIList(boolean horizontal, int rows, float spacing, float x, float y, float width, float height){
        super(x, y, width, height);
        this.horizontal = horizontal;
        this.rows = rows;
        this.spacing = spacing;
        list = new ArrayList<GUIElement>();
    }

    List<GUIElement> getList(){
        return list;
    }

    void Render(Graphics g, GUI gui, int scw, int sch, float xmouse, float ymouse){
        if(list.size() == 0) return;
        list.forEach(e -> {
            e.Render(g, gui, scw, sch, xmouse, ymouse);
        });
    }

    public void removeElement(int element){
        list.remove(element);
        for(int i = element; i < list.size(); i++){
            float xx = (horizontal) ? x + (spacing * i) : x,
                yy = (horizontal) ? y : y + (spacing * i);
            list.get(i).x = xx;
            list.get(i).y = yy;
        }
    }

    //adds a button
    public void addButton(String label, ButtonInterface bi){
        float xx = (horizontal) ? x + (spacing * list.size()) : x,
            yy = (horizontal) ? y : y + (spacing * list.size());
        list.add(new Button(label, xx, yy, width, height, bi));
    }

    //adds a text
    void addText(String label){
        float xx = (horizontal) ? x + (spacing * list.size()) : x,
            yy = (horizontal) ? y : y + (spacing * list.size());
        list.add(new Text(label, xx, yy, width, height));
    }

    //adds an image
    void addTexture(BufferedImage bi){
        float xx = (horizontal) ? x + (spacing * list.size()) : x,
            yy = (horizontal) ? y : y + (spacing * list.size());
        list.add(new Texture(bi, xx, yy, width, height));
    }
}
