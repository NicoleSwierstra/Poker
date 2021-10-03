package Graphics;

import java.awt.*;

//class playerPos {
//    final static float[][] pos = {
//        {0.0f, 0.5f}, //right
//        {0.0f, 0.0f}, //top r
//        {0.0f, 0.0f}, //top c
//        {0.0f, 0.0f}, //top l
//        {0.0f, 0.0f}, //left
//        {0.0f, 0.0f}, //bottom l
//        {0.0f, 0.0f}, //bottom c
//        {0.0f, 0.0f} //bottom r
//    };
//}

public class TableRenderer {
    CardRenderer cr;
    int x, y, suit, num;

    TableRenderer(){
        cr = new CardRenderer();
        x = 0;
    }

    void Render(Graphics g){
        cr.drawCard(g, true, x, y, suit, num, 100);
    };

    void renderPlayer(Graphics2D g, int x, int y, float r){
        g.rotate(Math.toRadians(r));
        g.translate(x, y);
    }
}
