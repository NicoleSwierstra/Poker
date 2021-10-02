import java.util.*;

public class Main{

    final static String startup = 
    "Welcome to \"poker\", by some nerd\n" +
    "2 to 8 players compete in an exciting game of,,, poker\n" +
    "\n" +
    "\n" +
    "Press enter to begin";

    public static void main(String[] args){
        System.out.println("");
        Table t = new Table();
        Scanner sc = t.scanner;
        System.out.println(startup);
        sc.nextLine();
        System.out.println("Enter the name of a player or type 'start' to begin!");
        String buffer = sc.nextLine();
        while(!buffer.contains("start")){
            t.addPlayer(buffer);
            System.out.println("Enter the name of a player or type 'start' to begin!");
            buffer = sc.nextLine();
        }
        //for(int i = 0; i < 4000; i++){
        //    t.game();
        //}
        while(true) t.game();
        //t.players.forEach(player->{
        //    System.out.println(player.name + "..." + player.wins);
        //});
    }
}