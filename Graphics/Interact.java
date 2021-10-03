package Graphics;

import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class Interact {
    //button class
    class Button {
        int xmin, xmax, ymin, ymax;
        ButtonInterface bi;

        Button(int xmin, int ymin, int xmax, int ymax, ButtonInterface bi){
            this.xmin = xmin;
            this.xmax = xmax;
            this.ymin = ymin;
            this.ymax = ymax;
            this.bi = bi;
        }
    }

    //the important part
    public List<Button> Buttons;

    public interface ButtonInterface {void onClick();}

    Interact(){
        Buttons = new ArrayList<Button>();
    }

    //on mouse checks intersect
    void onMouse(int x, int y){
        Buttons.forEach(b -> {
            checkIntersect(b, true, x, y);
        });
    }

    //adds a button
    void addButton(int xmin, int ymin, int xmax, int ymax, ButtonInterface bi){
        Buttons.add(new Button(xmin, ymin, xmax, ymax, bi));
    }

    //clears all buttons
    void clearButtons(){
        Buttons.clear();
    }

    //renders all buttons, and render the moused over button specially
    void render(Graphics g){
        Buttons.forEach(b -> {
            g.setColor(new Color(255, 255, 255));
            g.fillRect(b.xmin, b.ymin, b.xmax - b.xmin, b.ymax - b.ymin);
            g.setColor(new Color(0, 0, 0));
            g.drawRect(b.xmin, b.ymin, b.xmax - b.xmin, b.ymax - b.ymin);
        });
    }

    //checks intersect of the button, presses it if press is enabled
    boolean checkIntersect(Button b, boolean press, int x, int y){
        boolean isx = x < b.xmax && x > b.xmin;
        boolean isy = y < b.ymax && y > b.ymin;
        if(isx && isy){
            if(press) b.bi.onClick();
            return true;
        }
        return false;
    }
}
