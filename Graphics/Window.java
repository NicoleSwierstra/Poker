package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window {

    public class GraphicPanel extends JComponent implements Runnable {

        private static final long serialVersionUID = 1L;

        int panewidth = 1440, paneheight = 720;

        boolean renderGame;

        GraphicsTable tr;
        GUI interact;

        GraphicPanel(GraphicsTable r, GUI in) {
            tr = r;
            interact = in;
            setPreferredSize(new Dimension(panewidth, paneheight));
            JComponent pane = this;
            pane.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent componentEvent) {
                    paneheight = pane.getHeight();
                    panewidth = pane.getWidth();
                }
            });
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(0.1f, 0.3f, 0.1f));
            g.fillRect(0, 0, panewidth, paneheight);
            if(renderGame) tr.Render(g);
            interact.render(g, panewidth, paneheight);
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
    GraphicsTable tr;
    GUI gui;

    public Window(){
        mainwindow = new JFrame("Poker");
        mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui = new GUI();
        tr = new GraphicsTable(this);
        g = new GraphicPanel(tr, gui);
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
                float x = e.getX() / (float)g.panewidth;
                float y = e.getY() / (float)g.paneheight;
                gui.onMouse(x, y);
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
