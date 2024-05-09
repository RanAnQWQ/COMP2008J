package GameTable;


import Player.InitPlayer;
import Player.Movement;
import Player.Player;

import java.util.Scanner;

/**
 *
 * @author Administrator
 * The whole game process
 */
public class Game {


    public static void main(String[] args) throws InterruptedException {
        @SuppressWarnings("resource")
        Scanner scanner=new Scanner(System.in);

        //初始化玩家
        InitPlayer initPlayer=null;

        //四个玩家
        Player player=null,computer1=null,computer2=null,computer3=null;

        //麻将
        ShuffleMajiang shuffleMajiang=null;

        //骰子
        GameWindow win = new GameWindow();
        int diceNum= win.sum;

        System.out.println("【欢迎开始“麻将”小游戏。请输入:[Y:开始游戏]，[其他输入:退出游戏]】");
        String inputStart = scanner.nextLine();
        if ("Y".equalsIgnoreCase(inputStart)) {
            System.out.print("【开始初始化麻将桌...】");
            Thread.sleep(100);
            //洗牌
            shuffleMajiang=new ShuffleMajiang();
        }else {
            System.out.println("Game over, see u next time!");
            System.exit(0);
        }

        // roll the dice
        // decide the boss

        System.out.println("【掷骰子完毕，是否开始第一次起牌（轮流起牌，每次起4张，一共起3轮）？请输入:[Y:是]，[其他输入:退出游戏]】");
        String inputFirstBoard = scanner.nextLine();
        if ("Y".equalsIgnoreCase(inputFirstBoard)) {
            // create players
            initPlayer = new InitPlayer();
            //第一次起牌
            initPlayer.haveFirstBoard();
            //输出此时玩家双手中的牌
            initPlayer.printPlayer();
            //输出牌局中的整副牌（剩余的牌）
            System.out.println("【此时牌局中的剩余的牌：】");
            shuffleMajiang.printMaJiangs(0);
        }else {
            System.out.println("【游戏结束，再见！】");
            System.exit(0);
        }

        System.out.println("【第一次起牌完毕，是否开始跳庄（玩家拿第1和第5张，电脑1拿第2张，电脑2拿第3张，电脑3拿第4张）？请输入:[Y:是]，[其他输入:退出游戏]】");
        String inputJumpBoard = scanner.nextLine();
        if ("Y".equalsIgnoreCase(inputJumpBoard)) {
            //第二次起牌：跳庄
            initPlayer.haveJumpBoard();
            //输出此时玩家双手中的牌
            initPlayer.printPlayer();
            //输出牌局中的整副牌（剩余的牌）
            System.out.println("【此时牌局中的剩余的牌：】");
            shuffleMajiang.printMaJiangs(0);
        }else {
            System.out.println("【游戏结束，再见！】");
            System.exit(0);
        }

        System.out.println("【 您是庄家，请打一张牌。 请输入:[待打出牌对应的顺序，例如：1表示打出第一张牌] 】");
        String inputdiscardBoard = scanner.nextLine();

        player=initPlayer.players.get(0);
        computer1=initPlayer.players.get(1);
        computer2=initPlayer.players.get(2);
        computer3=initPlayer.players.get(3);
        //打一张牌
        player.discardMajiang(Integer.valueOf(inputdiscardBoard)-1);
        //输出此时玩家双手中的牌
        System.out.println("【此时玩家手中的牌：】");
        player.printMaJiangs();
        //输出河里的牌
        System.out.println("\n【河里牌：】");
        shuffleMajiang.printMaJiangsRiver();

        //以下是正式打麻将的逻辑，还存在很多问题
        while(true){
            //电脑起牌
            computer1.gainMajiang(0);
            //电脑打出一张牌
            System.out.println("【电脑打出了："+computer1.discardMajiang(1)+"】");

            //玩家开始
            System.out.println("【开始打牌：请输入:[1:起牌]，[2:打牌]，[3:碰牌]，[4:杠牌]，[5:胡牌]，[6:不做操作]，[其他输入:退出游戏]】");
            String inputOperate = scanner.nextLine();
            if (shuffleMajiang.maJiangs.size()==0) {
                System.out.println("【留局】");
                System.out.println("【游戏结束，再见！】");
                System.exit(0);
            }
            if ("1".equals(inputOperate)) {
                player.gainMajiang(0);
            }else if("2".equals(inputOperate)) {
                player.discardMajiang(0);
            }else if("3".equals(inputOperate)) {
                // 碰
                Movement peng = new Movement(player.getPlayerMajiangs());
                peng.Peng();
            }else if("4".equals(inputOperate)) {
                // 杠
                Movement gang = new Movement(player.getPlayerMajiangs());
                gang.Gang();
            }else if("6".equals(inputOperate)){
                //不做处理，继续下一次
                continue;
            }else {
                System.out.println("【游戏结束，再见！】");
                System.exit(0);
            }
        }
    }

}
