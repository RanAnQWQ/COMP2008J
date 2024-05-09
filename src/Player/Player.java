package Player;

import GameTable.ShuffleMajiang;
import Majiang.Majiang;
import Majiang.MajiangFeng;
import Majiang.MajiangNumber;

import java.util.ArrayList;
import java.util.List;


/**
 * Player class aims to
 * @author Qiyue Zhu
 */
public class Player {
    public boolean isBoss;

    // the name of the player
    private String name;

    // every player's cards
    private List<Majiang> playerMajiangs=new ArrayList<Majiang>();

    // note the index of cards of the player
    private int playerMajiangsIndex=0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Majiang> getPlayerMajiangs() {
        return playerMajiangs;
    }

    public void setPlayerMajiangs(List<Majiang> playerMajiangs) {
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
        //从ShuffleMaJiang中的maJiangs中取一粒麻将，放入到自己的playerMaJiangs中
        playerMajiangs.add(playerMajiangsIndex, ShuffleMajiang.maJiangs.get(index));
        playerMajiangsIndex++;
        //原来的ShuffleMaJiang的maJiangs中的牌减少这一粒
        ShuffleMajiang.maJiangs.remove(index);
    }

    /**
     * play a hand：pick a card from playerMaJiangs, and put it into the river
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

    /**
     * to delete......................................................................
     * print the cards
     */
    public void printMaJiangs(){
        for (Majiang maJiang : playerMajiangs) {
            int type = maJiang.getType();
            //输出字牌
            if (type<=3) {
                MajiangNumber maJiangNumber=(MajiangNumber)maJiang;
                System.out.print(maJiangNumber+",");
                //输出风牌
            }else {
                MajiangFeng maJiangWind=(MajiangFeng) maJiang;
                System.out.print(maJiangWind+",");
            }
        }
    }


}
