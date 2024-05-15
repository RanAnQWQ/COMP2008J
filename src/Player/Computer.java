package Player;

import GameTable.ShuffleMajiang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Computer extends Player{
    public boolean isHost;

    // a boolean to tell if it fits the Chi condition
    public boolean isChi = false;
    // the number of performing Chi
    public static int ChiNumber = 0;

    // the number of performing Peng
    public static int PengNumber = 0;

    // the number of performing Gang
    public static int GangNumber = 0;

    // placing the players' cards
    private ArrayList<Integer> playerMajiangs=new ArrayList<Integer>();

    // cards to display after performing movements
    private ArrayList<Integer> cardsToDisplay = new ArrayList<Integer>();




    /**
     * same as Player!
     * gainMajiang(): pick one from maJiangs in ShuffleMaJiang and put it in playerMaJiangs
     * @param index: get the position of the card
     */



    /**
     * nextCard(): decide which card should be discarded for the computer.
     *
     * return: an index of the card to be discarded in the player's cards
     *
     * Algorithm: if there exists card hard to be matched (as a couple, triple or a sequence),
     *            discard the first encountered one.
     *            If all the cards in player's hand can be matched (as a couple or triple),
     *            then discard a random card.
     */
    public int nextCard(){
        // if nothing happen after, then randomly pick one card to discard
        Random num = new Random();
        int index = num.nextInt(playerMajiangs.size());

        // check if there exists card hard to be matched (as a couple or triple)
        for (int element: playerMajiangs){
            // iterate through the player's cards to find if the player have 2 or more same cards with every last card in the river
            int frequency = Collections.frequency(playerMajiangs, element);
            // find if the cards in player's hand have neighbors to form a sequence
            boolean middle = playerMajiangs.contains(element-1) && playerMajiangs.contains(element+1);
            boolean left = playerMajiangs.contains(element-1) && playerMajiangs.contains(element-2);
            boolean right = playerMajiangs.contains(element+1) && playerMajiangs.contains(element+2);
            // if the player have a card hard to be matched in the player's hand
            if (frequency < 2 || (! middle) || (! left) || (! right)) {
                // discard the first encountered one
                index = playerMajiangs.indexOf(element);
            }
        }
        return index;
    }




    /**
     * Aside: put this card aside to display.
     *        It starts by removing this card from the player's hand,
     *        then add this card to the display array.
     */
    public void Aside(int card){
        // add this card aside to display the card
        cardsToDisplay.add(card);
        // remove the card from the player's card
        playerMajiangs.remove(playerMajiangs.indexOf(card));
    }



    /**
     * Chi: When you can form a sequence with a tile discarded by your previous player,
     *      you can choose to eat this tile to complete the sequence.
     *      For example, if your hand tiles are 3, 4, 5, and your previous player discards 2,
     *      you can eat the 2 to form the sequence 2, 3, 4.
     */
    public void Chi(int card){
        // if the card both have left and right neighbour, its neighbours and itself will be a sequence
        if ( playerMajiangs.contains(card + 1) && playerMajiangs.contains(card - 1) ) {
            Aside(card - 1);
            Aside(card + 1);
            // add this card aside to display the card
            cardsToDisplay.add(card);
            // remove this card from the river (both the player's river and the whole river)
            ShuffleMajiang.river.remove(ShuffleMajiang.river.size() - 1);
            playerRiver.remove(playerRiver.size() - 1);
            // add up the times of Chi
            ChiNumber++;
        }
        // if the card only have right neighbour, its right neighbour,the right neighbour of its right neighbour and itself will be a sequence
        else if ( playerMajiangs.contains(card + 1) &&  playerMajiangs.contains(card + 2) ) {
            Aside(card + 1);
            Aside(card + 2);
            // add this card aside to display the card
            cardsToDisplay.add(card);
            // remove this card from the river (both the player's river and the whole river)
            ShuffleMajiang.river.remove(ShuffleMajiang.river.size() - 1);
            playerRiver.remove(playerRiver.size() - 1);
            // add up the times of Chi
            ChiNumber++;
        }
        // if the card only have left neighbour, its left neighbour,the left neighbour of its left neighbour and itself will be a sequence
        else if ( playerMajiangs.contains(card - 1) && playerMajiangs.contains(card - 2) ) {
            Aside(card - 1);
            Aside(card - 2);
            // add this card aside to display the card
            cardsToDisplay.add(card);
            // remove this card from the river (both the player's river and the whole river)
            ShuffleMajiang.river.remove(ShuffleMajiang.river.size() - 1);
            playerRiver.remove(playerRiver.size() - 1);
            // add up the times of Chi
            ChiNumber++;
        }

        // test
        //System.out.println(ChiNumber);
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
        // iterate through the player's cards to find if the player have 2 same cards with every last card in the river
        int frequency = Collections.frequency(playerMajiangs, card);
        // if the player have 2 same cards with one card in the river
        if (frequency == 2) {
            // remove these 2 same cards from the player's card
            // then add these 2 same cards to a new array to display the Peng cards
            Aside(card);
            Aside(card);
            // add this card aside to display the card
            cardsToDisplay.add(card);
            // remove this card from the river (both the player's river and the whole river)
            ShuffleMajiang.river.remove(ShuffleMajiang.river.size() - 1);
            playerRiver.remove(playerRiver.size() - 1);
            // add up the times of Peng
            PengNumber++;
        }
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
        // iterate through the player's cards to find if the player have 3 same cards with this card
        int frequency = Collections.frequency(playerMajiangs, card);
        if (frequency==3) {
            // remove these 3 cards from the player's card
            // then add these 3 cards to a new array to display the Gang cards
            Aside(card);
            Aside(card);
            Aside(card);
            // add this card aside to display the card
            cardsToDisplay.add(card);
            // remove this card from the river (both the player's river and the whole river)
            ShuffleMajiang.river.remove(ShuffleMajiang.river.size() - 1);
            playerRiver.remove(playerRiver.size() - 1);
            // add up the times of Gang
            GangNumber++;
        }
    }



}
