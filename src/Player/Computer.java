package Player;

import GameTable.ShuffleMajiang;
import Majiang.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Computer extends Player{
    public boolean isBoss;
    private String name;

    // placing the players' cards
    private List<Majiang> playerMajiangs=new ArrayList<Majiang>();

    // to note every index of player's cards in playerMaJiangs
    private int playerMajiangsIndex=0;



    /**
     * same as Player!
     * gainMajiang(): pick one from maJiangs in ShuffleMaJiang and put it in playerMaJiangs
     * @param index: get the position of the card
     */



    /**
     * nextCard(): decide which card should be discarded for the computer.
     *
     * Algorithm: if there exists card hard to be matched (as a couple or triple),
     *            discard the first encountered one.
     *            If all the cards in player's hand can be matched (as a couple or triple),
     *            then discard a random card.
     */
    public int nextCard(){
        int index;

        if ( true  ){
            index = 1;
        } else {
            Random num = new Random();
            index = num.nextInt(1, 10);
        }
        return index;
    }


    /**
     * same as Player!
     * discardMajiang()：pick a card from playerMaJiangs, and put it into the river
     */

    public Majiang discardMajiang(int index){
        if ((index>playerMajiangs.size()) || (index<=0)) {
            return null;
        }
        // pick a card to put it into the river
        ShuffleMajiang.river.add(ShuffleMajiang.riverIndex, playerMajiangs.get(index-1));
        ShuffleMajiang.riverIndex++;
        // remove the card from playerMaJiangs
        playerMajiangs.remove(index);
        return playerMajiangs.get(index);
    }

}
