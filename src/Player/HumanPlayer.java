package Player;

import GameTable.ShuffleMajiang;
import window.AddTile;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * HumanPlayer: define the related features of human players,
 *              including gain card, discard card, chi, peng, gang, ting & hu.
 *              the discard card and the chi methods are rewritten.
 *
 * @author: Qiyue Zhu
 */
public class HumanPlayer extends Player {

    /**
     * discardMajiang()：remove a card from the player
     * @param card: the card to be discarded in the player's cards
     */
    @Override
    public int discardMajiang(int card){
        // remove the card from playerMaJiangs
        playerMajiangs.remove(playerMajiangs.indexOf(card));
        Collections.sort(playerMajiangs);
        return card;
    }


    /**
     * Aside: put this card aside to display.
     *        It starts by removing this card from the player's hand,
     *        then add this card to the display array.
     */
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
     * Chi: When you can form a sequence with a tile discarded by your previous player,
     *      you can choose to eat this tile to complete the sequence.
     *      For example, if your hand tiles are 3, 4, 5, and your previous player discards 2,
     *      you can eat the 2 to form the sequence 2, 3, 4.
     *
     * listener is first assigned as the chosen position of the Chi patterns.
     *
     * If set have 3 cards, meaning there will only be one choice for player.
     *
     * If set have 6 cards, there will be 2 choices for the player:
     *    choose the left (listener = 1) or the right(listener = 2) half of the set.
     *    To notice, if a card both have its left 2 neighbours and its right 2 neighbours,
     *    the length of the set should be 9 instead of 6.
     *
     * If set have 9 cards, there will be 3 choices for the player:
     *    choose the left (listener = 0), the middle(listener = 1) or the right(listener = 2) part of the set.
     *
     * In this process, listener is set finally as the index of the pattern chosen.
     */
    public void Chi(ArrayList<Integer> set, int listener, Integer card, int scaledWidth, int scaledHeight, JPanel gamePanel,
                    int computerName, AddTile addTile) {
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
            // listener=1 (card on the left)
            // if cards fit: (card-1, card, card+1), card, card+1, card+2
            // listener=1 (card in the middle)
            if (listener == 1) {
                set = new ArrayList<>(set.subList(0, 3));
                listener = set.indexOf(card);
            }
            // if cards fit: card-1, card, card+1, (card, card+1, card+2)
            // listener=2 (card on the right)
            // if cards fit: card-2, card-1, card, (card-1, card, card+1)
            // listener=2 (card in the middle)
            else {
                set = new ArrayList<>(set.subList(3, 6));
                listener = set.indexOf(card);
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

        // remove this card from the river (both the player's river and the whole river)
        ShuffleMajiang.river.remove(ShuffleMajiang.river.size()-1);
        for (int i:set) {
            addTile.addTileToDisplay(i, scaledWidth, scaledHeight, gamePanel,computerName);
        }
        // add up the number of Chi
        ChiNumber++;
    }
}
