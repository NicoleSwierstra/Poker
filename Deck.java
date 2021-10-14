import java.awt.*;
import java.lang.Math;

class Deck {
  Card[] deckOfCards = new Card[54];
  Graphics g;
  int scale;

  public Deck(Graphics s) {
    createOrganisedDeck();
    this.g = s;
  }

  public void createOrganisedDeck() {
    MakeAllCardsOfOneSute('S', 0);
    MakeAllCardsOfOneSute('D', 13);
    MakeAllCardsOfOneSute('C', 26);
    MakeAllCardsOfOneSute('H', 39);
    System.out.println("Deck sucsessfully created!");
  }

  public void MakeAllCardsOfOneSute(char suite, int startingCardCord) {
    for (int q = 0; q < 13; q++) {
      Card temp = new Card(suite, q + 1);
      deckOfCards[startingCardCord + q] = temp;
    }
    startingCardCord = startingCardCord + 13;
    // System.out.println("allCardsOfSuite "+suite+" createdUpToCord
    // "+(startingCardCord+12) );
  }

  public Card getCardAt(int cord) {
    return deckOfCards[cord];
  }

  public void RandomiseThisDeck(int AmountOfSuffles) {
    for (int i = 0; i < AmountOfSuffles; i++) {
      double r = Math.random() * (52 - 0 + 1) + 0;
      int r1 = (int) r;
      r = Math.random() * (52 - 0 + 1) + 0;
      int r2 = (int) r;
      if (deckOfCards[r1] != null && deckOfCards[r2] != null) {
        Card temp = deckOfCards[r1];
        deckOfCards[r1] = deckOfCards[r2];
        deckOfCards[r2] = temp;
      }
    }
  }

  public void swapTwoCards(int one, int two) {

    if (deckOfCards[one] != null && deckOfCards[two] != null) {
      Card temp = deckOfCards[one];
      deckOfCards[one] = deckOfCards[two];
      deckOfCards[two] = temp;
    }
  }
  public int findSameCardAs(Card card){
    for(int i=0;i<52;i++){
      if (deckOfCards[i]==card){
        //System.out.println("card found at: "+i);
        return i;
        
      }else{
        //System.out.println("error card not found at: "+i);
      }
      
    }
    return 1;
  }

}