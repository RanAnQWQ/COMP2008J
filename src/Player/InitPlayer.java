package Player;

import GameTable.ShuffleMajiang;

import java.util.ArrayList;
import java.util.List;

/**
 * Player: create the 4 players
 * @author Qiyue Zhu
 */
public class InitPlayer {
    //store the information of each in the players
    public static List<Player> players=new ArrayList<Player>();

    private Player player=null;
    private Computer computer1=null,computer2=null,computer3=null;

    public InitPlayer() {
        // create 4 players
        createPlayer();
    }

    // create 4 players, 3 of them are computers
    private void createPlayer(){
        player = new Player();
        computer1 = new Computer();
        computer2 = new Computer();
        computer3 = new Computer();

        player.setName("Player");
        computer1.setName("Computer1");
        computer2.setName("Computer2");
        computer3.setName("Computer3");

        players.add(player);
        players.add(computer1);
        players.add(computer2);
        players.add(computer3);
    }

    /**
     * only for testing, to delete......................................................................
     * print the cards
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

    // deal the cards to players for the first time
    public void haveFirstBoard(){
        // 3 turns in total
        for (int i = 0; i < 3; i++) {
            // player, get 4 cards at a time
            for (int j = 0; j < 4; j++) {
                player.gainMajiang(ShuffleMajiang.maJiangsIndex);
            }
            // computer1, get 4 cards at a time
            for (int j = 0; j < 4; j++) {
                computer1.gainMajiang(ShuffleMajiang.maJiangsIndex);
            }
            // computer2, get 4 cards at a time
            for (int j = 0; j < 4; j++) {
                computer2.gainMajiang(ShuffleMajiang.maJiangsIndex);
            }
            // computer3, get 4 cards at a time
            for (int j = 0; j < 4; j++) {
                computer3.gainMajiang(ShuffleMajiang.maJiangsIndex);
            }
        }
    }

    /**
     * change this based on the "isBoss"
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