package GameLogic;

import java.util.ArrayList;
import java.util.List;

public class handValue{
    public int highcard, value;
    handValue(int high, int value){
        this.value = value;
        highcard = high;
    }

    handValue(List<Card> cards, int highcard){
        List<Card> hand_sorted = new ArrayList<Card>();
        hand_sorted.addAll(cards);
        for(int i = hand_sorted.size(); i < 7; i++){
            hand_sorted.add(new Card(-1, -1));
        }
        
        //sorting
        int lowest_index;

        for (int i = 0; i < hand_sorted.size() - 1; i++) {
            lowest_index = i;
            for (int j = i; j < hand_sorted.size(); j++) {
                if (hand_sorted.get(j).num < hand_sorted.get(lowest_index).num) {
                    lowest_index = j;
                }
            }
            if (lowest_index != i) {
                Card temp = hand_sorted.get(i);
                hand_sorted.set(i, hand_sorted.get(lowest_index));
                hand_sorted.set(lowest_index, temp);
            }
        }

        //evaluating things
        int highest = 0;
        for(int i = 0; i < 3; i++){
            int newvalue = evaluate5(hand_sorted.subList(i, i + 5));
            if(newvalue > highest)
                highest = newvalue;
        }
        if(evaluateFlush(hand_sorted) > highest){
            highest = 5;
        }
        value = Math.max(highest, evaluateFlush(hand_sorted));
    }

    static int evaluate5(List<Card> cards){
        //7 = full house
        //6 = quadruple
        //5 = flush
        //4 = straight
        //3 = triple
        //2 = twopair
        //1 = pair
        //0 = highcard

        boolean pair01 = cards.get(0).num == cards.get(1).num, 
            pair12 = cards.get(1).num == cards.get(2).num, 
            pair23 = cards.get(2).num == cards.get(3).num, 
            pair34 = cards.get(3).num == cards.get(4).num;
        
        //full house
        if((pair01 && pair12 && pair34) || (pair23 && pair34 && pair01)) {
            return 7;
        }
        
        //quadruple
        if((pair12 && pair23) && (pair01 || pair34)) {
            return 6;
        }

        //straight
        if (cards.get(1).num == (cards.get(0).num + 1) &&
            cards.get(2).num == (cards.get(1).num + 1) &&
            cards.get(3).num == (cards.get(2).num + 1) &&
            cards.get(4).num == (cards.get(3).num + 1) ){
                return 4;
        }

        //triple
        if((pair01 && pair12) || (pair12 && pair23) || (pair23 && pair34)) {
            return 3;
        }

        //twopair
        if((pair01 && pair12) || (pair12 && pair23) || (pair23 && pair34)) {
            return 2;
        }

        //pair
        if(pair01 || pair12 || pair23 || pair34) {
            return 1;
        }

        return 0;
    }

    static int evaluateFlush(List<Card> cards){
        int[] suits = {0,0,0,0};
        for(int i = 0; i < cards.size(); i++) suits[cards.get(i).suit]++;
        if(suits[0] >= 5 || suits[1] >= 5 || suits[2] >= 5 || suits[3] >= 5) return 5;
        return 0;
    }
}    