package Player;

import GameTable.ShuffleMajiang;
import HuHelper.Hu;

import java.util.*;

/**
 * Player: define the related features of players,
 *         including gain card, discard card, chi, peng, gang, ting & hu.
 *         the gain card, discard card, chi, peng, gang are written by Qiyue Zhu,
 *         and the ting and hu related methods are written by Ran An.
 *
 * @author: Qiyue Zhu & Ran An
 */
public abstract class Player {
    // the boss is decided in the InitPlayer class
    public boolean isHost;

    // a boolean to tell if it fits the Chi condition
    public boolean isChi = false;

    // the number of performing Chi
    public static int ChiNumber;

    // the number of performing Peng
    public static int PengNumber;

    // the number of performing Gang
    public static int GangNumber;

    // cards in player's hand
    public ArrayList<Integer> playerMajiangs=new ArrayList<Integer>();

    public ArrayList<Integer> getPlayerMajiangs() {
        return playerMajiangs;
    }

    public void setPlayerMajiangs(ArrayList<Integer> playerMajiangs) {
        this.playerMajiangs = playerMajiangs;
    }

    // cards to display after performing movements
    public ArrayList<Integer> cardsToDisplay = new ArrayList<Integer>();

    public ArrayList<Integer> getCardsToDisplay() {
        return cardsToDisplay;
    }

    // player's river (the discarded cards)
    public ArrayList<Integer> playerRiver = new ArrayList<>();

    public boolean isTing;
    public boolean isHu;
    public boolean Tinging;
    public HashMap<Integer,ArrayList<Integer>> pairs=new HashMap<>();
    public ArrayList<Integer> TingTiles=new ArrayList();
    ArrayList<Integer> TingThrowTiles=new ArrayList<>();
    public ArrayList<Integer> set=new ArrayList<>();



    /**
     * gainMajiang: pick one from maJiangs in ShuffleMaJiang and put it in playerMaJiangs
     */
    public void gainMajiang(){
        // pick a card from the cards and put it into the extra place
        playerMajiangs.add(ShuffleMajiang.maJiangs.get(0));
        // remove this card from maJiangs
        ShuffleMajiang.maJiangs.remove(0);
    }

    /**
     * discardMajiang()：remove a card from the player, the card will be chosen by nextCard()
     */
    public abstract int discardMajiang(int index);


    public void discardAfterTing(){
        int card=playerMajiangs.remove(playerMajiangs.size()-1);
        playerRiver.add(card);
        ShuffleMajiang.river.add(card);
        ShuffleMajiang.riverIndex++;
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



    //Hu method
    public static boolean isWinningType(ArrayList<Integer> hand, ArrayList<Integer> HuTiles ) {
        // Implement your winning hand logic here
        // For simplicity, let's assume a winning hand is a hand with 14 tiles
        return hand.size()+ HuTiles.size() >= 14;
    }


    private static ArrayList<Integer> getPairs(ArrayList<Integer> hand) {
        Collections.sort(hand);
        ArrayList<Integer> res=new ArrayList<>();
        if (null != hand && hand.size() > 1) {
            for (int i = 0; i < hand.size() - 1; i++) {
                if (hand.get(i) == hand.get(i+1)) {
                    res.add(hand.get(i));
                    i++;
                }
            }
        }
        return res;

    }

    // Method to count the number of triplets in the hand

    private static ArrayList<Integer> removeTriplets(ArrayList<Integer> temp) {
        Iterator<Integer> iterator = temp.iterator();

        while (iterator.hasNext()) {
            Integer tile = iterator.next();
            int count = countTiles(temp, tile);

            if (count >= 3) {
                int removals = count >= 4 ? 4 : 3;  // Remove 4 if there are quadruplets, otherwise remove 3
                for (int i = 0; i < removals; i++) {
                    iterator.remove();             // Remove the current element safely
                    if (i < removals - 1) {        // If not the last iteration, move to next element
                        iterator.next();
                    }
                }
                if (iterator.hasNext()) {          // Move to next element if exists, or iterator will automatically end
                    iterator.next();
                }
            }
        }
        return temp;
    }


    // Method to count the number of sequences in the hand
    private static ArrayList<Integer> removeSequences(ArrayList<Integer> temp) {
        Collections.sort(temp);
        //remove Feng tiles
        int[] feng={41,42,43,44,45,46,47};
        for (Integer i:feng){
            if(temp.contains(i)){
                temp.remove(i);
            }
        }
        ArrayList<Integer> tempCards = (ArrayList<Integer>) temp.clone();
        for (int i = 0; i < temp.size() - 2; i++) {
            if (temp.get(i) + 1 == temp.get(i+1)&& temp.get(i+1)+ 1 == temp.get(i+2)) {
                Integer a=temp.get(i);
                Integer b=temp.get(i+1);
                Integer c=temp.get(i+2);
                tempCards.remove(a);
                tempCards.remove(b);
                tempCards.remove(c);
                i += 2; // Skip the next two tiles as they're part of the sequence
            }
        }
        return tempCards;
    }

    // Method to count the occurrences of a specific tile in the hand
    private static int countTiles(ArrayList<Integer> hand, int tile) {
        int count = 0;
        for (int t : hand) {
            if (t == tile) {
                count++;
            }
        }
        return count;
    }

    public boolean isHu(ArrayList<Integer> hand,ArrayList<Integer> HuTiles){
        if(isWinningType(hand,HuTiles)) {
            ArrayList<Integer> js = getPairs(hand);
            if (null == js || js.size() <= 0) {
                return false;
            }else{
                for (Integer j : js) {
                    ArrayList<Integer> tempCards = (ArrayList<Integer>) hand.clone();
                    tempCards.remove(j);
                    tempCards.remove(j);
                    Collections.sort(tempCards);
                    tempCards = removeTriplets(tempCards);
                    tempCards= removeSequences(tempCards);
                    if (tempCards.size() <= 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    //Ting method
    public boolean isTing(){
        Throw_NeedPairs();
        if(!this.pairs.isEmpty()){
            this.isTing=true;
            return true;
        }
        return false;
    }
    public void Throw_NeedPairs() {
        ArrayList<Integer> hand = new ArrayList<>(this.getPlayerMajiangs());   //use Arraylist to copy list
        ArrayList<Integer> HuTiles = new ArrayList<>(this.getCardsToDisplay()); // same as above
        //ArrayList<Integer> TingTiles = new ArrayList<>();  // initialize TingTiles ArrayList
        Hu hu = new Hu(); // instance Hu (if is not finished)

        if (hand.size() + HuTiles.size() >= 14 && !hu.isHu(hand, HuTiles)) {
            for (Integer tile : hand) {
                ArrayList<Integer> tempHand = new ArrayList<>(hand); // create temporary arraylist for updating every time

                tempHand.remove(tile); // remove the tile from the temporary arraylist

                for (Integer i = 11; i <= 47; i++) { // Iterate all possible tiles
                    if (i >= 11 && i <= 19 || i >= 21 && i <= 29 || i >= 31 && i <= 39 || i >= 41 && i <= 47) {
                        tempHand.add(i); // add the current tile to temporary copy
                        if (hu.isHu(tempHand, HuTiles)) { // check is hu
                            this.TingTiles.add(i);
                        }
                        tempHand.remove(i); // remove tile from copy
                    }
                }
                if(!this.TingTiles.isEmpty()){
                    this.pairs.put(tile, new ArrayList<>(this.TingTiles)); // put TingTiles copy into pairs
                    this.TingTiles.clear(); // clear TingTiles for next use;
                    TingThrowTiles.add(tile);
                }

            }
        }
    }

}

