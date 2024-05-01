package Player;

import GameTable.ShuffleMajiang;
import Majiang.Majiang;
import Majiang.MajiangFeng;
import Majiang.MajiangNumber;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Administrator
 * 玩家
 */
public class Player {
    public boolean isBoss;
    /**
     * 玩家姓名
     */
    private String name;
    /**
     * 每个玩家的牌都放在playerMaJiangs中
     */
    private List<Majiang> playerMajiangs=new ArrayList<Majiang>();
    /**
     * 用来指示“每个玩家的牌”在playerMaJiangs中的下标
     */
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
     * 起牌:从ShuffleMaJiang中的maJiangs中取一粒麻将，放入到自己的playerMaJiangs中
     * @param index “从ShuffleMaJiang中的maJiangs”获取麻将的指定的位置
     */
    public void gainMajiang(int index){
        //从ShuffleMaJiang中的maJiangs中取一粒麻将，放入到自己的playerMaJiangs中
        playerMajiangs.add(playerMajiangsIndex, ShuffleMajiang.maJiangs.get(index));
        playerMajiangsIndex++;
        //原来的ShuffleMaJiang的maJiangs中的牌减少这一粒
        ShuffleMajiang.maJiangs.remove(index);
    }

    /**
     * 打牌：从自己的playerMaJiangs中取一粒牌，放入到ShuffleMaJiang中的river中
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
     * 输出该玩家拥有的牌
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
