package Graphics;

import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class GUI {
    public class GUIElement{
        float x, y, width, height;
    }

    class Button extends GUIElement {
        String label;
        ButtonInterface bi;

        Button(String label, float x, float y, float width, float height, ButtonInterface bi){
            this.label = label;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.bi = bi;
        }
    }
    
    class Text extends GUIElement {
        String text;

        Text(String t, float x, float y, float width, float height){ 
            text = t;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    //the important part
    public List<GUIElement> Elements;
    public List<GUIElement> eQueue;

    public interface ButtonInterface {void onClick();}

    GUI(){
        Elements = new ArrayList<GUIElement>();
        eQueue =  new ArrayList<GUIElement>();
    }

    //on mouse checks intersect
    void                                                                                                                                                                                                                                                                                                                                    onMouse(float x, float y){
        Elements.forEach(b -> {
            if(b instanceof Button)
                checkIntersect((Button)b, true, x, y);
        });
    }

    //adds a button
    void queueButton(String label, float x, float y, float width, float height, ButtonInterface bi){
        eQueue.add(new Button(label, x, y, width, height, bi));
    }

    //adds a text field
    void queueText(String label, float x, float y, float width, float height){
        eQueue.add(new Text(label, x, y, width, height));
    }

    //clears all buttons
    void applyQueue(){
        Elements = eQueue;
        eQueue = new ArrayList<GUIElement>();
    }

    //renders all buttons, and render the moused over button specially
    void render(Graphics g, int width, int height, float xmouse, float ymouse){
        //render button
        Elements.forEach(b -> {
            g.setColor(new Color(255, 255, 255, 125));
            int xmin = (int)(b.x * width) - (int)(b.width * width)/2, ymin = (int)(b.y * height) - (int)(b.height * height)/2, 
                bw = (int)(b.width * width), bh = (int)(b.height * height);

            if (b instanceof Button){
                Font font = new Font("Comic Sans MS", 0, height/30);
                FontMetrics metrics = g.getFontMetrics(font);
                if(checkIntersect((Button)b, false, xmouse, ymouse)) 
                    g.setColor(new Color(255, 0, 0, 125));
                
                g.fillRect(xmin, ymin, bw, bh);
                g.setColor(new Color(0, 0, 0));
                g.drawRect(xmin, ymin, bw, bh);

                int x = xmin + (bw - metrics.stringWidth(((Button)b).label)) / 2;
                int y = ymin + ((bh - metrics.getHeight()) / 2) + metrics.getAscent();
                g.setFont(font);
                g.drawString(((Button)b).label, x, y);
            }

            if (b instanceof Text){
                Font font = new Font("Comic Sans MS", 0, (int)(b.height * height));
                FontMetrics metrics = g.getFontMetrics(font);
                int x = xmin + (bw - metrics.stringWidth(((Text)b).text)) / 2;
                int y = ymin + ((bh - metrics.getHeight()) / 2) + metrics.getAscent();
                g.setFont(font);
                g.setColor(new Color(0, 0, 0, 255));
                g.drawString(((Text)b).text, x + 3, y + 3);
                g.setColor(new Color(255, 255, 255, 255));
                g.drawString(((Text)b).text, x, y);
            }
        });
    }

    //checks intersect of the button, presses it if press is enabled
    boolean checkIntersect(Button b, boolean press, float x, float y){
        float xmin = b.x - (b.width/2.0f), ymin = b.y - (b.height/2.0f), 
            xmax = xmin + b.width, ymax = ymin + b.height;
        boolean isx = x < xmax && x > xmin;
        boolean isy = y < ymax && y > ymin;
        if(isx && isy){
            if(press) b.bi.onClick();
            //System.out.println(xmax + "," + ymax + " || " + x + ", " + y + " || " + press);
            return true;
        }
        return false;
    }
}
