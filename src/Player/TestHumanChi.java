package Player;

import static org.junit.Assert.*;

import GameTable.ShuffleMajiang;
import org.junit.Test;
import java.util.ArrayList;

/**
 * TestHumanChi: test the Chi(ArrayList<Integer> set, int listener, int card) in the HumanPlayer class
 *
 * @author: Qiyue Zhu
 */
public class TestHumanChi {
    HumanPlayer player = new HumanPlayer();

    // set length=3, listener=3 (card on the left), card: 24
    // 24, 25, 26;
    @Test
    public void testChi30() {
        // Arrange
        ShuffleMajiang.river.add(24);
        player.playerMajiangs.add(25);
        player.playerMajiangs.add(26);
        player.set.add(24);
        player.set.add(25);
        player.set.add(26);
        player.Chi(player.set, 3, 24);
        // Assert
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(24);
        correctDisplay.add(25);
        correctDisplay.add(26);
        assertEquals(correctDisplay, player.cardsToDisplay);
        assertEquals(new ArrayList<>(), player.playerMajiangs);
        assertEquals(new ArrayList<>(), ShuffleMajiang.river);
        assertEquals(1, player.ChiNumber);
    }


    // set length=3, listener=3 (card in the middle), card: 25
    // 24, 25, 26
    @Test
    public void testChi31() {
        // Arrange
        ShuffleMajiang.river.add(25);
        player.playerMajiangs.add(24);
        player.playerMajiangs.add(26);
        player.set.add(24);
        player.set.add(25);
        player.set.add(26);
        player.Chi(player.set, 3, 25);
        // Assert
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(24);
        correctDisplay.add(25);
        correctDisplay.add(26);
        assertEquals(correctDisplay, player.cardsToDisplay);
        assertEquals(new ArrayList<>(), player.playerMajiangs);
        assertEquals(new ArrayList<>(), ShuffleMajiang.river);
        assertEquals(2, player.ChiNumber);
    }



    // set length=3, listener=3 (card on the right), card: 26
    // 24, 25, 26
    @Test
    public void testChi32() {
        // Arrange
        ShuffleMajiang.river.add(26);
        player.playerMajiangs.add(24);
        player.playerMajiangs.add(25);
        player.set.add(24);
        player.set.add(25);
        player.set.add(26);
        player.Chi(player.set, 3, 26);
        // Assert
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(24);
        correctDisplay.add(25);
        correctDisplay.add(26);
        assertEquals(correctDisplay, player.cardsToDisplay);
        assertEquals(new ArrayList<>(), player.playerMajiangs);
        assertEquals(new ArrayList<>(), ShuffleMajiang.river);
        assertEquals(3, player.ChiNumber);
    }


    // set length=9, listener=0 (card on the left), card: 25
    // playerMajiangs: 23, 24, (25, 26, 27)
    // set: 23, 24, 25, 24, 25, 26, (25, 26, 27)
    @Test
    public void testChi90() {
        // Arrange
        ShuffleMajiang.river.add(25);
        player.playerMajiangs.add(23);
        player.playerMajiangs.add(24);
        player.playerMajiangs.add(26);
        player.playerMajiangs.add(27);
        player.set.add(23);
        player.set.add(24);
        player.set.add(25);
        player.set.add(24);
        player.set.add(25);
        player.set.add(26);
        player.set.add(25);
        player.set.add(26);
        player.set.add(27);
        player.Chi(player.set, 0, 25);
        // Assert
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(25);
        correctDisplay.add(26);
        correctDisplay.add(27);
        ArrayList<Integer> correctCards = new ArrayList<>();
        correctCards.add(23);
        correctCards.add(24);
        assertEquals(correctDisplay, player.cardsToDisplay);
        assertEquals(correctCards, player.playerMajiangs);
        assertEquals(new ArrayList<>(), ShuffleMajiang.river);
        assertEquals(4, player.ChiNumber);
    }


    // set length=9, listener=1 (card in the middle), card: 25
    // playerMajiangs: 23, (24, 25, 26), 27
    // set: 23, 24, 25, (24, 25, 26), 25, 26, 27
    @Test
    public void testChi91() {
        // Arrange
        ShuffleMajiang.river.add(25);
        player.playerMajiangs.add(23);
        player.playerMajiangs.add(24);
        player.playerMajiangs.add(26);
        player.playerMajiangs.add(27);
        player.set.add(23);
        player.set.add(24);
        player.set.add(25);
        player.set.add(24);
        player.set.add(25);
        player.set.add(26);
        player.set.add(25);
        player.set.add(26);
        player.set.add(27);
        player.Chi(player.set, 1, 25);
        // Assert
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(24);
        correctDisplay.add(25);
        correctDisplay.add(26);
        ArrayList<Integer> correctCards = new ArrayList<>();
        correctCards.add(23);
        correctCards.add(27);
        assertEquals(correctDisplay, player.cardsToDisplay);
        assertEquals(correctCards, player.playerMajiangs);
        assertEquals(new ArrayList<>(), ShuffleMajiang.river);
        assertEquals(5, player.ChiNumber);
    }



    // set length=9, listener=2 (card on the right), card: 25
    // playerMajiangs: (23, 24, 25), 26, 27
    // set: (23, 24, 25), 24, 25, 26, 25, 26, 27
    @Test
    public void testChi92() {
        // Arrange
        ShuffleMajiang.river.add(25);
        player.playerMajiangs.add(23);
        player.playerMajiangs.add(24);
        player.playerMajiangs.add(26);
        player.playerMajiangs.add(27);
        player.set.add(23);
        player.set.add(24);
        player.set.add(25);
        player.set.add(24);
        player.set.add(25);
        player.set.add(26);
        player.set.add(25);
        player.set.add(26);
        player.set.add(27);
        player.Chi(player.set, 2, 25);
        // Assert
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(23);
        correctDisplay.add(24);
        correctDisplay.add(25);
        ArrayList<Integer> correctCards = new ArrayList<>();
        correctCards.add(26);
        correctCards.add(27);
        assertEquals(correctDisplay, player.cardsToDisplay);
        assertEquals(correctCards, player.playerMajiangs);
        assertEquals(new ArrayList<>(), ShuffleMajiang.river);
        assertEquals(6, player.ChiNumber);
    }



    // set length=6, listener=1 (card on the right), card: 25
    // playerMajiangs: (23, 24, 25), 26
    // set: (23, 24, 25), 24, 25, 26
    @Test
    public void testChi61A() {
        // Arrange
        ShuffleMajiang.river.add(25);
        player.playerMajiangs.add(23);
        player.playerMajiangs.add(24);
        player.playerMajiangs.add(26);
        player.set.add(23);
        player.set.add(24);
        player.set.add(25);
        player.set.add(24);
        player.set.add(25);
        player.set.add(26);
        player.Chi(player.set, 1, 25);
        // Assert
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(23);
        correctDisplay.add(24);
        correctDisplay.add(25);
        ArrayList<Integer> correctCards = new ArrayList<>();
        correctCards.add(26);
        assertEquals(correctDisplay, player.cardsToDisplay);
        assertEquals(correctCards, player.playerMajiangs);
        assertEquals(new ArrayList<>(), ShuffleMajiang.river);
        assertEquals(7, player.ChiNumber);
    }


    // set length=6, listener=1 (card in the middle), card: 24
    // playerMajiangs: (23, 24, 25), 26
    // set: (23, 24, 25), 24, 25, 26
    @Test
    public void testChi61B() {
        // Arrange
        ShuffleMajiang.river.add(24);
        player.playerMajiangs.add(23);
        player.playerMajiangs.add(25);
        player.playerMajiangs.add(26);
        player.set.add(23);
        player.set.add(24);
        player.set.add(25);
        player.set.add(24);
        player.set.add(25);
        player.set.add(26);
        player.Chi(player.set, 1, 24);
        // Assert
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(23);
        correctDisplay.add(24);
        correctDisplay.add(25);
        ArrayList<Integer> correctCards = new ArrayList<>();
        correctCards.add(26);
        assertEquals(correctDisplay, player.cardsToDisplay);
        assertEquals(correctCards, player.playerMajiangs);
        assertEquals(new ArrayList<>(), ShuffleMajiang.river);
        assertEquals(8, player.ChiNumber);
    }


    // set length=6, listener=2 (card in the middle), card: 25
    // playerMajiangs: 23, (24, 25, 26)
    // set: 23, 24, 25, (24, 25, 26)
    @Test
    public void testChi62A() {
        // Arrange
        ShuffleMajiang.river.add(25);
        player.playerMajiangs.add(23);
        player.playerMajiangs.add(24);
        player.playerMajiangs.add(26);
        player.set.add(23);
        player.set.add(24);
        player.set.add(25);
        player.set.add(24);
        player.set.add(25);
        player.set.add(26);
        player.Chi(player.set, 2, 25);
        // Assert
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(24);
        correctDisplay.add(25);
        correctDisplay.add(26);
        ArrayList<Integer> correctCards = new ArrayList<>();
        correctCards.add(23);
        assertEquals(correctDisplay, player.cardsToDisplay);
        assertEquals(correctCards, player.playerMajiangs);
        assertEquals(new ArrayList<>(), ShuffleMajiang.river);
        assertEquals(9, player.ChiNumber);
    }


    // set length=6, listener=2 (card on the left), card: 24
    // playerMajiangs: 23, (24, 25, 26)
    // set: 23, 24, 25, (24, 25, 26)
    @Test
    public void testChi62B() {
        // Arrange
        ShuffleMajiang.river.add(24);
        player.playerMajiangs.add(23);
        player.playerMajiangs.add(25);
        player.playerMajiangs.add(26);
        player.set.add(23);
        player.set.add(24);
        player.set.add(25);
        player.set.add(24);
        player.set.add(25);
        player.set.add(26);
        player.Chi(player.set, 2, 24);
        // Assert
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(24);
        correctDisplay.add(25);
        correctDisplay.add(26);
        ArrayList<Integer> correctCards = new ArrayList<>();
        correctCards.add(23);
        assertEquals(correctDisplay, player.cardsToDisplay);
        assertEquals(correctCards, player.playerMajiangs);
        assertEquals(new ArrayList<>(), ShuffleMajiang.river);
        assertEquals(10, player.ChiNumber);
    }

}
