package GameTable;

import Majiang.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ShuffleMajiang: this class aims to create and shuffle the cards
 */
public class ShuffleMajiang {
    // store all the cards
    public static List<Majiang> maJiangs=new ArrayList<Majiang>();

    // the current index of all the cards
    public static int maJiangsIndex=0;

    // river: the cards been discarded
    public static List<Majiang> river=new ArrayList<Majiang>();

    // current index of the cards in the river
    public static int riverIndex=0;


    public ShuffleMajiang() throws InterruptedException {
        //create majiangs
        createMaJiangs();
        // shuffle the cards
        chaosMaJiangs();
        // print the cards
        System.out.println("【洗牌后所有的牌：】");
        printMaJiangs(0);
        System.out.println("【按照东南西北四个方向输出的牌(all the cards in order)：】");
        printMaJiangs(1);
        // restart teh indexes
        riverIndex=0;
        maJiangsIndex=135;
    }

    /**
     * create majiangs in one game
     * @throws InterruptedException
     */
    private void createMaJiangs() throws InterruptedException {
        // insert cards by their types
        for (int i = 1; i <= 10 ; i++ ) {
            // create Number cards: Wan, Tiao, Tong
            if (i<=3) {
                // j: the value of the cards
                for (int j = 1; j <= 9; j++) {
                    // g: 4 packs of cards in total
                    for (int g = 1; g <= 4; g++) {
                        MajiangNumber maJiangNumber=(MajiangNumber) createMajiang(i);
                        maJiangNumber.setNumber(j);
                        maJiangs.add(maJiangsIndex, maJiangNumber);
                        maJiangsIndex++;
                    }
                }
            // create other Feng cards: North Feng, South Feng, East Feng, West Feng, Zhong, Bai Ban, Fa Cai
            }else {
                // g: 4 packs of cards in total
                for (int g = 1; g <= 4; g++) {
                    MajiangFeng maJiangFeng=(MajiangFeng) createMajiang(i);
                    maJiangs.add(maJiangsIndex, maJiangFeng);
                    maJiangsIndex++;
                }
            }
        }

        Thread.sleep(100);
    }

    /**
     * print out the cards
     * @param direction 0表示输出为一行，1表示按照四个方位进行输出
     * @throws InterruptedException
     */
    public void printMaJiangs(int direction) throws InterruptedException {
        for (int i=0;i<maJiangs.size();i++) {
            //以下是按照方位输出
            if (direction==1) {
                if (i==0) {
                    System.out.print("【桌子东方（玩家）】");
                }else if(i==34){
                    System.out.print("\n【桌子南方（电脑）】");
                }else if(i==68){
                    System.out.print("\n【桌子西方（电脑）】");
                }else if(i==102){
                    System.out.print("\n【桌子北方（电脑）】");
                }
            }
            Majiang maJiangTemp=maJiangs.get(i);
            int type = maJiangTemp.getType();
            //输出字牌
            if (type<=3) {
                MajiangNumber maJiangNumber=(MajiangNumber) maJiangTemp;
                System.out.print(maJiangNumber+",");
                //输出风牌
            }else {
                MajiangFeng majiangFeng=(MajiangFeng) maJiangTemp;
                System.out.print(majiangFeng+",");
            }
        }
        System.out.println();
        //睡一会，显得更加逼真
        Thread.sleep(100);
    }

    /**
     * shuffle the cards created in order
     */
    private void chaosMaJiangs(){
        Collections.shuffle(maJiangs);
    }


    /**
     * print the river
     * @throws InterruptedException
     */
    public void printMaJiangsRiver() throws InterruptedException {
        for (int i=0;i<river.size();i++) {
            Majiang maJiangTemp=river.get(i);
            int type = maJiangTemp.getType();
            //输出字牌
            if (type<=3) {
                MajiangNumber maJiangNumber=(MajiangNumber)maJiangTemp;
                System.out.print(maJiangNumber+",");
                //输出风牌
            }else {
                MajiangFeng maJiangWind=(MajiangFeng) maJiangTemp;
                System.out.print(maJiangWind+",");
            }
        }
        System.out.println();
        //睡一会，显得更加逼真
        Thread.sleep(100);
    }

    /**
     * create a Majiang card
     * @param type: the card type wanted
     */
    public Majiang createMajiang(int type){
        //一次只允许创建一张麻将
        Majiang m=null;

        if (type<=3) {
            m=new MajiangNumber();
            m.setType(type);
        }else {
            m=new MajiangFeng();
            m.setType(type);
        }
        return m;
    }
}