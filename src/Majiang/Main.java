package Majiang;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


/*

    //set 4 players
    static List<Majiang> list_one, list_two, list_three, list_four;

    public static void main(String[] args) {

        // welcome and roll the dice, then enter the game


        // preparation

        //shuffle the cards
        List<Majiang> cardList = shuffleCards();
        //dealing the cards
        playingMaJiang(cardList);
        // order the cards
        maJiangCollection();
        // output the cards
        printAllMJ();

        // 开始打牌
    }

    // display the cards in the player
        public static void printAllMJ(){
    }
            //封装：将四个人手中的麻将排序
            public static void maJiangCollection(){
                Collections.sort(list_one);
                Collections.sort(list_two);
                Collections.sort(list_three);
                Collections.sort(list_four);
            }

            //封装：发麻将
            public static void playingMaJiang(List<Majiang> cardList){
                list_one = cardList.subList(0, 13);
                list_two = cardList.subList(13, 26);
                list_three = cardList.subList(26, 39);
                list_four = cardList.subList(39, 52);
            }

            //封装欢迎
            public static void welcome(){
                System.out.println("欢迎来到欢乐菠菜娱乐中心");
                System.out.println("请选择您的方位:1-东方;2-南方;3-西方;4-北方");
                int direction= Integer.parseInt(sc.nextLine());
                //根据方位判定玩家今日的胜率
                System.out.println("祝您今天财源广进---->");
                System.out.println("请输入'xp'后,按回车开始进行洗牌发牌操作...");
            }


            //封装：洗牌操作
            public static List<Majiang> shuffleCards(){
                //1.定义返回牌型集合
                List<Majiang> list = new ArrayList<>();
                //2.定义花色
                String[] color = {"条","筒","万"};
                //3.定义字符
                String[] num = {"一", "二", "三", "四", "五", "六", "七", "八","九"};
                //4.给每个牌一个数字方便后期排序
                int index=0;
                //4.定义随机数
                Random r = new Random();
                for(int i=0;i<color.length;i++){
                    for(int j=0;j<num.length;j++){
                        //每个牌型四张牌,但是index不一样
                        list.add(new Majiang(color[i],num[j],index++));
                        list.add(new Majiang(color[i],num[j],index++));
                        list.add(new Majiang(color[i],num[j],index++));
                        list.add(new Majiang(color[i],num[j],index++));
                    }
                }
                //使用经典Collections.shuffle打乱顺序
                Collections.shuffle(list);
                return list;
            }
        }

    }

    */
}