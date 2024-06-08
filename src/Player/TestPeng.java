package Player;

import GameTable.ShuffleMajiang;
import org.junit.Test;
import window.AddComputerTile;
import window.ImagePanel;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestPeng {
    HumanPlayer player = new HumanPlayer();

    int scaledWidth = 100;
    int scaledHeight = 100;
    JPanel gamePanel = new ImagePanel("src/window/background/background1.png");
    int computerName = 1;
    AddComputerTile addTile = new AddComputerTile();


    @Test
    public void testPeng1() {
        // Arrange
        ShuffleMajiang.river.add(25);
        player.playerRiver.add(25);
        player.playerMajiangs.add(25);
        player.playerMajiangs.add(25);
        // perform Peng
        player.Peng(25, scaledWidth, scaledHeight, gamePanel, computerName, addTile);
        // Assert correct answer
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(25);
        correctDisplay.add(25);
        correctDisplay.add(25);
        // test
        assertEquals(correctDisplay, player.getCardsToDisplay());
        assertEquals(new ArrayList<>(), player.playerMajiangs);
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
        player.Peng(47, scaledWidth, scaledHeight, gamePanel, computerName, addTile);
        // Assert correct answer
        ArrayList<Integer> correctDisplay = new ArrayList<>();
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
        assertEquals(2, player.PengNumber);
    }
}

