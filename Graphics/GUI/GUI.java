package Graphics.GUI;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.KeyEvent;

public class GUI {

    //the important part
    public List<GUIElement> Elements;
    public List<GUIElement> eQueue;
    public List<GUIElement> saved;
    public TextBox active;
    long starttime;

    public GUI(){
        Elements = new ArrayList<GUIElement>();
        eQueue =  new ArrayList<GUIElement>();
        starttime = System.currentTimeMillis();
    }

    //on mouse checks intersect
    public void onMouse(float x, float y){
        Elements.forEach(b -> {
            if(b instanceof Button || b instanceof TextBox)
                checkIntersect(b, true, x, y);
            else if(b instanceof GUIList) {
                GUIList h = ((GUIList)b);
                for(int i = 0; i < h.list.size(); i++){
                    if(h.list.get(i) instanceof Button){
                        checkIntersect(h.list.get(i), true, x, y);
                    }
                }
            }
        });
    }

    //adds a button
    public void queueButton(String label, float x, float y, float width, float height, ButtonInterface bi){
        eQueue.add(new Button(label, x, y, width, height, bi));
    }
    //adds a button
    public void queueButton(String label, String icon, float x, float y, float width, float height, ButtonInterface bi){
        eQueue.add(new IconButton(label, icon, x, y, width, height, bi));
    }

    //adds a text
    public void queueText(String label, float x, float y, float width, float height){
        eQueue.add(new Text(label, x, y, width, height));
    }

    //adds a text field
    public TextBox queueTextBox(String text, float x, float y, float width, float height){
        TextBox t = new TextBox(text, x, y, width, height); eQueue.add(t); return t;
    }

    //adds an image
    public Texture queueTexture(BufferedImage bi, float x, float y, float width, float height){
        Texture t = new Texture(bi, x, y, width, height); eQueue.add(t); return t;
    }

    //adds a list
    public GUIList queueList(boolean horizontal, int rows, float spacing, float x, float y, float width, float height){
        GUIList l = new GUIList(horizontal, rows, spacing, x, y, width, height); eQueue.add(l); return l;
    }

    public void saveGUI(){
        saved = Elements;
    }

    public void applyOld(){
        Elements = saved;
    }

    //clears all buttons
    public void applyQueue(){
        Elements = eQueue;
        eQueue = new ArrayList<GUIElement>();
    }

    //renders all buttons, and render the moused over button specially
    public void render(Graphics g, int width, int height, float xmouse, float ymouse){
        Graphics2D g2d = (Graphics2D) g;
        Stroke s = g2d.getStroke();
        g2d.setStroke(new BasicStroke(1.5f));
        Elements.forEach(e -> {e.Render(g, this, width, height, xmouse, ymouse);});
        g2d.setStroke(s);
    }

    //checks intersect of the button, presses it if press is enabled
    boolean checkIntersect(GUIElement b, boolean press, float x, float y){
        float xmin = b.x - (b.width/2.0f), ymin = b.y - (b.height/2.0f), 
            xmax = xmin + b.width, ymax = ymin + b.height;
        boolean isx = x < xmax && x > xmin;
        boolean isy = y < ymax && y > ymin;
        if(isx && isy){
            if(press) {
                if(b instanceof Button){
                    ((Button)b).bi.onClick();
                }
                else if (b instanceof TextBox){
                    active = (TextBox)b;
                }
            }
            return true;
        }
        return false;
    }

    public void textInput(KeyEvent e){
        if (active == null) return;
        char c = e.getKeyChar();
        if(c == KeyEvent.VK_BACK_SPACE){
            if(!(active.field.length() > 0)) return;
            active.field = active.field.substring(0, active.field.length() - 1);
        }
        else if (c == KeyEvent.VK_ENTER)
            active = null;
        else if(c != KeyEvent.CHAR_UNDEFINED)
            active.field += c;
    }
}