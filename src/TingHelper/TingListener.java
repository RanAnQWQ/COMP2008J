package TingHelper;

import HuHelper.Hu;
import Player.Player;
import tiles.Tilemap;

import java.util.ArrayList;
import java.util.HashMap;

public class TingListener {

    Hu hu=new Hu();

    ArrayList<Integer> TingThrowTiles=new ArrayList<>();

    public boolean isTing(Player player){
        Throw_NeedPairs(player);
        if(!player.pairs.isEmpty()){
            player.isTing=true;
            return true;
        }
        return false;
    }
    public void Throw_NeedPairs(Player player) {
        ArrayList<Integer> hand = new ArrayList<>(player.getPlayerMajiangs()); // 使用 ArrayList 构造函数克隆列表
        ArrayList<Integer> HuTiles = new ArrayList<>(player.getCardsToDisplay()); // 使用 ArrayList 构造函数克隆列表
        //ArrayList<Integer> TingTiles = new ArrayList<>();  初始化 TingTiles ArrayList
        Hu hu = new Hu(); // 实例化 Hu 对象（如果尚未完成）

        if (hand.size() + HuTiles.size() >= 14 && !hu.isHu(hand, HuTiles)) {
            for (Integer tile : hand) {
                ArrayList<Integer> tempHand = new ArrayList<>(hand); // 为每次迭代创建手的临时副本

                tempHand.remove(tile); // 从临时副本中移除当前牌

                for (Integer i = 11; i <= 47; i++) { // 遍历所有可能的牌
                    if (i >= 11 && i <= 19 || i >= 21 && i <= 29 || i >= 31 && i <= 39 || i >= 41 && i <= 47) {
                        tempHand.add(i); // 将当前牌添加到临时副本中
                        if (hu.isHu(tempHand, HuTiles)) { // 使用临时手检查 Hu
                            player.TingTiles.add(i);
                        }
                        tempHand.remove(i); // 从临时副本中移除当前牌
                    }
                }
                if(!player.TingTiles.isEmpty()){
                    player.pairs.put(tile, new ArrayList<>(player.TingTiles)); // 将 TingTiles 的副本放入 pairs 映射中
                    player.TingTiles.clear(); // 清除 TingTiles 以供下一次迭代使用
                    TingThrowTiles.add(tile);
                }

            }
        }
    }
}