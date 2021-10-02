package Graphics;

import javax.swing.*;
import java.awt.*;

public class Window {

    public class GraphicPanel extends JComponent {

        private static final long serialVersionUID = 1L;

        GraphicPanel() {
            setPreferredSize(new Dimension(640, 480));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }

    JFrame mainwindow;
    GraphicPanel g;

    public Window(){
        mainwindow = new JFrame("Poker");
        mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g = new GraphicPanel();
        mainwindow.add(g);
        mainwindow.setSize(640, 480);
        mainwindow.setBackground(new Color(0, 0, 0));
        mainwindow.setVisible(true);
    }
}
