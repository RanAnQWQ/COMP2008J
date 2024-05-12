package GameTable;

import Player.*;
import window.*;
import HuHelper.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This is the whole game process.
 *
 * To start with, we deal the cards (the number of cards are different between the host and normal ones)
 * After that, the player and computers will gain and discard their cards in turns.
 * During the process, the chance to Chi, Peng, Gang, Ting, Hu will pop up as a button.
 * The player can choose to take the action or not, meanwhile, the computers will take that action automatically.
 *
 * @author Qiyue Zhu
 */
public class Game {
    //store the information of each in the players
    public static List<Player> players=new ArrayList<Player>();

    public static void main(String[] args) throws InterruptedException {
        new MainMenuWindow();
        // initial the players
        InitPlayer initPlayer = new InitPlayer();

        // create 4 players, and 3 of them are computers
        Player player = initPlayer.player;
        Computer computer1 = initPlayer.computer1, computer2 = initPlayer.computer2, computer3 = initPlayer.computer3;

        // create and shuffle the Majiang cards
        ShuffleMajiang shuffleMajiang = new ShuffleMajiang();


        // deal the cards for the first time evenly (13 cards each)
        ShuffleMajiang.maJiangsIndex=0;
        initPlayer.haveFirstBoard();



        // sort the cards
        Collections.sort(player.getPlayerMajiangs());
        Collections.sort(computer1.getPlayerMajiangs());
        Collections.sort(computer2.getPlayerMajiangs());
        Collections.sort(computer3.getPlayerMajiangs());

        System.out.println(player.getPlayerMajiangs());


        // to judge if any of the players will Hu or not
        boolean playerHu = Hu.isHu(player.getPlayerMajiangs(), player.getCardsToDisplay(), player.ChiNumber, player.PengNumber, player.GangNumber);
        boolean computer1Hu = Hu.isHu(computer1.getPlayerMajiangs(), computer1.getCardsToDisplay(), computer1.ChiNumber, computer1.PengNumber, computer1.GangNumber);
        boolean computer2Hu = Hu.isHu(computer2.getPlayerMajiangs(), computer2.getCardsToDisplay(), computer2.ChiNumber, computer2.PengNumber, computer2.GangNumber);
        boolean computer3Hu = Hu.isHu(computer3.getPlayerMajiangs(), computer3.getCardsToDisplay(), computer3.ChiNumber, computer3.PengNumber, computer3.GangNumber);


        // in the first turn, the host will gain a card and discard one
        initPlayer.players.get(0).gainMajiang(1);  // index are got from the window
        initPlayer.players.get(0).discardMajiang(1);  // index are got from the window


        // take turns to play, always start with the host
        for (int turn = 1; turn < 4; turn++) {
            if (  ( !playerHu ) && ( !computer1Hu ) && ( !computer2Hu ) && ( !computer3Hu )  ) {
                // in one's turn, the movement of Chi, Peng, Gang will happen
                int riverLastCard = ShuffleMajiang.river.get(ShuffleMajiang.riverIndex - 1);
                Movement movement1 = new Movement(riverLastCard, initPlayer.players.get(turn).ChiNumber, initPlayer.players.get(turn).PengNumber, initPlayer.players.get(turn).GangNumber);

                // in one's turn, the player will gain a card and discard one
                initPlayer.players.get(turn).gainMajiang(1);  // index are got from the window
                initPlayer.players.get(turn).discardMajiang(1);  // index are got from the window

                //and for others, the possible movements like Peng, Gang will be detected
                Movement movement2 = new Movement(initPlayer.players.get((turn + 1) % 4).getPlayerMajiangs(), initPlayer.players.get((turn + 1) % 4).ChiNumber, initPlayer.players.get((turn + 1) % 4).PengNumber, initPlayer.players.get((turn + 1) % 4).GangNumber);
                Movement movement3 = new Movement(initPlayer.players.get((turn + 2) % 4).getPlayerMajiangs(), initPlayer.players.get((turn + 2) % 4).ChiNumber, initPlayer.players.get((turn + 2) % 4).PengNumber, initPlayer.players.get((turn + 2) % 4).GangNumber);
                Movement movement4 = new Movement(initPlayer.players.get((turn + 3) % 4).getPlayerMajiangs(), initPlayer.players.get((turn + 3) % 4).ChiNumber, initPlayer.players.get((turn + 3) % 4).PengNumber, initPlayer.players.get((turn + 3) % 4).GangNumber);

                /*
        // after a Peng or Gang happens, the order of turn will change
        if ( this.PengNumber != PengNumber || this.GangNumber != GangNumber ){
             currentPlayer = ;
        }*/
            }
        }
    }
}
