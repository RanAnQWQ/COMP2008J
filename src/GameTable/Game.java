package GameTable;

import Player.*;


/**
 * This is the whole game process.
 *
 * To start with, we deal the cards (the number of cards are different between the boss and normal ones)
 * After that, the player and computers will gain and discard their cards in turns.
 * During the process, the chance to Chi, Peng, Gang, Ting, Hu will pop up as a button.
 * The player can choose to take the action or not, meanwhile, the computers will take that action automatically.
 *
 * @author Qiyue Zhu
 */
public class Game {
    public static void main(String[] args) throws InterruptedException {
        // initial the players
        InitPlayer initPlayer = null;

        // create 4 players, and 3 of them are computers
        Player player = null;
        Computer computer1 = null, computer2 = null, computer3 = null;

        // create and shuffle the Majiang cards
        ShuffleMajiang shuffleMajiang = null;


        // create and shuffle the cards
        shuffleMajiang=new ShuffleMajiang();

        // throw the dice
        //GameWindow win = new GameWindow();
        //int diceNum= win.sum;

        // decide the boss


        // create players
        initPlayer = new InitPlayer();
        // deal the cards the first time evenly
        initPlayer.haveFirstBoard();


        // print the players' cards
        initPlayer.printPlayer();
        //输出牌局中的整副牌（剩余的牌）
        System.out.println("【此时牌局中的剩余的牌：】");
        shuffleMajiang.printMaJiangs(0);


        // add one more card to the boss
        initPlayer.haveJumpBoard();


        //输出此时玩家双手中的牌
        initPlayer.printPlayer();
        //输出牌局中的整副牌（剩余的牌）
        System.out.println("【此时牌局中的剩余的牌：】");
        shuffleMajiang.printMaJiangs(0);


        // change this according to the listener
        System.out.println("【 您是庄家，请打一张牌。 请输入:[待打出牌对应的顺序，例如：1表示打出第一张牌] 】");
        //String inputdiscardBoard = scanner.nextLine();

        player=initPlayer.players.get(0);
        computer1=(Computer) initPlayer.players.get(1);
        computer2=(Computer) initPlayer.players.get(2);
        computer3=(Computer) initPlayer.players.get(3);


        // discard a card
        //player.discardMajiang(Integer.valueOf(inputdiscardBoard)-1);


        //输出此时玩家双手中的牌
        System.out.println("【此时玩家手中的牌：】");
        player.printMaJiangs();
        //输出河里的牌
        System.out.println("\n【河里牌：】");
        shuffleMajiang.printMaJiangsRiver();





        while (true) {
            // computer turn
            computer1.gainMajiang(0);
            // computer discard a card
            System.out.println("【电脑打出了："+computer1.discardMajiang(1)+"】");

            // player's turn
            System.out.println("【开始打牌：请输入:[1:起牌]，[2:打牌]，[3:碰牌]，[4:杠牌]，[5:胡牌]，[6:不做操作]，[其他输入:退出游戏]】");
            String inputOperate = "1";
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
