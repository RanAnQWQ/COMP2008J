package Player;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestIsChi {
    Player player = new Player();


    @Test
    public void testIsChiWith4Neighbours() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(24);
        cards.add(25);
        cards.add(27);
        cards.add(28);
        player.playerMajiangs = cards;
        player.isChi(26);
        // Assert
        ArrayList<Integer> correctSet1 = new ArrayList<>();
        correctSet1.add(24);
        correctSet1.add(25);
        ArrayList<Integer> correctSet2 = new ArrayList<>();
        correctSet2.add(25);
        correctSet2.add(27);
        ArrayList<Integer> correctSet3 = new ArrayList<>();
        correctSet3.add(27);
        correctSet3.add(28);
        assertEquals(correctSet1, player.set1);
        assertEquals(correctSet2, player.set2);
        assertEquals(correctSet3, player.set3);
        assertEquals(true, player.isChi);
    }


    @Test
    public void testIsChiWith3Neighbours1() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(25);
        cards.add(27);
        cards.add(28);
        player.playerMajiangs = cards;
        player.isChi(26);
        // Assert
        ArrayList<Integer> correctSet1 = new ArrayList<>();
        ArrayList<Integer> correctSet2 = new ArrayList<>();
        correctSet2.add(25);
        correctSet2.add(27);
        ArrayList<Integer> correctSet3 = new ArrayList<>();
        correctSet3.add(27);
        correctSet3.add(28);
        assertEquals(correctSet1, player.set1);
        assertEquals(correctSet2, player.set2);
        assertEquals(correctSet3, player.set3);
        assertEquals(true, player.isChi);
    }


    @Test
    public void testIsChiWith3Neighbours2() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(24);
        cards.add(25);
        cards.add(27);
        player.playerMajiangs = cards;
        player.isChi(26);
        // Assert
        ArrayList<Integer> correctSet1 = new ArrayList<>();
        correctSet1.add(24);
        correctSet1.add(25);
        ArrayList<Integer> correctSet2 = new ArrayList<>();
        correctSet2.add(25);
        correctSet2.add(27);
        ArrayList<Integer> correctSet3 = new ArrayList<>();
        assertEquals(correctSet1, player.set1);
        assertEquals(correctSet2, player.set2);
        assertEquals(correctSet3, player.set3);
        assertEquals(true, player.isChi);
    }


    @Test
    public void testIsChiWith2NeighboursMiddle() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(25);
        cards.add(27);
        player.playerMajiangs = cards;
        player.isChi(26);
        // Assert
        ArrayList<Integer> correctSet1 = new ArrayList<>();
        ArrayList<Integer> correctSet2 = new ArrayList<>();
        correctSet2.add(25);
        correctSet2.add(27);
        ArrayList<Integer> correctSet3 = new ArrayList<>();
        assertEquals(correctSet1, player.set1);
        assertEquals(correctSet2, player.set2);
        assertEquals(correctSet3, player.set3);
        assertEquals(true, player.isChi);
    }



    @Test
    public void testIsChiWith3LeftNeighbours() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(24);
        cards.add(25);
        player.playerMajiangs = cards;
        player.isChi(26);
        // Assert
        ArrayList<Integer> correctSet1 = new ArrayList<>();
        correctSet1.add(24);
        correctSet1.add(25);
        ArrayList<Integer> correctSet2 = new ArrayList<>();
        ArrayList<Integer> correctSet3 = new ArrayList<>();
        assertEquals(correctSet1, player.set1);
        assertEquals(correctSet2, player.set2);
        assertEquals(correctSet3, player.set3);
        assertEquals(true, player.isChi);
    }



    @Test
    public void testIsChiWith2RightNeighbours() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(27);
        cards.add(28);
        player.playerMajiangs = cards;
        player.isChi(26);
        // Assert
        ArrayList<Integer> correctSet1 = new ArrayList<>();
        ArrayList<Integer> correctSet2 = new ArrayList<>();
        ArrayList<Integer> correctSet3 = new ArrayList<>();
        correctSet3.add(27);
        correctSet3.add(28);
        assertEquals(correctSet1, player.set1);
        assertEquals(correctSet2, player.set2);
        assertEquals(correctSet3, player.set3);
        assertEquals(true, player.isChi);
    }
}
