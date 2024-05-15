package Player;

import GameTable.ShuffleMajiang;
import Majiang.Majiang;
import Majiang.MajiangFeng;
import Majiang.MajiangNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Player class aims to create a player and its features.
 * @author Qiyue Zhu
 */
public class Player {
    // the boss is decided in the GameWindow and will be assigned in the Game class.
    public boolean isHost;

    // a boolean to tell if it fits the Chi condition
    public boolean isChi = false;

    // a boolean to tell if it fits the Peng condition
    public boolean isPeng =false;

    // a boolean to tell if it fits the G condition
    public boolean isGang = false;

    // the number of performing Chi
    public static int ChiNumber;

    // the number of performing Peng
    public static int PengNumber = 0;

    // the number of performing Gang
    public static int GangNumber = 0;

    // every player's cards
    public ArrayList<Integer> playerMajiangs=new ArrayList<Integer>();

    // cards to display after performing movements
    private ArrayList<Integer> cardsToDisplay = new ArrayList<Integer>();
    public ArrayList<Integer> getCardsToDisplay() {
        return cardsToDisplay;
    }

    // player's river (the discarded cards)
    public ArrayList<Integer> playerRiver = new ArrayList<>();

    // the sets of Chi user might choose
    public ArrayList<Integer> set1=new ArrayList<>();
    public ArrayList<Integer> set2=new ArrayList<>();
    public ArrayList<Integer> set3=new ArrayList<>();



    public ArrayList<Integer> getPlayerMajiangs() {
        return playerMajiangs;
    }

    public void setPlayerMajiangs(ArrayList<Integer> playerMajiangs) {
        this.playerMajiangs = playerMajiangs;
    }

    /**
     * gainMajiang: pick one from maJiangs in ShuffleMaJiang and put it in playerMaJiangs
     * @param index: get the position of the card
     */
    public void gainMajiang(int index){
        // pick a card from the cards and put it into the player's hand
        playerMajiangs.add(ShuffleMajiang.maJiangs.get(index));
        // remove this card from maJiangs
        ShuffleMajiang.maJiangs.remove(index);
        Collections.sort(playerMajiangs);

        // pick a card from the cards and put it into the player's hand
//        playerMajiangs.add(ShuffleMajiang.maJiangs.get(index));
//        // remove this card from maJiangs
//        ShuffleMajiang.maJiangs.remove(index);
//        Collections.sort(playerMajiangs);
    }


    public void Aside(int card){
        // add this card aside to display the card
        cardsToDisplay.add(card);
        // remove the card from the player's card
        playerMajiangs.remove(playerMajiangs.indexOf(card));
    }


    /**
     * isChi: isChi method is written to test if the card discarded by the former player
     *        fits the Chi condition:
     *        has 2 neighbors or both a neighbour and its neighbour to complete a sequence.
     *
     *        For example, if your hand tiles are 3, 4, 5, and your previous player discards 2,
     *        you can eat the 2 to form the sequence 2, 3, 4.
     *
     * Chi action can be taken if isChi is true and the user click the Chi bottom.
     */
    public void isChi(int card){
        // reset the variables
        isChi =false;
        set1.clear();
        set2.clear();
        set3.clear();
        // if the card is the Number cards
        if(card<41){
            // this can have different situation, and the final sequence (set1/ set2/ set3) will be chosen by the user

            // if the card only have left neighbour, its left neighbour,the left neighbour of its left neighbour and itself will be a sequence
            if ( playerMajiangs.contains(card-1) && playerMajiangs.contains(card-2) ) {
                //set2.add(card);
                set1.add(card-2);
                set1.add(card-1);
                isChi = true;
            }
            // if the card both have left and right neighbour, its neighbours and itself will be a sequence
            if ( playerMajiangs.contains(card-1) && playerMajiangs.contains(card+1) ) {
                // choose this condition to be a sequence
                set2.add(card-1);
                //set1.add(card);
                set2.add(card+1);
                isChi = true;
            }
            // if the card only have right neighbour, its right neighbour,the right neighbour of its right neighbour and itself will be a sequence
            if ( playerMajiangs.contains(card+1) && playerMajiangs.contains(card+2) ) {
                //set3.add(card);
                set3.add(card+1);
                set3.add(card+2);
                isChi = true;
            }
        }
    }




    /**
     * Chi: When you can form a sequence with a tile discarded by your previous player,
     *      you can choose to eat this tile to complete the sequence.
     *      For example, if your hand tiles are 3, 4, 5, and your previous player discards 2,
     *      you can eat the 2 to form the sequence 2, 3, 4.
     */
    public void Chi(ArrayList<Integer> set, int card){
        //listener=1 : left(set1)
        //listener=2 : middle(set2)
        //listener=3 : right(set3)

        // add this card aside to display the card
        // remove the card from the player's card
        Aside(set.get(0));
        Aside(set.get(1));
        cardsToDisplay.add(card);
        Collections.sort(cardsToDisplay);
        // remove this card from the river (both the player's river and the whole river)
        ShuffleMajiang.river.remove(ShuffleMajiang.river.size()-1);
        playerRiver.remove(playerRiver.size() - 1);
        // add up the number of Chi
        ChiNumber++;
    }



    /**
     * isPeng: isPeng method is written to test if the card just discarded fits the Peng condition:
     *        has 2 same cards to complete a set.
     *
     *       For example, if you already have two 8 tiles and another player discards an 8,
     *       you can collide the 8 to form the set 8, 8, 8.
     *
     * Peng action can be taken if isPeng is true and the user click the Peng bottom.
     */
    public boolean isPeng(int card){
        // iterate through the player's cards to find if the player have 2 same cards with every last card in the river
        int frequency = Collections.frequency(playerMajiangs, card);
        // if the player have 2 same cards with one card in the river
        if (frequency == 2) {
            return true;
        }
        return false;
    }


    /**
     * isGang: isGang method is written to test if the card just discarded fits the Gang condition:
     *        has 3 more same cards with the given card.
     *
     *       For example, if you already have three 8 tiles and was given an 8,
     *       you can take the 8 to form the set 8, 8, 8, 8.
     *
     * Gang action can be taken if isGang is true and the user click the Gang bottom.
     */
    public boolean isGang(int card){
        // iterate through the player's cards to find if the player have 2 same cards with every last card in the river
        int frequency = Collections.frequency(playerMajiangs, card);
        // if the player have 2 same cards with one card in the river
        if (frequency == 3) {
            return true;
        }
        return false;
    }

}

