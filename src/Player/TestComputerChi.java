package Player;

import GameTable.ShuffleMajiang;
import org.junit.Test;
import window.AddComputerTile;
import window.AddTile;
import window.GameContent;
import window.ImagePanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class TestComputerChi {

    // Chi(Integer card, int scaledWidth,
    // int scaledHeight, JPanel gamePanel,
    // int computerName, AddTile addTile)

    Computer computer = new Computer();
    ShuffleMajiang s;
    int scaledWidth = 100;
    int scaledHeight = 100;
    JPanel gamePanel = new ImagePanel("src/window/background/background0.png");
    int computerName = 1;
    AddComputerTile addTile = new AddComputerTile();


    {
        try {
            s = new ShuffleMajiang();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    // ( 25, 26, (27) ), 29; card = 27
    @Test
    public void testChiWithLeftNeighbourCorrect() {
        // Arrange
        ArrayList<Integer> playerMajiangs = new ArrayList<>();
        playerMajiangs.add(25);
        playerMajiangs.add(26);
        playerMajiangs.add(29);
        computer.playerMajiangs = playerMajiangs;

        ShuffleMajiang.river.add(27);
        computer.Chi(27, scaledWidth, scaledHeight, gamePanel, computerName, addTile);
        // Assert
        assertEquals(1, computer.ChiNumber);

        ArrayList<Integer> correctPlayerMajiangs = new ArrayList<>();
        correctPlayerMajiangs.add(29);
        assertEquals(correctPlayerMajiangs, computer.playerMajiangs);
        Collections.sort(computer.cardsToDisplay);

        ArrayList<Integer> correctCardsToDisplay = new ArrayList<>();
        correctCardsToDisplay.add(25);
        correctCardsToDisplay.add(26);
        correctCardsToDisplay.add(27);
        assertEquals(correctCardsToDisplay, computer.cardsToDisplay);

        ArrayList<Integer> correctRiver = new ArrayList<>();
        assertEquals(correctRiver, ShuffleMajiang.river);
    }

    // 23, ( 25, (26), 27 ), 29, 31; card = 26
    @Test
    public void testChiWithBothNeighboursCorrect() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(23);
        cards.add(25);
        cards.add(27);
        cards.add(29);
        cards.add(31);
        computer.playerMajiangs = cards;

        ShuffleMajiang.river.add(26);
        computer.Chi(26, scaledWidth, scaledHeight, gamePanel, computerName, addTile);
        Collections.sort(computer.cardsToDisplay);
        // Assert
        assertEquals(2, computer.ChiNumber);

        ArrayList<Integer> correctPlayerMajiangs = new ArrayList<>();
        correctPlayerMajiangs.add(23);
        correctPlayerMajiangs.add(29);
        correctPlayerMajiangs.add(31);
        assertEquals(correctPlayerMajiangs, computer.playerMajiangs);

        ArrayList<Integer> correctCardsToDisplay = new ArrayList<>();
        correctCardsToDisplay.add(25);
        correctCardsToDisplay.add(26);
        correctCardsToDisplay.add(27);
        assertEquals(correctCardsToDisplay, computer.cardsToDisplay);

        ArrayList<Integer> correctRiver = new ArrayList<>();
        assertEquals(correctRiver, ShuffleMajiang.river);
    }


    // 25, ( (27), 28, 29 ); card = 27
    @Test
    public void testChiWithRightNeighbourCorrect() {
        // Arrange
        ArrayList<Integer> playerMajiangs = new ArrayList<>();
        playerMajiangs.add(25);
        playerMajiangs.add(28);
        playerMajiangs.add(29);
        computer.playerMajiangs = playerMajiangs;

        ShuffleMajiang.river.add(27);
        computer.Chi(27, scaledWidth, scaledHeight, gamePanel, computerName, addTile);
        Collections.sort(computer.cardsToDisplay);
        // Assert
        assertEquals(3, computer.ChiNumber);

        ArrayList<Integer> correctPlayerMajiangs = new ArrayList<>();
        correctPlayerMajiangs.add(25);
        assertEquals(correctPlayerMajiangs, computer.playerMajiangs);

        ArrayList<Integer> correctCardsToDisplay = new ArrayList<>();
        correctCardsToDisplay.add(27);
        correctCardsToDisplay.add(28);
        correctCardsToDisplay.add(29);
        assertEquals(correctCardsToDisplay, computer.cardsToDisplay);

        ArrayList<Integer> correctRiver = new ArrayList<>();
        assertEquals(correctRiver, ShuffleMajiang.river);
    }

}
