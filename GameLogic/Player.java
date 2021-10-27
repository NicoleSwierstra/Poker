package GameLogic;
import java.util.*;

public class Player {
    public String name;
    public int money;
    protected Table table;
    public List<Card> hand;
    public boolean AI;
    public boolean playing;
    public int deficit;
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
    public void bet(int amount){
        table.raiseDeficit(amount - deficit);
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
        return new handValue(hand_sorted, Math.max(hand.get(0).num, hand.get(1).num));
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