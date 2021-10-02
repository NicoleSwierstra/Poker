package GameLogic;
import java.util.*;

public class Deck {
    List<Card> cards;
    static Random rand;

    Deck(){
        rand = new Random();
        reset();
    }
    
    //shuffling and generating the deck are the same thing
    void reset(){
        cards = new ArrayList<Card>();
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 13; j++){
                cards.add(new Card(i, j));
            }
        }
        for(int i = 0; i < 52; i++){
            swap(rand.nextInt(52), rand.nextInt(52));
        }
    }

    void swap(int a, int b){
        Card temp = cards.get(a);
        cards.set(a, cards.get(b));
        cards.set(b, temp);
    }
    
    //copies from start(inclusive) to end (exclusive)
    public List<Card> copyAndRemove(int start, int end){
        List<Card> cardstocopy = new ArrayList<Card>();
        cardstocopy.addAll(cards.subList(start, end));
        for(int i = end - 1; i >= start; i--){
            cards.remove(i);
        }
        return cardstocopy;
    }

    //copies last num of cards from back of array
    public List<Card> copyAndRemove(int num){
        return copyAndRemove(cards.size() - num, cards.size());
    }

    //Debug method
    public void printDeck(){
        for(int i = 0; i < 4; i++) {
            cards.subList(i * 13, (i + 1) * 13)
                .forEach(card -> {card.print();});
            System.out.println();
        }
    }
}
