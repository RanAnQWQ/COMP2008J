package Player;



import GameTable.ShuffleMajiang;
import Majiang.Majiang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Movement {
    private List<Majiang> playerMajiangs=new ArrayList<Majiang>();    // player's cards
    private List<Majiang> cardsToDisplay = new ArrayList<Majiang>();   // cards to display after performing movements

    public Movement(List<Majiang> playerMajiangs){
        this.playerMajiangs = playerMajiangs;
    }


    /**
     * Chi: When you can form a sequence with a tile discarded by your previous player,
     *      you can choose to eat this tile to complete the sequence.
     *      For example, if your hand tiles are 3, 4, 5, and your previous player discards 2,
     *      you can eat the 2 to form the sequence 2, 3, 4.
     */
    public void Chi(){
        Majiang riverLastCard;
        // get the card discarded by the previous player from the river
        if ( true ) {
            // detect is the card from the 上家


            riverLastCard = ShuffleMajiang.river.get(ShuffleMajiang.riverIndex - 1);
            // iterate through the player's cards to find
            // if the player have 2 same cards with the card just been discarded by the previous player
            int frequency = Collections.frequency(playerMajiangs, riverLastCard);
            if (frequency >= 2) {
                // remove the cards from the player's cards
                playerMajiangs.remove(riverLastCard);
                playerMajiangs.remove(riverLastCard);
                // add these 2 cards aside to display the Chi cards
                cardsToDisplay.add(riverLastCard);
                cardsToDisplay.add(riverLastCard);
            }
        }
    }


    /**
     * Peng: When a tile discarded by another player
     *       matches the two identical tiles in your hand to form a set.
     *       Whether it's your turn to play or not, you can choose to peng the set.
     *       For example, if you already have two 8 tiles and another player discards an 8,
     *       you can collide the 8 to form the set 8, 8, 8.
     *
     */
    public void Peng(){
        // get the last card in the river (have been displayed)
        Majiang riverLastCard = ShuffleMajiang.river.get(ShuffleMajiang.riverIndex-1);
        // iterate through the player's cards to find if the player have 2 same cards with every last card in the river
        int frequency = Collections.frequency(playerMajiangs, riverLastCard);
        // if the player have 2 same cards with one card in the river
        if (frequency>=2) {
            // remove these 2 cards from the player's card
            playerMajiangs.remove(riverLastCard);
            playerMajiangs.remove(riverLastCard);
            // add these 2 cards to a new array to display the Peng cards
            cardsToDisplay.add(riverLastCard);
            cardsToDisplay.add(riverLastCard);
        }
    }

    /**
     * Gang: When you can have the same 4 cards. It divides to overt gang and covert gang.
     *      Overt Gang: When you have three same tiles and another player discards the rest tile,
     *                  you can choose to kong the forth tile and show them in public.
     *      Covert Gang: When you have three same tiles, and you draw the forth tile by yourself,
     *                  this make up the covert kong, and you don't need to show them in public.
     * However, in this game, we will display the Gang cards anyway.
     */
    public void Gang(){
        // get the last card in teh river (have been displayed)
        Majiang riverLastCard = ShuffleMajiang.river.get(ShuffleMajiang.riverIndex-1);
        // iterate through the player's cards to find if the player have 2 same cards with every last card in the river
        int frequency = Collections.frequency(playerMajiangs, riverLastCard);
        if (frequency>=3) {
            // remove these 2 cards from the player's card
            playerMajiangs.remove(riverLastCard);
            playerMajiangs.remove(riverLastCard);
            playerMajiangs.remove(riverLastCard);
            // add these 2 cards to a new array to display the Peng cards
            cardsToDisplay.add(riverLastCard);
            cardsToDisplay.add(riverLastCard);
            cardsToDisplay.add(riverLastCard);
        }
    }
}
