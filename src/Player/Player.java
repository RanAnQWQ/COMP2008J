package Player;

import GameTable.ShuffleMajiang;
import Majiang.Majiang;
import Majiang.MajiangFeng;
import Majiang.MajiangNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Player class aims to
 * @author Qiyue Zhu
 */
public class Player {
    // the boss is decided in the GameWindow and will be assigned in the Game class.
    public boolean isHost;

    // the name of the player
    private String name;

    // the number of performing Chi
    public int ChiNumber = 0;

    // the number of performing Peng
    public int PengNumber = 0;

    // the number of performing Gang
    public int GangNumber = 0;

    // every player's cards
    public ArrayList<Integer> playerMajiangs=new ArrayList<Integer>();

    // cards to display after performing movements
    public ArrayList<Integer> cardsToDisplay = new ArrayList<Integer>();
    public ArrayList<Integer> getCardsToDisplay() {
        return cardsToDisplay;
    }


    // note the index of cards of the player
    private int playerMajiangsIndex=0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getPlayerMajiangs() {
        return playerMajiangs;
    }

    public void setPlayerMajiangs(ArrayList<Integer> playerMajiangs) {
        this.playerMajiangs = playerMajiangs;
    }

    @Override
    public String toString() {
        return "Player [name=" + name + "]";
    }

    /**
     * gainMajiang: pick one from maJiangs in ShuffleMaJiang and put it in playerMaJiangs
     * @param index: get the position of the card
     */
    public void gainMajiang(int index){
        // pick a card from the cards and put it into the player's hand
        playerMajiangs.add(playerMajiangsIndex, ShuffleMajiang.maJiangs.get(index));
        playerMajiangsIndex++;
        // remove this card from maJiangs
        ShuffleMajiang.maJiangs.remove(index);
        Collections.sort(playerMajiangs);
    }
    // this operation will be performed in "all turn"
    public void operationTest(Movement movement){
        movement.playerMajiangs = this.playerMajiangs;
        int riverLastCard = ShuffleMajiang.riverIndex-1;
        if(movement.Peng(riverLastCard)){
            PengNumber++;
        }
        if(movement.isChi(riverLastCard)){
            ChiNumber++;
        }
        if(movement.Gang(riverLastCard)){
            GangNumber++;
        }
    }

    // this operation will be performed only "in my turn"
    public void  operationTest(Movement movement, int riverLastCard){
        movement.playerMajiangs = this.playerMajiangs;
        if(movement.Peng(riverLastCard)){
            PengNumber++;
        }
        if(movement.isChi(riverLastCard)){
            ChiNumber++;
        }
        if(movement.Gang(riverLastCard)){
            GangNumber++;
        }
    }

}

