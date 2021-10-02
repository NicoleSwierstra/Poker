import java.util.*;

public class Player {
    public String name;
    public int money;
    Table table;
    List<Card> hand;
    boolean playing;
    int deficit;
    int wins;

    //constructor, obviously
    public Player(String name, Table table){
        this.name = name;
        this.table = table;
        money = 20;
    }

    //oh boy
    public void takeTurn(Scanner sc) {
        String getstring;
        boolean h = true;
        System.out.println("b # to bet, c to check, f to fold");
        System.out.println("you must bet at least " + deficit + " to continue");
        while (h) {
            getstring = sc.nextLine();
            try {
                switch(getstring.split(" ")[0]){
                    case "b":
                        int b = Integer.valueOf(getstring.split(" ")[1]);
                        if(b >= deficit){
                            table.raiseDeficit(b - deficit);
                            bet(b);
                        }
                            
                        else {
                            System.out.println("bet not high enough, please enter a legal bet of at least $" 
                                + deficit + " or fold");
                            continue;
                        }
                        break;
                    case "c":
                        if(deficit == 0)
                            bet(0);
                        else {
                            System.out.println("checking is not allowed, please enter a legal bet of at least $" 
                                + deficit + "or fold");
                            continue;
                        }
                        break;
                    case "f":
                        playing = false;
                        break;
                    default:
                        throw new Exception();
                }
                h = false;
            }
            catch(Exception e){
                System.out.println("Please enter a legal command");
            }
        }
    }

    //betting method
    private void bet(int amount){
        table.pot += amount;
        money -= amount;
        deficit -= amount;
    }

    //deals cards to player
    public void dealTo(List<Card> cards){
        hand = cards;
    }

    //gets the value of the hand
    public handValue handvalue(){
        //sorts the hand
        List<Card> hand_sorted = new ArrayList<Card>();
        hand_sorted.addAll(hand);
        hand_sorted.addAll(table.community);
        int lowest_index;

        for (int i = 0; i < 6; i++) {
            lowest_index = i;
            for (int j = i; j < 7; j++) {
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

        hand_sorted.forEach(card -> {card.print();});

        int highest = 0;
        int highcard = hand_sorted.get(6).num;
        for(int i = 0; i < 3; i++){
            int newvalue = evaluate5(hand_sorted.subList(i, i + 5));
            if(newvalue > highest)
                highest = newvalue;
        }
        if(evaluateFlush(hand_sorted) > highest){
            highest = 5;
        }
        return new handValue(highcard, highest);
    }

    static int evaluateFlush(List<Card> cards){
        int[] h = {0,0,0,0};
        for(int i = 0; i < 7; i++) h[cards.get(i).suit]++;
        if(h[0] >= 5 || h[1] >= 5 || h[2] >= 5 || h[3] >= 5) return 5;
        return 0;
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

    //method for printing player hand, used every rendering frame
    public void printHand(boolean showCards) {
        System.out.printf("%-10.10s $%d\n", name, money);
        System.out.print("================\n");
        if(showCards){
            if(hand.size() == 0) return; 
            hand.get(0).print();
            hand.get(1).print();
        }
        else if(playing) {
            System.out.println("███ ███");
        } 
        else {
            System.out.println("XXX XXX");
        }
        System.out.println();
    }
}