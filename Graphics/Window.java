package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window {

    public class GraphicPanel extends JComponent implements Runnable {

        private static final long serialVersionUID = 1L;

        int panewidth = 1280, paneheight = 720;

        TableRenderer tr;
        Interact interact;

        GraphicPanel(TableRenderer r, Interact in) {
            tr = r;
            interact = in;
            setPreferredSize(new Dimension(panewidth, paneheight));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(0.1f, 0.3f, 0.1f));
            g.fillRect(0, 0, panewidth, paneheight);
            tr.Render(g);
            interact.render(g);
        }

        public void run() {
            long end;
            while(true) {
                end = System.nanoTime() + 16666666;
                this.repaint();
                while(System.nanoTime() < end);
            }
        }
    }

    JFrame mainwindow;
    GraphicPanel g;
    TableRenderer tr;
    Interact interact;

    public Window(){
        mainwindow = new JFrame("Poker");
        mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        interact = new Interact();
        tr = new TableRenderer();
        g = new GraphicPanel(tr, interact);
        setupInput();
        mainwindow.add(g);
        mainwindow.pack();
        mainwindow.setVisible(true);
    }

    void start(){
        g.run();
    }

    void setupInput(){
        g.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x=e.getX();
                int y=e.getY();
                interact.onMouse(x, y);
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
}
