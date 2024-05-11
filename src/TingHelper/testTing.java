package TingHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * A simple test to check if the TingListener class run correctly
 * @author Ran An
 */
public class testTing {
    @Test
    public void ting() {
        // 1 Tiao、1 Tiao
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(11, 11, 13, 12, 12));
        // 1 Tiao、2 Tiao、3 Tiao、4 Tiao、4 Tiao
        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(11, 12, 13, 14, 16));
        // 1 Tiao、2 Tiao、3 Tiao、2 Wan、3 Wan、4 Wan、4 Tiao、4 Tiao
        ArrayList<Integer> c = new ArrayList<>(Arrays.asList(11, 12, 13, 32, 33, 36, 14, 14));
        // 1 Tiao、2 Tiao、3 Tiao、2 Wan、3 Wan、4 Wan、east、east、east、4 Tiao、4 Tiao
        ArrayList<Integer> d = new ArrayList<>(Arrays.asList(11, 12, 13, 32, 33, 34, 31, 31, 17, 14, 14));
        ArrayList<Integer> e = new ArrayList<>(Arrays.asList(11, 12, 13, 32, 33, 26, 31, 31, 31, 14, 14));
        // 1 Tiao、2 Tiao、3 Tiao、2 Wan、3 Wan、4 Wan、east、west、south、4Wan、4Wan
        ArrayList<Integer> f = new ArrayList<>(Arrays.asList(11, 12, 16, 32, 33, 34, 41, 42, 43, 14, 14));


        TingListener Listener=new TingListener();
        Assert.assertTrue(Listener.isTing(a, new ArrayList<>(Arrays.asList( 21, 21, 21, 23, 24, 25, 31, 32, 33)), 2, 1, 0));
        Assert.assertTrue(Listener.isTing(b, new ArrayList<>(Arrays.asList(15, 15, 15, 21, 21, 21, 21, 31, 32, 33)), 1, 1, 1));
        Assert.assertTrue(Listener.isTing(c, new ArrayList<>(Arrays.asList(15, 15, 15, 21, 21, 21, 21)), 0, 1, 1));
        Assert.assertTrue(Listener.isTing(d, new ArrayList<>(Arrays.asList(15, 15, 15)), 0, 1, 0));
        Assert.assertFalse(Listener.isTing(e, new ArrayList<>(Arrays.asList(21, 21)), 1, 0, 0));
        Assert.assertFalse(Listener.isTing(f, new ArrayList<>(Arrays.asList(21,21,21)), 1, 0, 0));
        System.out.println("Can a tiles Ting?" + (Listener.isTing(a, new ArrayList<>(Arrays.asList(12, 12, 12, 21, 21, 21, 23, 24, 25, 31, 32, 33)), 2, 2, 0) ? "胡" : "不胡"));
        System.out.println("Can b tiles Ting?" + (Listener.isTing(b, new ArrayList<>(Arrays.asList(15, 15, 15, 21, 21, 21, 21, 31, 32, 33)), 1, 1, 1) ? "胡" : "不胡"));
        System.out.println("Can c tiles Ting?" + (Listener.isTing(c, new ArrayList<>(Arrays.asList(15, 15, 15, 21, 21, 21, 21)), 0, 1, 1) ? "胡" : "不胡"));
        System.out.println("Can d tiles Ting?" + (Listener.isTing(d, new ArrayList<>(Arrays.asList(15, 15, 15)), 0, 1, 0) ? "胡" : "不胡"));
        System.out.println("Can e tiles Ting?" + (Listener.isTing(e, new ArrayList<>(Arrays.asList(21, 21)), 1, 0, 0) ? "胡" : "不胡"));
        System.out.println("Can f tiles Ting?" + (Listener.isTing(f, new ArrayList<>(Arrays.asList(31, 31, 31)), 1, 0, 0) ? "胡" : "不胡"));
    }
}
