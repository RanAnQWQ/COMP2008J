package Player;

import GameTable.ShuffleMajiang;
import Majiang.Majiang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Movement {
    /**
     * 每个玩家的牌都放在playerMaJiangs中
     */
    private List<Majiang> playerMajiangs=new ArrayList<Majiang>();

    public Movement(List<Majiang> playerMajiangs){
        this.playerMajiangs = playerMajiangs;
    }


    /**
     * 碰牌:河里刚打出的牌，在玩家手中有两张同样的牌，就可以碰牌
     */
    public void Peng(){
        //获取河里刚打出的牌
        Majiang riverLastJiang = ShuffleMajiang.river.get(ShuffleMajiang.riverIndex-1);
        //遍历自己的所有的牌，是否包含上面的牌，并且有两张
        int frequency = Collections.frequency(playerMajiangs, riverLastJiang);
        if (frequency>=2) {
            System.out.println("【碰牌成功】");
            //把碰的牌移除
            playerMajiangs.remove(riverLastJiang);
            playerMajiangs.remove(riverLastJiang);
            return;
        }else {
            System.out.println("【没有对应的两张牌，碰牌失败】");
        }
    }

    /**
     * 杠牌:河里刚打出的牌，在玩家手中有3张同样的牌，就可以杠牌
     */
    public void Gang(){
        //获取河里刚打出的牌
        Majiang riverLastJiang = ShuffleMajiang.river.get(ShuffleMajiang.riverIndex-1);
        //遍历自己的所有的牌，是否包含上面的牌，并且有两张
        int frequency = Collections.frequency(playerMajiangs, riverLastJiang);
        if (frequency>=3) {
            System.out.println("【杠牌成功】");
            //把杠的牌移除
            playerMajiangs.remove(riverLastJiang);
            playerMajiangs.remove(riverLastJiang);
            playerMajiangs.remove(riverLastJiang);
            return;
        }else {
            System.out.println("【没有对应的三张牌，杠牌失败】");
        }
    }

    /**
     * 胡牌：满足胡牌的规则
     */
    public void huCards(){
        //难度较大，以后再研究
    }

}
