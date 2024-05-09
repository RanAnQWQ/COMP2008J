package Player;

import GameTable.ShuffleMajiang;
import Majiang.*;

import java.util.ArrayList;
import java.util.List;

public class Computer extends Player{
    public boolean isBoss;
    private String name;

    // placing the players' cards
    private List<Majiang> playerMajiangs=new ArrayList<Majiang>();

    // to note every index of player's cards in playerMaJiangs
    private int playerMajiangsIndex=0;

    public List<Majiang> getPlayerMajiangs() {
        return playerMajiangs;
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
            System.out.println("输入的牌不存在");
            return null;
        }
        //从自己的playerMaJiangs中取一粒牌，放入到ShuffleMaJiang中的river中
        ShuffleMajiang.river.add(ShuffleMajiang.riverIndex, playerMajiangs.get(index-1));
        ShuffleMajiang.riverIndex++;
        //自己的playerMaJiangs中减少这一粒牌
        playerMajiangs.remove(index);
        return playerMajiangs.get(index);
    }

    /**
     * print
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
