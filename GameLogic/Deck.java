package GameLogic;
import java.util.*;

public class Deck {
  public int dealCounter = 0;
  Random rand = new Random();
  public Card[] deck = new Card[52];

  public Deck(){
    genDeck();
    shuffle();
  }

  public Deck(boolean unshuffled){
    if(unshuffled){
      genDeck();
    } else {
      genDeck();
      shuffle();
    }
  }

  public Deck(byte[] load){

  }

  void genDeck(){
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 13; j++){
        deck[(i*13)+j] = new Card(i, j);
      }
    }
  }

  public void shuffle(){
    Card temp;
    for(int i = 51; i > 0; i--){
      int randInt = rand.nextInt(i+1);
      temp = deck[i];
      deck[i] = deck[randInt];
      deck[randInt] = temp;
    }
    dealCounter = 0;
  }

  public Card[] deal(int amount){
    Card[] output = new Card[amount];
    for(int i = dealCounter; i < dealCounter + amount; i++){
      output[i - dealCounter] = deck[i];
    }
    dealCounter += amount;
    return output;
  }

  public void printDeck(){
    for(Card var : deck){
      var.print();
    }
  }
}