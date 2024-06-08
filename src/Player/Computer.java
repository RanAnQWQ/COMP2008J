package Player;

import GameTable.ShuffleMajiang;
import window.AddTile;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Computer extends Player{
    public int discardMajiang(int index) {
        int card = playerMajiangs.get(index);
        playerMajiangs.remove(index);
        ShuffleMajiang.river.add(card);
        ShuffleMajiang.riverIndex++;
        playerRiver.add(card);
        return card;
    }


    /**
     * nextCard(): decide which card should be discarded for the computer.
     *
     * return: the index of the card to be discarded in the player's cards
     *
     * Algorithm: if there exists card hard to be matched (as a couple, triple or a sequence),
     *            discard the first encountered one.
     *            If all the cards in player's hand can be matched (as a couple or triple),
     *            then discard a random card.
     */
    public int nextCard(){
        // if nothing happen after, then randomly pick one card to discard
        Random num = new Random();
        int index = num.nextInt(playerMajiangs.size()-1);

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
                break;
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
    public void Chi(Integer card, int scaledWidth, int scaledHeight, JPanel gamePanel,
                    int computerName, AddTile addTile){
        // if the card both have left and right neighbour, its neighbours and itself will be a sequence
        if ( playerMajiangs.contains(card + 1) && playerMajiangs.contains(card - 1) ) {
            Aside(card - 1);
            Aside(card + 1);
            // add this card aside to display the card
            cardsToDisplay.add(card);
            // remove this card from the river (both the player's river and the whole river)
            ShuffleMajiang.river.remove(ShuffleMajiang.river.size() - 1);
            ShuffleMajiang.riverIndex--;
            // add up the times of Chi
            ChiNumber++;
            addTile.addTileToDisplay(card-1, scaledWidth, scaledHeight, gamePanel,computerName);
            addTile.addTileToDisplay(card, scaledWidth, scaledHeight, gamePanel,computerName);
            addTile.addTileToDisplay(card+1, scaledWidth, scaledHeight, gamePanel,computerName);

        }
        // if the card only have right neighbour, its right neighbour,the right neighbour of its right neighbour and itself will be a sequence
        else if ( playerMajiangs.contains(card + 1) &&  playerMajiangs.contains(card + 2) ) {
            Aside(card + 1);
            Aside(card + 2);
            // add this card aside to display the card
            cardsToDisplay.add(card);
            // remove this card from the river (both the player's river and the whole river)
            ShuffleMajiang.river.remove(ShuffleMajiang.river.size() - 1);
            ShuffleMajiang.riverIndex--;
            // add up the times of Chi
            ChiNumber++;
            addTile.addTileToDisplay(card, scaledWidth, scaledHeight, gamePanel,computerName);
            addTile.addTileToDisplay(card+1, scaledWidth, scaledHeight, gamePanel,computerName);
            addTile.addTileToDisplay(card+2, scaledWidth, scaledHeight, gamePanel,computerName);
        }
        // if the card only have left neighbour, its left neighbour,the left neighbour of its left neighbour and itself will be a sequence
        else if ( playerMajiangs.contains(card - 1) && playerMajiangs.contains(card - 2) ) {
            Aside(card - 1);
            Aside(card - 2);
            // add this card aside to display the card
            cardsToDisplay.add(card);
            ShuffleMajiang.river.remove(ShuffleMajiang.river.size() - 1);
            ShuffleMajiang.riverIndex--;
            // add up the times of Chi
            ChiNumber++;
            addTile.addTileToDisplay(card-2, scaledWidth, scaledHeight, gamePanel,computerName);
            addTile.addTileToDisplay(card-1, scaledWidth, scaledHeight, gamePanel,computerName);
            addTile.addTileToDisplay(card, scaledWidth, scaledHeight, gamePanel,computerName);
        }

    }

}
