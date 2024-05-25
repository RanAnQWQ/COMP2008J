package Player;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * TestIsPeng: test the isChi(int card) in the Player class
 *
 * @author: Qiyue Zhu
 */
public class TestIsChi {
    HumanPlayer player = new HumanPlayer();


    // 24, 25, 26, 27, 28; card: 26
    // set: 24, 25, 26, 25, 26, 27, 26, 27, 28
    @Test
    public void testIsChiWith4Neighbours() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(24);
        cards.add(25);
        cards.add(27);
        cards.add(28);
        player.playerMajiangs = cards;
        ArrayList<Integer> set= player.isChi(26);
        // Assert
        ArrayList<Integer> correctSet = new ArrayList<>();
        correctSet.add(24);
        correctSet.add(25);
        correctSet.add(26);
        correctSet.add(25);
        correctSet.add(26);
        correctSet.add(27);
        correctSet.add(26);
        correctSet.add(27);
        correctSet.add(28);
        assertEquals(correctSet, set);
        //assertEquals(true, player.isChi);
    }



    // 25, 26, 27, 28; card: 26
    // set: 25, 26, 27, 26, 27, 28
    @Test
    public void testIsChiWith3Neighbours1() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(25);
        cards.add(27);
        cards.add(28);
        player.playerMajiangs = cards;
        ArrayList<Integer> set= player.isChi(26);
        // Assert
        ArrayList<Integer> correctSet = new ArrayList<>();
        correctSet.add(25);
        correctSet.add(26);
        correctSet.add(27);
        correctSet.add(26);
        correctSet.add(27);
        correctSet.add(28);
        assertEquals(correctSet, set);
        //assertEquals(true, player.isChi);
    }



    // 24, 25, 26, 27; card: 26
    // set: 24, 25, 26, 25, 26, 27
    @Test
    public void testIsChiWith3Neighbours2() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(24);
        cards.add(25);
        cards.add(27);
        player.playerMajiangs = cards;
        ArrayList<Integer> set= player.isChi(26);
        // Assert
        ArrayList<Integer> correctSet = new ArrayList<>();
        correctSet.add(24);
        correctSet.add(25);
        correctSet.add(26);
        correctSet.add(25);
        correctSet.add(26);
        correctSet.add(27);
        assertEquals(correctSet, set);
        //assertEquals(true, player.isChi);
    }



    // 25, 26, 27; card: 26
    // set: 25, 26, 27
    @Test
    public void testIsChiWith2NeighboursMiddle() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(25);
        cards.add(27);
        player.playerMajiangs = cards;
        ArrayList<Integer> set= player.isChi(26);
        // Assert
        ArrayList<Integer> correctSet = new ArrayList<>();
        correctSet.add(25);
        correctSet.add(26);
        correctSet.add(27);
        assertEquals(correctSet, set);
        //assertEquals(true, player.isChi);
    }



    // 24, 25, 26; card: 26
    // set: 24, 25, 26
    @Test
    public void testIsChiWith3LeftNeighbours() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(24);
        cards.add(25);
        player.playerMajiangs = cards;
        ArrayList<Integer> set= player.isChi(26);
        // Assert
        ArrayList<Integer> correctSet = new ArrayList<>();
        correctSet.add(24);
        correctSet.add(25);
        correctSet.add(26);
        assertEquals(correctSet, set);
        //assertEquals(true, player.isChi);
    }



    // 26, 27, 28; card: 26
    // set: 26, 27, 28
    @Test
    public void testIsChiWith2RightNeighbours() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(27);
        cards.add(28);
        player.playerMajiangs = cards;
        ArrayList<Integer> set= player.isChi(26);
        // Assert
        ArrayList<Integer> correctSet = new ArrayList<>();
        correctSet.add(26);
        correctSet.add(27);
        correctSet.add(28);
        assertEquals(correctSet, set);
        //assertEquals(true, player.isChi);
    }

}
