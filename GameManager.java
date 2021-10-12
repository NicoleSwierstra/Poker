import java.awt.*;
import java.util.Scanner;

class GameManager{
  Graphics g;
  Scanner sc;
  Deck decky;
  CardDrawer drawer;
  Pos[] knownPositions = new Pos[10];



  GameManager(){
    DrawingPanel panel = new DrawingPanel(1000, 1000);
    panel.setBackground(Color.GREEN);
    this.g = panel.getGraphics();
    this.sc = new Scanner(System.in);
    this.drawer = new CardDrawer(g);
    this.decky = new Deck(g);
    decky.RandomiseThisDeck(52);
    //DrawAllCards();

    makeKnownPositions();
    for(int i=0;i<knownPositions.length;i++){
      DrawCardAtPos(i*5,i);
    }

  }



  public void DrawAllCards() {
    int c=0;
    for (int i = 0; i < 5; i++)
      for (int q = 0; q < 13; q++) {
        if (c<52){
          if (decky.getCardAt(c) != null) {
            Card currentCard = decky.getCardAt(c);
            drawer.drawThisCard(currentCard, 51 * q, 71 * i, 1);
            System.out.println("drawing card: " + currentCard.toString());
          } else {
            System.out.println("cardwasnull");
          }
        c++;
        }
        
      
      }
  }

  private void DrawCardAtPos(int c, int p){
    drawer.drawThisCard(decky.getCardAt(c),knownPositions[p]);
  }



  private void makeKnownPositions(){
    knownPositions[0] = new Pos(125,50);
    knownPositions[1] = new Pos(176,50);
    knownPositions[2] = new Pos(227,50);
    knownPositions[3] = new Pos(278,50);
    knownPositions[4] = new Pos(329,50);

    knownPositions[5] = new Pos(1,150,2);
    knownPositions[6] = new Pos(102,150,2);
    knownPositions[7] = new Pos(203,150,2);
    knownPositions[8] = new Pos(304,150,2);    
    knownPositions[9] = new Pos(405,150,2);
  }

}