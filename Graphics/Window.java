package Graphics;

import javax.swing.*;
import java.awt.*;

public class Window {

    public class GraphicPanel extends JComponent {

        private static final long serialVersionUID = 1L;

        Window win;

        GraphicPanel(Window w) {
            win = w;
            setPreferredSize(new Dimension(640, 480));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(0.1f, 0.3f, 0.1f));
            g.fillRect(0, 0, 640, 480);
            win.Render(g);
        }
    }

    JFrame mainwindow;
    GraphicPanel g;
    CardRenderer cr;

    public Window(){
        mainwindow = new JFrame("Poker");
        mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cr = new CardRenderer();
        g = new GraphicPanel(this);
        mainwindow.add(g);
        mainwindow.pack();
        mainwindow.setBackground(new Color(0, 0, 0));
        mainwindow.setVisible(true);
    }

    void Render(Graphics g){
        cr.drawCard(g, 0  , 0, 0, 0, 100);
        cr.drawCard(g, 100, 0, 1, 6, 100);
        cr.drawCard(g, 200, 0, 2, 2, 100);
        cr.drawCard(g, 300, 0, 3, 11, 100);
    }
}
