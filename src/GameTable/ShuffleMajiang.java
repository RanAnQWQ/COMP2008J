package GameTable;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ShuffleMajiang: this class aims to create and shuffle the cards
 */
public class ShuffleMajiang {
    // store all the cards
    public static List<Integer> maJiangs=new ArrayList<Integer>();

    // the current index of all the cards
    public static int maJiangsIndex=0;

    // river: the cards been discarded
    public static List<Integer> river=new ArrayList<Integer>();

    // current index of the cards in the river
    public static int riverIndex=0;


    public ShuffleMajiang() throws InterruptedException {
        //create majiangs
        maJiangs = new ArrayList<>(135);
        for (int i = 11; i < 47; i++){
            for (int j = 1; j <= 4; j++){
                maJiangs.add(i);
            }
        }

        // shuffle the cards that have been created in order
        Collections.shuffle(maJiangs);

        // restart teh indexes
        riverIndex=0;
        maJiangsIndex=135;
    }
}