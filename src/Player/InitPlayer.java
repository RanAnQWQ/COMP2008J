package Player;

import GameTable.ShuffleMajiang;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 * 初始化玩家：创建四个玩家（包括3个电脑）
 */
public class InitPlayer {
    /**
     * 4个玩家的信息都存放在List中,下标为0是人，其余的为电脑
     */
    public static List<Player> players=new ArrayList<Player>();

    private Player player=null,computer1=null,computer2=null,computer3=null;

    public InitPlayer() {
        //创建4个玩家
        createPlayer();
    }

    /**
     * 创建4个玩家
     */
    private void createPlayer(){
        player = new Player();
        computer1 = new Computer();
        computer2 = new Computer();
        computer3 = new Computer();

        player.setName("玩家");
        computer1.setName("电脑1");
        computer2.setName("电脑2");
        computer3.setName("电脑3");

        players.add(player);
        players.add(computer1);
        players.add(computer2);
        players.add(computer3);
    }

    /**
     * 输出四个玩家手中的牌
     */
    public void printPlayer(){
        //输出当前玩家手中的牌
        System.out.println("【当前玩家手中的牌如下：】");
        System.out.print("【玩家】");
        player.printMaJiangs();
        System.out.print("\n【电脑1】");
        computer1.printMaJiangs();
        System.out.print("\n【电脑2】");
        computer2.printMaJiangs();
        System.out.print("\n【电脑3】");
        computer3.printMaJiangs();
        System.out.println();
    }

    /**
     * 第一次起牌：轮流起牌，每次起4张，一共起3轮
     */
    public void haveFirstBoard(){
        //共3轮
        for (int i = 0; i < 3; i++) {
            //玩家，每次起4张
            for (int j = 0; j < 4; j++) {
                player.gainMajiang(ShuffleMajiang.maJiangsIndex);
            }
            //电脑1，每次起4张
            for (int j = 0; j < 4; j++) {
                computer1.gainMajiang(ShuffleMajiang.maJiangsIndex);
            }
            //电脑2，每次起4张
            for (int j = 0; j < 4; j++) {
                computer2.gainMajiang(ShuffleMajiang.maJiangsIndex);
            }
            //电脑3，每次起4张
            for (int j = 0; j < 4; j++) {
                computer3.gainMajiang(ShuffleMajiang.maJiangsIndex);
            }
        }
    }

    /**
     * 第二次起牌：跳庄（玩家拿第1和第5张，电脑1拿第2张，电脑2拿第3张，电脑3拿第4张）
     */
    public void haveJumpBoard(){
        player.gainMajiang(0);
        player.gainMajiang(4);
        computer1.gainMajiang(1);
        computer2.gainMajiang(2);
        computer3.gainMajiang(3);
    }

}