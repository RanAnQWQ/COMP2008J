package HuHelper;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple test to check if the HuHelper class run correctly
 * @author Ran An
 */
public class testHu {
    @Test
    public void test() {
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
        ArrayList<Integer> f =new ArrayList<>(Arrays.asList(11, 12, 13, 32, 33, 34, 41, 42, 43, 34, 34));


        Assert.assertTrue(Hu.isHu(a, new ArrayList<>(Arrays.asList(12,12,12,21,21,21,23,24,25,31,32,33))));
        Assert.assertTrue(Hu.isHu(b,new ArrayList<>(Arrays.asList(15,15,15,21,21,21,21,31,32,33))));
        Assert.assertTrue(Hu.isHu(c,new ArrayList<>(Arrays.asList(15,15,15,21,21,21,21))));
        Assert.assertTrue(Hu.isHu(d,new ArrayList<>(Arrays.asList(15,15,15))));
        //test for incorrect tile number
        Assert.assertFalse(Hu.isHu(e,new ArrayList<>(Arrays.asList(21,21))));
        //test for Feng card, Feng card can only in Ke form
        Assert.assertFalse(Hu.isHu(f,new ArrayList<>(Arrays.asList(31,31))));
        System.out.println("Can a tiles Hu? " + (Hu.isHu(a, new ArrayList<>(Arrays.asList(12,12,12,21,21,21,23,24,25,31,32,33))) ? "yes" : "no"));
        System.out.println("Can b tiles Hu? " + (Hu.isHu(b,new ArrayList<>(Arrays.asList(15,15,15,21,21,21,21,31,32,33))) ? "yes" : "no"));
        System.out.println("Can c tiles Hu? " + (Hu.isHu(c,new ArrayList<>(Arrays.asList(15,15,15,21,21,21,21))) ? "yes" : "no"));
        System.out.println("Can d tiles Hu? " + (Hu.isHu(d,new ArrayList<>(Arrays.asList(15,15,15))) ? "yes" : "no"));
        System.out.println("Can e tiles Hu? " + (Hu.isHu(e,new ArrayList<>(Arrays.asList(21,21))) ? "yes" : "no"));
        System.out.println("Can f tiles Hu? " + (Hu.isHu(f,new ArrayList<>(Arrays.asList(31,31,31))) ? "yes" : "no"));
    }
}