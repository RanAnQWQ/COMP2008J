package Player;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestIsPeng {
    Player player = new Player();


    @Test
    public void testIsPeng1() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(25);
        cards.add(25);
        player.playerMajiangs = cards;
        // Assert
        assertEquals(true, player.isPeng(25));
    }


    @Test
    public void testIsPeng2() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(46);
        cards.add(46);
        player.playerMajiangs = cards;
        // Assert
        assertEquals(true, player.isPeng(46));
    }
}