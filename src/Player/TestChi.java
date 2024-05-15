package Player;

import static org.junit.Assert.*;

import GameTable.ShuffleMajiang;
import org.junit.Test;
import java.util.ArrayList;

public class TestChi {
    Player player = new Player();

    public void initialize(int card1, int card2, int card){
        ShuffleMajiang.river.add(card);
        player.playerRiver.add(card);
        player.playerMajiangs.add(card1);
        player.playerMajiangs.add(card2);
    }


    //listener=1 : left(set1)
    @Test
    public void testChiWithSet1() {
        // Arrange
        initialize(24, 25, 26);
        player.set1.add(24);
        player.set1.add(25);
        player.Chi(player.set1, 26);
        // Assert
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(24);
        correctDisplay.add(25);
        correctDisplay.add(26);
        assertEquals(correctDisplay, player.getCardsToDisplay());
        assertEquals(new ArrayList<>(), player.playerMajiangs);
        assertEquals(new ArrayList<>(), player.playerRiver);
        assertEquals(new ArrayList<>(), ShuffleMajiang.river);
        assertEquals(1, player.ChiNumber);
    }


    //listener=2 : middle(set2)
    @Test
    public void testChiWithSet2() {
        // Arrange
        initialize(25, 27, 26);
        player.set2.add(25);
        player.set2.add(27);
        player.Chi(player.set2, 26);
        // Assert
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(25);
        correctDisplay.add(26);
        correctDisplay.add(27);
        assertEquals(correctDisplay, player.getCardsToDisplay());
        assertEquals(new ArrayList<>(), player.playerMajiangs);
        assertEquals(new ArrayList<>(), player.playerRiver);
        assertEquals(new ArrayList<>(), ShuffleMajiang.river);
        assertEquals(2, player.ChiNumber);
    }


    //listener=3 : right(set3)
    @Test
    public void testChiWithSet3() {
        // Arrange
        initialize(27, 28, 26);
        player.set3.add(27);
        player.set3.add(28);
        player.Chi(player.set3, 26);
        // Assert
        ArrayList<Integer> correctDisplay = new ArrayList<>();
        correctDisplay.add(26);
        correctDisplay.add(27);
        correctDisplay.add(28);
        assertEquals(correctDisplay, player.getCardsToDisplay());
        assertEquals(new ArrayList<>(), player.playerMajiangs);
        assertEquals(new ArrayList<>(), player.playerRiver);
        assertEquals(new ArrayList<>(), ShuffleMajiang.river);
        assertEquals(3, player.ChiNumber);
    }

}
