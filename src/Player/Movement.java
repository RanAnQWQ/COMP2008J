package Player;

import GameTable.*;
import java.util.ArrayList;
import java.util.Collections;


public class Movement {
    // player's cards
    public ArrayList<Integer> playerMajiangs=new ArrayList<Integer>();

    // cards to display after performing movements
    public ArrayList<Integer> cardsToDisplay = new ArrayList<Integer>();

    public ArrayList<Integer> getPlayerMajiangs() {
        return playerMajiangs;
    }

    public ArrayList<Integer> getCardsToDisplay() {
        return cardsToDisplay;
    }
    // the number of performing Chi
    public int ChiNumber;

    // the number of performing Peng
    public int PengNumber;

    // the number of performing Gang
    public int GangNumber;

    // this constructor is empty, for test
    public Movement(){}

    // this movement can be performed anytime
    public Movement(ArrayList<Integer> playerMajiangs){
        this.playerMajiangs = playerMajiangs;
        int riverLastCard = ShuffleMajiang.riverIndex-1;
        Peng(riverLastCard);
        Gang(riverLastCard);
    }

    // this movement will be performed only "in my turn"
    public Movement(ArrayList<Integer> playerMajiangs, int riverLastCard){
        this.playerMajiangs = playerMajiangs;
        isChi(riverLastCard);
        Peng(riverLastCard);
        Gang(riverLastCard);
    }


    /**
     * Aside: put this card aside to display.
     *        It starts by removing this card from the player's hand, 
     *        then add this card to the display array.
     */
    public void Aside(int card){
        // add this card aside to display the card
        cardsToDisplay.add(playerMajiangs.indexOf(card));
        // remove the card from the player's card
        playerMajiangs.remove(playerMajiangs.indexOf(card));
    }

    
    
    /**
     * Chi: When you can form a sequence with a tile discarded by your previous player,
     *      you can choose to eat this tile to complete the sequence.
     *      For example, if your hand tiles are 3, 4, 5, and your previous player discards 2,
     *      you can eat the 2 to form the sequence 2, 3, 4.
     */
    public boolean isChi(int card){
        // the neighbours of the card
        int neighbourLeft = card - 1;
        int neighbourRight = card + 1;
        //remove Feng tiles
        int[] feng={41,42,43,44,45,46,47};
        for (Integer i:feng){
            if(card==i){
                return false;
            }
        }
        // if the card both have left and right neighbour, its neighbours and itself will be a sequence
        if ( playerMajiangs.contains(neighbourRight) && playerMajiangs.contains(neighbourLeft) ) {
            Aside(neighbourLeft);
            Aside(neighbourRight);
            // add this card aside to display the card
            cardsToDisplay.add(playerMajiangs.indexOf(card));
            // remove this card from the river
            ShuffleMajiang.river.remove(playerMajiangs.indexOf(card));
            // add up the times of Chi
            // Chi is performed
            return true;
        }
        // if the card only have right neighbour, its right neighbour,the right neighbour of its right neighbour and itself will be a sequence
        else if ( playerMajiangs.contains(neighbourRight) && ! playerMajiangs.contains(neighbourLeft) ) {
            Aside(neighbourRight);
            Aside(neighbourRight + 1);
            // add this card aside to display the card
            cardsToDisplay.add(playerMajiangs.indexOf(card));
            // remove this card from the river
            if(ShuffleMajiang.river!=null){
                ShuffleMajiang.river.remove(playerMajiangs.indexOf(card));
            }

            // add up the times of Chi
            // Chi is performed
            return true;
        }
        // if the card only have left neighbour, its left neighbour,the left neighbour of its left neighbour and itself will be a sequence
        else if ( playerMajiangs.contains(neighbourLeft) && ! playerMajiangs.contains(neighbourRight) ) {
            Aside(neighbourLeft);
            Aside(neighbourLeft - 1);
            // add this card aside to display the card
            cardsToDisplay.add(playerMajiangs.indexOf(card));
            // remove this card from the river
            ShuffleMajiang.river.remove(playerMajiangs.indexOf(card));
            // add up the times of Chi
            // Chi is performed
            return true;
        }
        return false;
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
    public boolean Peng(int card){
        // iterate through the player's cards to find if the player have 2 same cards with every last card in the river
        int frequency = Collections.frequency(playerMajiangs, card);
        // if the player have 2 same cards with one card in the river
        if (frequency == 2) {
            // remove these 2 same cards from the player's card
            // then add these 2 same cards to a new array to display the Peng cards
            Aside(card);
            Aside(card);
            // add this card aside to display the card
            cardsToDisplay.add(playerMajiangs.indexOf(card));
            // remove this card from the river
            ShuffleMajiang.river.remove(playerMajiangs.indexOf(card));
            // add up the times of Peng
            // Peng is performed
            return true;
        }

        return false;
        // test
        //System.out.println(PengNumber);
    }

    /**
     * Gang: When you can have the same 4 cards. It can be divided into overt gang and covert gang.
     *      Overt Gang: When you have three same tiles and another player discards the rest tile,
     *                  you can choose to gang the forth tile and show them in public.
     *      Covert Gang: When you have three same tiles, and you draw the forth tile by yourself,
     *                  this make up the covert gang, and you don't need to show them in public.
     * In this case, the overt gang is shown below.
     */
    public boolean Gang(int card){
        // iterate through the player's cards to find if the player have 3 same cards with this card
        int frequency = Collections.frequency(playerMajiangs, card);
        if (frequency==3) {
            // remove these 3 cards from the player's card
            // then add these 3 cards to a new array to display the Gang cards
            Aside(card);
            Aside(card);
            Aside(card);
            // add this card aside to display the card
            cardsToDisplay.add(playerMajiangs.indexOf(card));
            // remove this card from the river
            ShuffleMajiang.river.remove(playerMajiangs.indexOf(card));
            // add up the times of Gang
            // Gang is performed
            return true;
        }
        return false;
        // test
        //System.out.println(GangNumber);
    }
}

