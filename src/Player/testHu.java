
package Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple test to check if the HuHelper class run correctly
 *
 * @author Ran An
 */

public class testHu {
    @Test
    public void test() {
        HumanPlayer humanPlayer=new HumanPlayer();

        // 1 Tiao、1 Tiao
        ArrayList<Integer> a =new ArrayList<>(Arrays.asList(11, 11));
        // 1 Tiao、2 Tiao、3 Tiao、4 Tiao、4 Tiao
        ArrayList<Integer> b =new ArrayList<>(Arrays.asList(11, 12, 13, 14, 14));
        // 1 Tiao、2 Tiao、3 Tiao、2 Wan、3 Wan、4 Wan、4 Tiao、4 Tiao
        ArrayList<Integer> c =new ArrayList<>(Arrays.asList(11, 12, 13, 32, 33, 34, 14, 14));
        // 1 Tiao、2 Tiao、3 Tiao、2 Wan、3 Wan、4 Wan、east、east、east、4 Tiao、4 Tiao
        ArrayList<Integer> d =new ArrayList<>(Arrays.asList(11, 12, 13, 32, 33, 34, 41,41,41, 14, 14));
        ArrayList<Integer> e =new ArrayList<>(Arrays.asList(11, 12, 13, 32, 33, 34, 41,41,41, 14, 14));
        // 1 Tiao、2 Tiao、3 Tiao、2 Wan、3 Wan、4 Wan、east、west、south、4Wan、4Wan
        ArrayList<Integer> f =new ArrayList<>(Arrays.asList(11, 12, 13, 32, 33, 34, 34, 34, 41, 42, 43));


        Assert.assertTrue(humanPlayer.isHu(a));
        Assert.assertTrue(humanPlayer.isHu(b));
        Assert.assertTrue(humanPlayer.isHu(c));
        Assert.assertTrue(humanPlayer.isHu(d));
        //test for incorrect tile number
        Assert.assertTrue(humanPlayer.isHu(e));
        //test for Feng card, Feng card can only in Ke form
        Assert.assertFalse(humanPlayer.isHu(f));
        System.out.println("Can a tiles Hu? " + (humanPlayer.isHu(a) ? "yes" : "no"));
        System.out.println("Can b tiles Hu? " + (humanPlayer.isHu(b) ? "yes" : "no"));
        System.out.println("Can c tiles Hu? " + (humanPlayer.isHu(c) ? "yes" : "no"));
        System.out.println("Can d tiles Hu? " + (humanPlayer.isHu(d) ? "yes" : "no"));
        System.out.println("Can e tiles Hu? " + (humanPlayer.isHu(e) ? "yes" : "no"));
        System.out.println("Can f tiles Hu? " + (humanPlayer.isHu(f) ? "yes" : "no"));
    }
}
