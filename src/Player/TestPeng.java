package Player;

import GameTable.ShuffleMajiang;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * TestPeng: test the Peng(int card) in the Player class
 *
 * @author: Qiyue Zhu
 */
public class TestPeng {
    HumanPlayer player = new HumanPlayer();


    @Test
    public void testPeng1() {
        // Arrange
        ShuffleMajiang.river.add(25);
        player.playerRiver.add(25);
        player.playerMajiangs.add(25);
        player.playerMajiangs.add(25);
        // perform Peng
        player.Peng(25);
        // Assert correct answer
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(25);
        correctDisplay.add(25);
        correctDisplay.add(25);
        // test
        assertEquals(correctDisplay, player.getCardsToDisplay());
        assertEquals(new ArrayList<>(), player.playerMajiangs);
        assertEquals(new ArrayList<>(), player.playerRiver);
        assertEquals(new ArrayList<>(), ShuffleMajiang.river);
        assertEquals(1, player.PengNumber);
    }


    @Test
    public void testPeng2() {
        // Arrange
        ShuffleMajiang.river.add(42);
        ShuffleMajiang.river.add(47);

        player.playerRiver.add(31);
        player.playerRiver.add(47);

        player.playerMajiangs.add(25);
        player.playerMajiangs.add(47);
        player.playerMajiangs.add(47);
        // perform Peng
        player.Peng(47);
        // Assert correct answer
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(47);
        correctDisplay.add(47);
        correctDisplay.add(47);
        ArrayList<Integer> correctCards = new ArrayList<>();
        correctCards.add(25);
        ArrayList<Integer> correctRiver = new ArrayList<>();
        correctRiver.add(42);
        ArrayList<Integer> correctPlayerRiver = new ArrayList<>();
        correctPlayerRiver.add(31);
        // test
        assertEquals(correctDisplay, player.getCardsToDisplay());
        assertEquals(correctCards, player.playerMajiangs);
        assertEquals(correctPlayerRiver, player.playerRiver);
        assertEquals(correctRiver, ShuffleMajiang.river);
        assertEquals(2, player.PengNumber);
    }
}

