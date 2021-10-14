import java.awt.*;
import java.util.Scanner;

class GameManager {
  Graphics g;
  Scanner sc;
  Deck decky;
  CardDrawer drawer;
  Pos[] knownPositions = new Pos[10];

  GameManager(){
    initiate();
    int i=0;
    String c="y";
    while(c.equals("y")){
      askAndSwitch();
      System.out.println("continue?(y/n)... ");
      c = sc.next();
      reRollBoard();
      i++;
    }
    
    int total=0;
    for(int q=5;q<10;q++){
      Card temp = decky.getCardAt(q);
      total = total+temp.value();
    }
    
    System.out.println("Congrats you got to the end of the game");
    System.out.println("And you got a score of "+total+" in "+i+" turns");
  }

  

  public void reRollBoard() {
    System.out.println("reRolling board...");
    Card[] temp = new Card[5];
    for (int i = 0; i < 5; i++) {
      temp[i] = decky.getCardAt(i + 5);
    }
    decky.RandomiseThisDeck(104);
    for (int q = 0; q < 5; q++) {
      int o = decky.findSameCardAs(temp[q]);
      decky.swapTwoCards(o, q + 5);
    }
    for (int i = 0; i < knownPositions.length; i++) {
      drawer.drawThisCard(decky.getCardAt(i), knownPositions[i]);
    }

  }

  private void askAndSwitch() {
    System.out.println("What two cards would you like to switch? \n enter pos of a card on the board (i.e 0-4): ");
    int one = sc.nextInt();
    System.out.println("Enter the postion of a card in your hand (I.e 0-4): ");
    int two = sc.nextInt() + 5;
    while (two >= 10 || one >= 5 || one < 0 || two < 5) {
      System.out.println("error invalid positions, try again \n Enter pos of a card on the board (i.e 0-4): ");
      one = sc.nextInt();
      System.out.println("Enter the postion of a card in your hand (I.e 0-4):");
      two = sc.nextInt() + 5;
    }
    System.out.println("Cards sucessfully swapped! ");
    decky.swapTwoCards(one, two);
    drawer.drawThisCard(decky.getCardAt(one), knownPositions[one]);
    drawer.drawThisCard(decky.getCardAt(two), knownPositions[two]);
    // System.out.println("yep it worked");
  }

  private void initiate() {
    DrawingPanel panel = new DrawingPanel(1000, 1000);
    panel.setBackground(Color.GREEN);
    this.g = panel.getGraphics();
    this.sc = new Scanner(System.in);
    this.drawer = new CardDrawer(g);
    this.decky = new Deck(g);
    decky.RandomiseThisDeck(104);
    makeKnownPositions();
    for (int i = 0; i < knownPositions.length; i++) {
      drawer.drawThisCard(decky.getCardAt(i), knownPositions[i]);
    }

  }



  private void makeKnownPositions() {
    knownPositions[0] = new Pos(125, 50);
    knownPositions[1] = new Pos(176, 50);
    knownPositions[2] = new Pos(227, 50);
    knownPositions[3] = new Pos(278, 50);
    knownPositions[4] = new Pos(329, 50);

    knownPositions[5] = new Pos(11, 150, 2);
    knownPositions[6] = new Pos(112, 150, 2);
    knownPositions[7] = new Pos(213, 150, 2);
    knownPositions[8] = new Pos(314, 150, 2);
    knownPositions[9] = new Pos(415, 150, 2);
  }

}