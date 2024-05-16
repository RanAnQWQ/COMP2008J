package TingHelper;
import Player.Player;
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
        Player player1=new Player();
        Player player2=new Player();
        Player player3=new Player();
        Player player4=new Player();
        Player player5=new Player();
        TingListener Listener=new TingListener();

        player1.ChiNumber=2;
        player1.PengNumber=1;
        player1.GangNumber=0;
        player1.playerMajiangs=new ArrayList<>(Arrays.asList(11, 11, 13, 12, 12));
        player1.cardsToDisplay=new ArrayList<>(Arrays.asList( 21, 21, 21, 23, 24, 25, 31, 32, 33));

        player2.ChiNumber=1;
        player2.PengNumber=1;
        player2.GangNumber=1;
        player2.playerMajiangs= new ArrayList<>(Arrays.asList(11, 12, 13, 14, 16));
        player2.cardsToDisplay=new ArrayList<>(Arrays.asList(15, 15, 15, 21, 21, 21, 21, 31, 32, 33));

        player3.ChiNumber=0;
        player3.PengNumber=1;
        player3.GangNumber=1;
        player3.playerMajiangs= new ArrayList<>(Arrays.asList(11, 12, 13, 32, 33, 36, 14, 14));
        player3.cardsToDisplay=new ArrayList<>(Arrays.asList(15, 15, 15, 21, 21, 21, 21));

        player4.ChiNumber=1;
        player4.PengNumber=0;
        player4.GangNumber=0;
        player4.playerMajiangs=new ArrayList<>(Arrays.asList(13,14,17,32,33, 26, 31, 31, 31, 14, 14));
        player4.cardsToDisplay=new ArrayList<>(Arrays.asList(21,22,23));
        // 1 Tiao、1 Tiao
        //14,14,14,31,31,31,21,22,23\13,17,32,33,26
        // 1 Tiao、2 Tiao、3 Tiao、4 Tiao、4 Tiao
        // 1 Tiao、2 Tiao、3 Tiao、2 Wan、3 Wan、4 Wan、4 Tiao、4 Tiao
        // 1 Tiao、2 Tiao、3 Tiao、2 Wan、3 Wan、4 Wan、east、east、east、4 Tiao、4 Tiao
        //ArrayList<Integer> d = new ArrayList<>(Arrays.asList(11, 12, 13, 32, 33, 34, 31, 31, 17, 14, 14));
        // 1 Tiao、2 Tiao、3 Tiao、2 Wan、3 Wan、4 Wan、east、west、south、4Wan、4Wan
        //ArrayList<Integer> f = new ArrayList<>(Arrays.asList(11, 12, 16, 32, 33, 34, 41, 42, 43, 14, 14));


        Assert.assertTrue(Listener.isTing(player1));
        for (Integer tingTile : player1.TingTiles) {
            System.out.println(tingTile);
        }

        Assert.assertTrue(Listener.isTing(player2));
        for (Integer tingTile : player2.TingTiles) {
            System.out.println(tingTile);
        }
        Assert.assertTrue(Listener.isTing(player3));
        for (Integer tingTile : player3.TingTiles) {
            System.out.println(tingTile);
        }
        Listener.isTing(player4);
        //Assert.assertTrue(Listener.isTing(d, new ArrayList<>(Arrays.asList(15, 15, 15)), 0, 1, 0));
        Assert.assertFalse(Listener.isTing(player4));
        for (Integer tingTile : player4.TingTiles) {
            System.out.println(tingTile);
        }
        //Assert.assertFalse(Listener.isTing(f, new ArrayList<>(Arrays.asList(21,21,21)), 1, 0, 0));
        /*System.out.println("Can a tiles Ting?" + (Listener.isTing(a, new ArrayList<>(Arrays.asList(12, 12, 12, 21, 21, 21, 23, 24, 25, 31, 32, 33)), 2, 2, 0) ? "胡" : "不胡"));
        System.out.println("Can b tiles Ting?" + (Listener.isTing(b, new ArrayList<>(Arrays.asList(15, 15, 15, 21, 21, 21, 21, 31, 32, 33)), 1, 1, 1) ? "胡" : "不胡"));
        System.out.println("Can c tiles Ting?" + (Listener.isTing(c, new ArrayList<>(Arrays.asList(15, 15, 15, 21, 21, 21, 21)), 0, 1, 1) ? "胡" : "不胡"));
        System.out.println("Can d tiles Ting?" + (Listener.isTing(d, new ArrayList<>(Arrays.asList(15, 15, 15)), 0, 1, 0) ? "胡" : "不胡"));
        System.out.println("Can e tiles Ting?" + (Listener.isTing(e, new ArrayList<>(Arrays.asList(21, 21)), 1, 0, 0) ? "胡" : "不胡"));
        System.out.println("Can f tiles Ting?" + (Listener.isTing(f, new ArrayList<>(Arrays.asList(31, 31, 31)), 1, 0, 0) ? "胡" : "不胡"));*/
    }
}