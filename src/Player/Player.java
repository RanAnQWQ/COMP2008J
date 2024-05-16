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

    // the number of performing Chi
    public static int ChiNumber;

    // the number of performing Peng
    public static int PengNumber;

    // the number of performing Gang
    public static int GangNumber;

    // every player's cards
    public ArrayList<Integer> playerMajiangs=new ArrayList<Integer>();

    // cards to display after performing movements
    public ArrayList<Integer> cardsToDisplay = new ArrayList<Integer>();

    // player's river (the discarded cards)
    public ArrayList<Integer> playerRiver = new ArrayList<>();

    // the sets of Chi user might choose
    public ArrayList<Integer> set=new ArrayList<>();

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


    public void Aside(ArrayList<Integer> set, int cardIndex){
        if (cardIndex == 2){
            // add this card aside to display the card
            cardsToDisplay.add(set.get(0));
            cardsToDisplay.add(set.get(1));
            // remove the card from the player's card
            playerMajiangs.remove(playerMajiangs.indexOf(set.get(0)));
            playerMajiangs.remove(playerMajiangs.indexOf(set.get(1)));
        } else if (cardIndex == 1) {
            // add this card aside to display the card
            cardsToDisplay.add(set.get(0));
            cardsToDisplay.add(set.get(2));
            // remove the card from the player's card
            playerMajiangs.remove(playerMajiangs.indexOf(set.get(0)));
            playerMajiangs.remove(playerMajiangs.indexOf(set.get(2)));
        } else if (cardIndex == 0) {
            // add this card aside to display the card
            cardsToDisplay.add(set.get(1));
            cardsToDisplay.add(set.get(2));
            // remove the card from the player's card
            playerMajiangs.remove(playerMajiangs.indexOf(set.get(1)));
            playerMajiangs.remove(playerMajiangs.indexOf(set.get(2)));
        }
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
    public ArrayList<Integer> isChi(int card){
        // reset the variables
        isChi =false;
        set.clear();
        // if the card is the Number cards
        if(card<41){
            // this can have different situation, and the final sequence (set1/ set2/ set3) will be chosen by the user

            // if the card only have left neighbour, its left neighbour,the left neighbour of its left neighbour and itself will be a sequence
            if ( playerMajiangs.contains(card-1) && playerMajiangs.contains(card-2) ) {
                set.add(card-2);
                set.add(card-1);
                set.add(card);
                isChi = true;
            }
            // if the card both have left and right neighbour, its neighbours and itself will be a sequence
            if ( playerMajiangs.contains(card-1) && playerMajiangs.contains(card+1) ) {
                // choose this condition to be a sequence
                set.add(card-1);
                set.add(card);
                set.add(card+1);
                isChi = true;
            }
            // if the card only have right neighbour, its right neighbour,the right neighbour of its right neighbour and itself will be a sequence
            if ( playerMajiangs.contains(card+1) && playerMajiangs.contains(card+2) ) {
                set.add(card);
                set.add(card+1);
                set.add(card+2);
                isChi = true;
            }
        }
        return set;
    }




    /**
     * Chi: When you can form a sequence with a tile discarded by your previous player,
     *      you can choose to eat this tile to complete the sequence.
     *      For example, if your hand tiles are 3, 4, 5, and your previous player discards 2,
     *      you can eat the 2 to form the sequence 2, 3, 4.
     */
    public void Chi(ArrayList<Integer> set, int listener, int card) {
        // listener=3
        // if set has 3 cards
        if (listener == 3){
            listener = set.indexOf(card);
        }
        // listener=2 (card on the right)
        // listener=1 (card in the middle)
        // listener=0 (card on the left)
        else if (set.size() == 6) {
            // if cards fit: (card-2, card-1, card), card-1, card, card+1
            if (listener == 2) {
                set = new ArrayList<>(set.subList(0, 3));
            }
            // if cards fit: card-1, card, card+1, (card, card+1, card+2)
            else if (listener == 0) {
                set = new ArrayList<>(set.subList(3, 6));
            } else {
                int cardIndex = set.indexOf(card);
                if (cardIndex == 1){
                    set = new ArrayList<>(set.subList(0, 3));
                } else {
                    set = new ArrayList<>(set.subList(3, 6));
                }

            }
        }
        else if (set.size() == 9){
            // if cards fit: (card-2, card-1, card), card-1, card, card+1, card, card+1, card+2
            if (listener == 2){
                set = new ArrayList<>(set.subList(0, 3));
            }
            // if cards fit: card-2, (card-1, card, card+1), card+2
            else if (listener == 1) {
                set = new ArrayList<>(set.subList(3, 6));
            }
            // if cards fit:  card-1, (card, card+1, card+2)
            else if (listener == 0) {
                set = new ArrayList<>(set.subList(6, 9));
            }
        }
        // add this card aside to display the card
        // remove the card from the player's card
        Aside(set, listener);
        cardsToDisplay.add(set.get(listener));
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
     * Peng: When a tile discarded by another player
     *       matches the two identical tiles in your hand to form a set.
     *       Whether it's your turn to play or not, you can choose to peng the set.
     *       For example, if you already have two 8 tiles and another player discards an 8,
     *       you can collide the 8 to form the set 8, 8, 8.
     *
     */
    public void Peng(int card){
        // remove these 2 same cards from the player's card
        // then add these 2 same cards to a new array to display the Peng cards

        // add this card aside to display the card
        cardsToDisplay.add(card);
        cardsToDisplay.add(card);
        // remove the card from the player's card
        playerMajiangs.remove(playerMajiangs.indexOf(card));
        playerMajiangs.remove(playerMajiangs.indexOf(card));
        // add this card aside to display the card
        cardsToDisplay.add(card);
        // remove this card from the river (both the player's river and the whole river)
        ShuffleMajiang.river.remove(ShuffleMajiang.river.size() - 1);
        playerRiver.remove(playerRiver.size() - 1);
        // add up the times of Peng
        PengNumber++;
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

    /**
     * Gang: When you can have the same 4 cards. It can be divided into overt gang and covert gang.
     *      Overt Gang: When you have three same tiles and another player discards the rest tile,
     *                  you can choose to gang the forth tile and show them in public.
     *      Covert Gang: When you have three same tiles, and you draw the forth tile by yourself,
     *                  this make up the covert gang, and you don't need to show them in public.
     * In this case, the overt gang is shown below.
     */
    public void Gang(int card){
        // remove these 3 cards from the player's card
        // then add these 3 cards to a new array to display the Gang cards

        // add this card aside to display the card
        cardsToDisplay.add(card);
        cardsToDisplay.add(card);
        cardsToDisplay.add(card);
        // remove the card from the player's card
        playerMajiangs.remove(playerMajiangs.indexOf(card));
        playerMajiangs.remove(playerMajiangs.indexOf(card));
        playerMajiangs.remove(playerMajiangs.indexOf(card));
        // add this card aside to display the card
        cardsToDisplay.add(card);
        // remove this card from the river (both the player's river and the whole river)
        ShuffleMajiang.river.remove(ShuffleMajiang.river.size() -1);
        playerRiver.remove(playerRiver.size() - 1);
        // add up the times of Gang
        GangNumber++;
    }


}

