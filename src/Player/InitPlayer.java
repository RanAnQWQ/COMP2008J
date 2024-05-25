package Player;

import GameTable.ShuffleMajiang;
import window.*;

import java.util.ArrayList;
import java.util.List;


/**
 * InitPlayer: initialize the game table,
 *             including identify the attributes of players
 *             and deal the cards to the players.
 *
 * @author: Qiyue Zhu
 */
public class InitPlayer {
    //store the information of each in the players
    public static ArrayList<Player> players=new ArrayList<Player>();

    public HumanPlayer player;
    public Computer computer1, computer2, computer3;


    public InitPlayer() {

        // create players
        player = new HumanPlayer();
        computer1 = new Computer();
        computer2 = new Computer();
        computer3 = new Computer();

        players.add(player);
        players.add(computer1);
        players.add(computer2);
        players.add(computer3);

        GameContent gameContent = new GameContent();
        if (gameContent.getFirstHost().equalsIgnoreCase("east")) {
            // reset the boolean isHost based on the host result
            player.isHost = true;
            System.out.println("user is player");
            computer1.isHost = false;
            computer2.isHost = false;
            computer3.isHost = false;
        } else if (gameContent.getFirstHost().equalsIgnoreCase("North")) {
            // reset the boolean isHost based on the host result
            computer1.isHost = true;
            System.out.println("host is computer1");
            player.isHost = false;
            computer2.isHost = false;
            computer3.isHost = false;

        } else if (gameContent.getFirstHost().equalsIgnoreCase("West")) {
            // reset the boolean isHost based on the host result
            computer2.isHost = true;
            System.out.println("host is computer2");
            player.isHost = false;
            computer1.isHost = false;
            computer3.isHost = false;

        } else if (gameContent.getFirstHost().equalsIgnoreCase("South")) {
            // reset the boolean isHost based on the host result
            computer3.isHost = true;
            System.out.println("host is computer3");
            player.isHost = false;
            computer2.isHost = false;
            computer1.isHost = false;

        }
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
                player.gainMajiang();
            }
            // computer1, get 4 cards at a time
            for (int j = 0; j < 4; j++) {
                computer1.gainMajiang();
            }
            // computer2, get 4 cards at a time
            for (int j = 0; j < 4; j++) {
                computer2.gainMajiang();
            }
            // computer3, get 4 cards at a time
            for (int j = 0; j < 4; j++) {
                computer3.gainMajiang();
            }
        }
        // the last turn will get one more card each
        player.gainMajiang();
        computer1.gainMajiang();
        computer2.gainMajiang();
        computer3.gainMajiang();
        if (player.isHost) {
            player.gainMajiang();
        }else if (computer1.isHost) {
            computer1.gainMajiang();
        }else if(computer2.isHost){
            computer2.gainMajiang();
        }else if(computer3.isHost){
            computer3.gainMajiang();
        }
    }

}
