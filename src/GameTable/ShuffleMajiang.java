package GameTable;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * ShuffleMajiang: this class aims to create and shuffle the cards
 * @author: Qiyue Zhu
 */
public class ShuffleMajiang {
    // store all the cards
    public static List<Integer> maJiangs=new ArrayList<Integer>();

    // the current index of all the cards
    public static int maJiangsIndex=0;

    // river: the cards been discarded
    public static ArrayList<Integer> river=new ArrayList<Integer>();

    // current index of the cards in the river
    public static int riverIndex=0;



    // this method is written by Ran An
    public static ArrayList<Integer> getRiver() {
        if(!river.isEmpty()){
            return river;
        }else{
            return null;
        }
    }


    /**
     * ShuffleMajiang(): create and shuffle cards
     * @throws InterruptedException
     */
    public ShuffleMajiang() throws InterruptedException {
        Random random = new Random();
        //create majiangs and add them into maJiangs
        maJiangs = new ArrayList<>(132);
        for (int i = 11; i < 47; i++) {
            // 4 packs of Majiangs
            for ( int j = 1; j <= 4; j++) {
                // don't have the cards of 20, 30, 40
                if (i == 20 || i == 30 || i == 40){
                    break;
                }
                // add the card to the maJiangs
                maJiangs.add(i);
            }
        }
        // shuffle the cards that have been created in order
        Collections.shuffle(maJiangs);
        System.out.println(maJiangs.size());
        // restart teh indexes
        riverIndex=0;
        maJiangsIndex=135;
    }

}