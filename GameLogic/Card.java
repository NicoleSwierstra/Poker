package GameLogic;
import java.io.PrintStream;

public class Card {
    public byte suit;
    public byte num;

    public Card(int suit, int num){
        this.suit = (byte)suit;
        this.num = (byte)num;
    }

    public void print(){
        System.out.print(getPrintString());
    }

    public void print(PrintStream dest){
        dest.print(getPrintString());
    }

    String getPrintString(){
        String color = (suit == 1 || suit == 2) ? "\033[31;40m" : "\033[30;47m", 
            footer = "\033[0;0m", n;
        char s = (char)('♠' + suit);
        switch(num){
            case 0:
                n = "A  ";
                break;
            case 9:
                n = "10 ";
                break;
            case 10:
                n = "J  ";
                break;
            case 11:
                n = "Q  ";
                break;
            case 12: 
                n = "K  ";
                break;
            default:
                n = (num + 1) + "  ";
        }

        return color + s + footer + n;
    }
}
