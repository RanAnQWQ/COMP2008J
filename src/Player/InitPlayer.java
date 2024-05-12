package Player;

import GameTable.ShuffleMajiang;
import window.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Player: create the 4 players
 * @author Qiyue Zhu
 */
public class InitPlayer {
    //store the information of each in the players
    public static List<Player> players=new ArrayList<Player>();

    public Player player;
    public Computer computer1, computer2, computer3;


    public InitPlayer() {

        // create players
        player = new Player();
        computer1 = new Computer();
        computer2 = new Computer();
        computer3 = new Computer();

        // set the names of the players
        player.setName("p");
        computer1.setName("c1");
        computer2.setName("c2");
        computer3.setName("c3");

        // throw the dice to decide the first host (had been decided in the GameWindow class)
        // player: East; computer1: North; computer2: West; computer3: South
        //GameWindow window = new GameWindow();
        //if (window.getFirstHost().equalsIgnoreCase("east")) {
        if (true){
            // reset the boolean isHost based on the host result
            player.isHost = true;
            computer1.isHost = false;
            computer2.isHost = false;
            computer3.isHost = false;
            // resort the players' order
            players.add(player);
            players.add(computer1);
            players.add(computer2);
            players.add(computer3);
        }
        /*
        else if (window.getFirstHost().equalsIgnoreCase("north")) {
            // reset the boolean isHost based on the host result
            computer1.isHost = true;
            player.isHost = false;
            computer2.isHost = false;
            computer3.isHost = false;
            // resort the players' order
            players.add(computer1);
            players.add(computer2);
            players.add(computer3);
            players.add(player);
        } else if (window.getFirstHost().equalsIgnoreCase("west")) {
            // reset the boolean isHost based on the host result
            computer2.isHost = true;
            computer1.isHost = false;
            player.isHost = false;
            computer3.isHost = false;
            // resort the players' order
            players.add(computer2);
            players.add(computer3);
            players.add(player);
            players.add(computer1);
        } else if (window.getFirstHost().equalsIgnoreCase("south")) {
            // reset the boolean isHost based on the host result
            computer3.isHost = true;
            computer1.isHost = false;
            computer2.isHost = false;
            player.isHost = false;
            // resort the players' order
            players.add(computer3);
            players.add(player);
            players.add(computer1);
            players.add(computer2);
        }
        */


    }



    /**
     * haveFirstBoard: deal the cards to all players for the first time
     *
     * Result: Everyone will get 12 cards on their hands evenly.
     */
    public void haveFirstBoard(){
        // 4 turns in total
        // the first 3 turns will get 12 cards each
        for (int i = 0; i < 3; i++) {
            // player, get 4 cards at a time
            for (int j = 0; j < 4; j++) {
                player.gainMajiang(ShuffleMajiang.maJiangsIndex);
            }
            // computer1, get 4 cards at a time
            for (int j = 0; j < 4; j++) {
                computer1.gainMajiang(ShuffleMajiang.maJiangsIndex);
            }
            // computer2, get 4 cards at a time
            for (int j = 0; j < 4; j++) {
                computer2.gainMajiang(ShuffleMajiang.maJiangsIndex);
            }
            // computer3, get 4 cards at a time
            for (int j = 0; j < 4; j++) {
                computer3.gainMajiang(ShuffleMajiang.maJiangsIndex);
            }
        }
        // the last turn will get one more card each
        player.gainMajiang(ShuffleMajiang.maJiangsIndex);
        computer1.gainMajiang(ShuffleMajiang.maJiangsIndex);
        computer2.gainMajiang(ShuffleMajiang.maJiangsIndex);
        computer3.gainMajiang(ShuffleMajiang.maJiangsIndex);
    }
}
