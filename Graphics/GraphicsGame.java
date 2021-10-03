package Graphics;

public class GraphicsGame {
    Window w;

    public GraphicsGame(){
        w = new Window();
        addTransforms();
        w.start();
    }

    void addTransforms(){
        //y
        w.interact.addButton(620, 320, 660, 340, () -> {w.tr.y -= 10;});
        w.interact.addButton(620, 380, 660, 400, () -> {w.tr.y += 10;});
        //x
        w.interact.addButton(600, 340, 620, 380, () -> {w.tr.x -= 10;});
        w.interact.addButton(660, 340, 680, 380, () -> {w.tr.x += 10;});
        //suit/num
        w.interact.addButton(620, 340, 640, 360, () -> {w.tr.suit++; w.tr.suit %= 4;});
        w.interact.addButton(640, 340, 660, 360, () -> {w.tr.num++;  w.tr.num %= 13;});
        w.interact.addButton(620, 360, 640, 380, () -> {w.tr.suit--; w.tr.suit += 4; w.tr.suit %= 4;});
        w.interact.addButton(640, 360, 660, 380, () -> {w.tr.num--;  w.tr.num += 13; w.tr.num %= 13;});
    }
}
