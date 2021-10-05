package GameLogic;
import java.util.*;

public class Table {
    protected List<Player> players;
    protected List<Card> community;
    Deck deck;
    protected int pot;
    protected int turn;
    protected int com_turnover;
    protected final int[] comt = {0, 3, 4, 5};
    public Scanner scanner;
    
    public Table() {
        players = new ArrayList<Player>();
        deck = new Deck();
        reset();
        scanner = new Scanner(System.in);
    }

    //resets the table
    void reset() {
        deck.reset();
        pot = 0;
    }

    //adds a player to the list of players
    public void addPlayer(String name){
        players.add(new Player(name, this));
    }

    //deals from the deck to a player
    void dealToPlayer(int pnum){
        players.get(pnum).dealTo(deck.copyAndRemove(2));
    }

    //starts the game
    protected void startGame() {
        reset();
        for(int i = 0; i < players.size(); i++){
            dealToPlayer(i);
        }
        community = deck.copyAndRemove(5);
    }

    //runs a game, which is defined as a single deal
    public void game(){
        startGame(); //starts the game
        com_turnover = 5;
        //first round
        players.forEach(p->{p.deficit = 2; p.playing = true;});
        for(int i = 0; i < 4; i++){
            round(i);
        }
        Player winner = determineWinner();
        winner.money += pot;
        winner.wins++;
        printEnd(winner);
        pot = 0;
    }

    //finds the winner
    Player determineWinner(){
        Player p = null;
        handValue highhv = new handValue(0, 0);
        for(int i = 0; i < players.size(); i++){
            Player current = players.get(i);

            if(current.playing){
                handValue hv = current.handvalue();
                
                //I hate how long the variables are here
                if(hv.value > highhv.value || (hv.value == highhv.value && hv.highcard > highhv.highcard))
                {
                    p = current;
                    highhv = hv;
                }
            }
        }
        return p;
    }

    //round
    protected void round(int num){
        System.out.println("Round " + (num + 1) + ", press enter to continue");
        scanner.nextLine();
        com_turnover = comt[num];
        do {
            for(int i = 0; i < players.size(); i++){
                if(players.get(i).playing){
                    playerTurn(i);
                }
            }
        } while(!allAdvance());
    }

    boolean canAdvance(int pnum) {
        return players.get(pnum).deficit == 0 || !players.get(pnum).playing;
    }

    protected boolean allAdvance() {
        for(int i = 0; i < players.size(); i++){
            if (!canAdvance(i)) return false;
        }
        return true;
    }
        
    public void raiseDeficit(int i) {
        players.forEach(player -> {player.deficit += i;});
    }

    //whoooo boy
    protected void playerTurn(int turnNum){
        Player p = players.get(turnNum);
        System.out.println(
            "It is now " + p.name + "'s turn, press enter to continue"
        );
        scanner.nextLine();
        printTable(turnNum);
        p.takeTurn(scanner);
        clearConsole();
    }

    //prints the table
    void printTable(int turn){
        clearConsole();

        System.out.println("Pot: $" + pot + "\n");
        System.out.println("community:");
        printCommunity();
        System.out.println("\n");

        //prints each player
        for(int i = 0; i < players.size(); i++){
            players.get(i).printHand(i == turn);
            System.out.println();
        }
    }

    //for end of game
    void printEnd(Player winner){
        clearConsole();
        System.out.println("community:");
        printCommunity();
        System.out.println("\n");
        for(int i = 0; i < players.size(); i++){
            players.get(i).printHand(true);
            System.out.println();
        }
        System.out.println(winner.name + " wins $" + pot + "!!");
    }

    //prints the community cards
    void printCommunity(){
        for(int i = 0; i < com_turnover; i++){
            community.get(i).print();
        }
        for(int i = com_turnover; i < 5; i++){
            System.out.print("███");
        }
    }

    //helper method i just put here
    void clearConsole(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}
