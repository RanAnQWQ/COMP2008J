package GameTable;

import Majiang.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 洗牌
 */
public class ShuffleMajiang {
    /**
     * 牌局中所有的牌：使用List放所有的牌
     */
    public static List<Majiang> maJiangs=new ArrayList<Majiang>();
    /**
     * 用来指示“牌局中所有的牌”的当前下标
     */
    public static int maJiangsIndex=0;
    /**
     * 河：用来存放玩家打出的牌
     */
    public static List<Majiang> river=new ArrayList<Majiang>();
    /**
     * 用来指示“河”的当前下标
     */
    public static int riverIndex=0;

    public ShuffleMajiang() throws InterruptedException {
        //创建一副麻将
        createMaJiangs();
        //输出此时的所有的牌
        System.out.println("【初始化所有的牌：】");
        printMaJiangs(0);
        //把初始牌打乱
        chaosMaJiangs();
        //输出此时的所有的牌
        System.out.println("【洗牌后所有的牌：】");
        printMaJiangs(0);
        System.out.println("【按照东南西北四个方向输出的牌(牌局里的牌)：】");
        printMaJiangs(1);
        //下标恢复
        riverIndex=0;
        maJiangsIndex=135;
    }

    /**
     * 创建一副麻将
     * @throws InterruptedException
     */
    private void createMaJiangs() throws InterruptedException {
        //循环中的i用来把1到10代表的牌填入数组
        for (int i = 1; i <= 10 ; i++ ) {
            //对万、条、筒进行填入
            if (i<=3) {
                //循环中的j用来存字牌的数值
                for (int j = 1; j <= 9; j++) {
                    //循环中的g表示每种字牌有4张
                    for (int g = 1; g <= 4; g++) {
                        MajiangNumber maJiangNumber=(MajiangNumber) createMajiang(i);
                        maJiangNumber.setNumber(j);
                        maJiangs.add(maJiangsIndex, maJiangNumber);
                        maJiangsIndex++;
                    }
                }
                //对 东、南、西、北、中、发、白进行填入
            }else {
                //循环中的g表示每种风牌有4张
                for (int g = 1; g <= 4; g++) {
                    MajiangFeng maJiangFeng=(MajiangFeng) createMajiang(i);
                    maJiangs.add(maJiangsIndex, maJiangFeng);
                    maJiangsIndex++;
                }
            }
        }
        System.out.println();
        //睡一会，显得更加逼真
        Thread.sleep(100);
    }

    /**
     * 输出一副牌
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
     * 把初始牌打乱
     */
    private void chaosMaJiangs(){
        Collections.shuffle(maJiangs);
    }


    /**
     * 输出河里的牌
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