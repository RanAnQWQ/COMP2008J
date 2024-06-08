package Player;

import GameTable.ShuffleMajiang;
import org.junit.Test;
import window.AddComputerTile;
import window.ImagePanel;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestGang {
    HumanPlayer player = new HumanPlayer();

    int scaledWidth = 100;
    int scaledHeight = 100;
    JPanel gamePanel = new ImagePanel("src/window/background/background0.png");
    int computerName = 1;
    AddComputerTile addTile = new AddComputerTile();


    @Test
    public void testGang1() {
        // Arrange
        ShuffleMajiang.river.add(25);
        player.playerRiver.add(25);

        player.playerMajiangs.add(25);
        player.playerMajiangs.add(25);
        player.playerMajiangs.add(25);
        // perform Peng
        player.Gang(25, scaledWidth, scaledHeight, gamePanel, computerName, addTile);
        // Assert correct answer
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(25);
        correctDisplay.add(25);
        correctDisplay.add(25);
        correctDisplay.add(25);
        // test
        assertEquals(correctDisplay, player.getCardsToDisplay());
        assertEquals(new ArrayList<>(), player.playerMajiangs);
        assertEquals(new ArrayList<>(), ShuffleMajiang.river);
        assertEquals(1, player.GangNumber);
    }


    @Test
    public void testGang2() {
        // Arrange
        ShuffleMajiang.river.add(42);
        ShuffleMajiang.river.add(47);

        player.playerRiver.add(31);
        player.playerRiver.add(47);

        player.playerMajiangs.add(25);
        player.playerMajiangs.add(47);
        player.playerMajiangs.add(47);
        player.playerMajiangs.add(47);
        // perform Peng
        player.Gang(47, scaledWidth, scaledHeight, gamePanel, computerName, addTile);
        // Assert correct answer
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(47);
        correctDisplay.add(47);
        correctDisplay.add(47);
        correctDisplay.add(47);
        ArrayList<Integer> correctCards = new ArrayList<>();
        correctCards.add(25);
        ArrayList<Integer> correctRiver = new ArrayList<>();
        correctRiver.add(42);
        // test
        assertEquals(correctDisplay, player.getCardsToDisplay());
        assertEquals(correctCards, player.playerMajiangs);
        assertEquals(correctRiver, ShuffleMajiang.river);
        assertEquals(2, player.GangNumber);
    }
}
