package Player;

import GameTable.ShuffleMajiang;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * TestComputerChi: test the Chi(int card) in the Computer class
 *
 * @author: Qiyue Zhu
 */
public class TestComputerChi {
    Computer computer = new Computer();
    ShuffleMajiang s;

    {
        try {
            s = new ShuffleMajiang();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    // ( 5, 6, (7) ), 9; card = 7
    @Test
    public void testChiWithLeftNeighbourCorrect() {
        // Arrange
        ArrayList<Integer> playerMajiangs = new ArrayList<>();
        playerMajiangs.add(5);
        playerMajiangs.add(6);
        playerMajiangs.add(9);
        computer.playerMajiangs = playerMajiangs;

        ShuffleMajiang.river.add(7);
        computer.Chi(7);
        // Assert
        assertEquals(1, computer.ChiNumber);

        ArrayList<Integer> correctPlayerMajiangs = new ArrayList<>();
        correctPlayerMajiangs.add(9);
        assertEquals(correctPlayerMajiangs, computer.playerMajiangs);

        ArrayList<Integer> correctCardsToDisplay = new ArrayList<>();
        correctCardsToDisplay.add(5);
        correctCardsToDisplay.add(6);
        correctCardsToDisplay.add(7);
        assertEquals(correctCardsToDisplay, computer.cardsToDisplay);

        ArrayList<Integer> correctRiver = new ArrayList<>();
        assertEquals(correctRiver, ShuffleMajiang.river);
    }

    // 3, ( 5, (6), 7 ), 9, 11; card = 6
    @Test
    public void testChiWithBothNeighboursCorrect() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(3);
        cards.add(5);
        cards.add(7);
        cards.add(9);
        cards.add(11);
        computer.playerMajiangs = cards;

        ShuffleMajiang.river.add(6);
        computer.Chi(6);
        // Assert
        assertEquals(2, computer.ChiNumber);

        ArrayList<Integer> correctPlayerMajiangs = new ArrayList<>();
        correctPlayerMajiangs.add(3);
        correctPlayerMajiangs.add(9);
        correctPlayerMajiangs.add(11);
        assertEquals(correctPlayerMajiangs, computer.playerMajiangs);

        ArrayList<Integer> correctCardsToDisplay = new ArrayList<>();
        correctCardsToDisplay.add(5);
        correctCardsToDisplay.add(6);
        correctCardsToDisplay.add(7);
        assertEquals(correctCardsToDisplay, computer.cardsToDisplay);

        ArrayList<Integer> correctRiver = new ArrayList<>();
        assertEquals(correctRiver, ShuffleMajiang.river);
    }


    // 5, ( (7), 8, 9 ); card = 7
    @Test
    public void testChiWithRightNeighbourCorrect() {
        // Arrange
        ArrayList<Integer> playerMajiangs = new ArrayList<>();
        playerMajiangs.add(5);
        playerMajiangs.add(8);
        playerMajiangs.add(9);
        computer.playerMajiangs = playerMajiangs;

        ShuffleMajiang.river.add(7);
        computer.Chi(7);
        // Assert
        assertEquals(3, computer.ChiNumber);

        ArrayList<Integer> correctPlayerMajiangs = new ArrayList<>();
        correctPlayerMajiangs.add(5);
        assertEquals(correctPlayerMajiangs, computer.playerMajiangs);

        ArrayList<Integer> correctCardsToDisplay = new ArrayList<>();
        correctCardsToDisplay.add(7);
        correctCardsToDisplay.add(8);
        correctCardsToDisplay.add(9);
        assertEquals(correctCardsToDisplay, computer.cardsToDisplay);

        ArrayList<Integer> correctRiver = new ArrayList<>();
        assertEquals(correctRiver, ShuffleMajiang.river);
    }

}
