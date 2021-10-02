package GameLogic;

import java.util.Scanner;

public class ConsoleGame {

    final static String startup = 
    "Welcome to \"poker\", by some nerd\n" +
    "2 to 8 players compete in an exciting game of,,, poker\n" +
    "\n" +
    "\n" +
    "Press enter to begin";

    public static void game(){
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
        while(true) t.game();
    }
}
