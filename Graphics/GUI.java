package Graphics;

import java.util.List;
import java.util.ArrayList;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.KeyEvent;

public class GUI {
    
    public class GUIElement{
        float x, y, width, height;

        GUIElement(float x, float y, float width, float height){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    class Button extends GUIElement {
        String label;
        ButtonInterface bi;

        Button(String label, float x, float y, float width, float height, ButtonInterface bi){
            super(x, y, width, height);
            this.label = label;
            this.bi = bi;
        }
    }

    class IconButton extends Button {
        BufferedImage icon;

        IconButton(String label, String icon, float x, float y, float width, float height, ButtonInterface bi){
            super(label, x, y, width, height, bi);
            try {
                this.icon = ImageIO.read(new File(icon));
            } catch (IOException e) {
                System.out.println("FAIL");
            }
        }
    }
    
    class Text extends GUIElement {
        String text;

        Text(String t, float x, float y, float width, float height){ 
            super(x, y, width, height);
            text = t;
        }
    }

    class TextBox extends GUIElement {
        String field;

        TextBox(String text, float x, float y, float width, float height){
            super(x, y, width, height);
            field = text;
        }
    }

    class Texture extends GUIElement{
        BufferedImage bi;
        
        Texture(BufferedImage bi, float x, float y, float width, float height){
            super(x, y, width, height);
            this.bi = bi;
        }
    };

    class GUIList extends GUIElement{
        boolean horizontal;
        int rows;
        float spacing;
        List<GUIElement> list;

        GUIList(boolean horizontal, int rows, float spacing, float x, float y, float width, float height){
            super(x, y, width, height);
            this.horizontal = horizontal;
            this.rows = rows;
            this.spacing = spacing;
        }

        List<GUIElement> getList(){
            return list;
        }

        //adds a button
        void addButton(String label, ButtonInterface bi){
            float xx = (!horizontal) ? x + (spacing * list.size()) : x,
                yy = (horizontal) ? y : y + (spacing * list.size());
            list.add(new Button(label, xx, yy, width, height, bi));
        }

        //adds a text
        void addText(String label){
            float xx = (!horizontal) ? x + (spacing * list.size()) : x,
                yy = (horizontal) ? y : y + (spacing * list.size());
            list.add(new Text(label, xx, yy, width, height));
        }

        //adds an image
        void addTexture(BufferedImage bi){
            float xx = (!horizontal) ? x + (spacing * list.size()) : x,
                yy = (horizontal) ? y : y + (spacing * list.size());
            list.add(new Texture(bi, xx, yy, width, height));
        }
    }

    //the important part
    public List<GUIElement> Elements;
    public List<GUIElement> eQueue;
    public List<GUIElement> saved;
    public TextBox active;
    long starttime;

    public interface ButtonInterface {void onClick();}

    GUI(){
        Elements = new ArrayList<GUIElement>();
        eQueue =  new ArrayList<GUIElement>();
        starttime = System.currentTimeMillis();
    }

    //on mouse checks intersect
    void onMouse(float x, float y){
        Elements.forEach(b -> {
            if(b instanceof Button || b instanceof TextBox)
                checkIntersect(b, true, x, y);
        });
    }

    //adds a button
    void queueButton(String label, float x, float y, float width, float height, ButtonInterface bi){
        eQueue.add(new Button(label, x, y, width, height, bi));
    }
    //adds a button
    void queueButton(String label, String icon, float x, float y, float width, float height, ButtonInterface bi){
        eQueue.add(new IconButton(label, icon, x, y, width, height, bi));
    }

    //adds a text
    void queueText(String label, float x, float y, float width, float height){
        eQueue.add(new Text(label, x, y, width, height));
    }

    //adds a text field
    TextBox queueTextBox(String text, float x, float y, float width, float height){
        TextBox t = new TextBox(text, x, y, width, height); eQueue.add(t); return t;
    }

    //adds an image
    Texture queueTexture(BufferedImage bi, float x, float y, float width, float height){
        Texture t = new Texture(bi, x, y, width, height); eQueue.add(t); return t;
    }

    //adds a list
    GUIList queueList(boolean horizontal, int rows, float spacing, float x, float y, float width, float height){
        GUIList l = new GUIList(horizontal, rows, spacing, x, y, width, height); eQueue.add(l); return l;
    }

    void saveGUI(){
        saved = Elements;
    }

    void applyOld(){
        Elements = saved;
    }

    //clears all buttons
    void applyQueue(){
        Elements = eQueue;
        eQueue = new ArrayList<GUIElement>();
    }

    //renders a button
    void renderButton(Graphics g, Button b, int xmin, int ymin, int bw, int bh, float xmouse, float ymouse){
        Font font = new Font("Comic Sans MS", 0, bh/2);
        FontMetrics metrics = g.getFontMetrics(font);
        if(checkIntersect((Button)b, false, xmouse, ymouse)) 
            g.setColor(new Color(255, 0, 0, 125));
        
        ((Graphics2D)g).fillRoundRect(xmin, ymin, bw, bh, 10, 10);
        g.setColor(new Color(0, 0, 0));
        ((Graphics2D)g).drawRoundRect(xmin, ymin, bw, bh, 10, 10);

        if(b instanceof IconButton){
            int x = xmin + (bw - metrics.stringWidth(((Button)b).label)) / 2;
            int y = ymin + metrics.getAscent() + ((5 * bh)/6);
            g.setFont(font);
            g.drawString(((IconButton)b).label, x, y);
            g.drawImage(((IconButton)b).icon, xmin, ymin, bw, (7 * bh)/8, null);
        }
        else {
            int x = xmin + (bw - metrics.stringWidth(((Button)b).label)) / 2;
            int y = ymin + ((bh - metrics.getHeight()) / 2) + metrics.getAscent();
            g.setFont(font);
            g.drawString(((Button)b).label, x, y);
        }
    }

    //renders text
    void renderText(Graphics g, Text b, int xmin, int ymin, int bw, int bh){
        float time = ((starttime - System.currentTimeMillis()) / 4000.0f);
        Color rainbow = Color.getHSBColor(time % 1.0f, 1.0f, 0.5f);
        Font font = new Font("Comic Sans MS", 0, (int)(bh));
        FontMetrics metrics = g.getFontMetrics(font);
        int x = xmin + (bw - metrics.stringWidth(((Text)b).text)) / 2;
        int y = ymin + ((bh - metrics.getHeight()) / 2) + metrics.getAscent();

        g.setFont(font);
        g.setColor(new Color(0, 0, 0, 255));
        g.drawString(((Text)b).text, x + 3, y + 3);
        g.setColor(rainbow);
        g.drawString(((Text)b).text, x, y);
    }

    void renderTextBox(Graphics g, TextBox b, int xmin, int ymin, int bw, int bh){
        Font font = new Font("Comic Sans MS", 0, (int)(bh * 0.8f));
        FontMetrics metrics = g.getFontMetrics(font);
        int x = xmin + (bw  / 20);
        int y = ymin + (((int)(bh * 0.9f) - metrics.getHeight()) / 2) + metrics.getAscent();
        if(b == active) 
            g.setColor(new Color(255, 0, 0, 125));

        ((Graphics2D)g).fillRoundRect(xmin, ymin, bw, bh, 10, 10);
        g.setColor(new Color(0, 0, 0));
        ((Graphics2D)g).drawRoundRect(xmin, ymin, bw, bh, 10, 10);

        g.setFont(font);
        g.setColor(new Color(0, 0, 0, 255));
        g.drawString(((TextBox)b).field, x, y);
    }

    void renderTexture(Graphics g, Texture b, int xmin, int ymin, int bw, int bh){
        g.drawImage(b.bi, xmin, ymin, bw, bw, null);
    }

    void renderGUIlist(Graphics g, GUIList l, int width, int height, float xmouse, float ymouse){
        l.list.forEach(b -> {
            renderElement(g, b, width, height, xmouse, ymouse);
        });
    }

    void renderElement(Graphics g, GUIElement b, int width, int height, float xmouse, float ymouse){
        int xmin = (int)(b.x * width) - (int)(b.width * width)/2, 
            ymin = (int)(b.y * height) - (int)(b.height * height)/2, 
            bw = (int)(b.width * width), 
            bh = (int)(b.height * height);

        if (b instanceof Button)
            renderButton(g, (Button)b, xmin, ymin, bw, bh, xmouse, ymouse);
        else if (b instanceof Text)
            renderText(g, (Text)b, xmin, ymin, bw, bh);
        else if (b instanceof TextBox)
            renderTextBox(g, (TextBox)b, xmin, ymin, bw, bh);
        else if (b instanceof Texture)
            renderTexture(g, (Texture)b, xmin, ymin, bw, bh);
        else if (b instanceof GUIList)              
            renderGUIlist(g, (GUIList)b, width, height, xmouse, ymouse);
    }

    //renders all buttons, and render the moused over button specially
    void render(Graphics g, int width, int height, float xmouse, float ymouse){
        Graphics2D g2d = (Graphics2D) g;
        Stroke s = g2d.getStroke();
        g2d.setStroke(new BasicStroke(1.5f));
        //render button
        Elements.forEach(b -> {
            g.setColor(new Color(255, 255, 255, 125));
            renderElement(g, b, width, height, xmouse, ymouse);
        });
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

    void textInput(KeyEvent e){
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
