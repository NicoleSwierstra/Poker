package Graphics;

import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class GUI {
    //button class
    class Button {
        float xmin, xmax, ymin, ymax;
        String label;
        ButtonInterface bi;

        Button(String label, float xmin, float ymin, float xmax, float ymax, ButtonInterface bi){
            this.label = label;
            this.xmin = xmin;
            this.xmax = xmax;
            this.ymin = ymin;
            this.ymax = ymax;
            this.bi = bi;
        }
    }
    
    class Text {
        String text;

        Text(String t){ 
            text = t;
        }
    }

    //the important part
    public List<Button> Buttons;
    public List<Button> bQueue;

    public interface ButtonInterface {void onClick();}

    GUI(){
        Buttons = new ArrayList<Button>();
        bQueue = new ArrayList<Button>();
    }

    //on mouse checks intersect
    void onMouse(float x, float y){
        Buttons.forEach(b -> {
            checkIntersect(b, true, x, y);
        });
    }

    //adds a button
    void queueButton(String label, float xmin, float ymin, float xmax, float ymax, ButtonInterface bi){
        bQueue.add(new Button(label, xmin, ymin, xmax, ymax, bi));
    }

    //adds a text field
    void queueText(String label){

    }

    //clears all buttons
    void applyQueue(){
        Buttons = bQueue;
        bQueue = new ArrayList<Button>();
    }

    //renders all buttons, and render the moused over button specially
    void render(Graphics g, int width, int height){
        //render button
        Buttons.forEach(b -> {
            g.setColor(new Color(255, 255, 255));
            int xmin = (int)(b.xmin * width), xmax = (int)(b.xmax * width),
                ymin = (int)(b.ymin * height), ymax = (int)(b.ymax * height),
                bw = xmax - xmin, bh = ymax - ymin;

            g.fillRect(xmin, ymin, bw, bh);
            g.setColor(new Color(0, 0, 0));
            g.drawRect(xmin, ymin, bw, bh);


            Font font = new Font("Comic Sans MS", 0, height/30);
            FontMetrics metrics = g.getFontMetrics(font);
            int x = xmin + (bw - metrics.stringWidth(b.label)) / 2;
            int y = ymin + ((bh - metrics.getHeight()) / 2) + metrics.getAscent();
            g.setFont(font);
            g.drawString(b.label, x, y);
        });
    }

    //checks intersect of the button, presses it if press is enabled
    boolean checkIntersect(Button b, boolean press, float x, float y){
        boolean isx = x < b.xmax && x > b.xmin;
        boolean isy = y < b.ymax && y > b.ymin;
        if(isx && isy){
            if(press) b.bi.onClick();
            return true;
        }
        return false;
    }
}
