package Player;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * TestIsPeng: test the isGang(int card) in the Player class
 *
 * @author: Qiyue Zhu
 */
public class TestIsGang {
    HumanPlayer player = new HumanPlayer();


    @Test
    public void testIsGang1() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(25);
        cards.add(25);
        cards.add(25);
        player.playerMajiangs = cards;
        // Assert
        assertEquals(true, player.isGang(25));
    }


    @Test
    public void testIsGang2() {
        // Arrange
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(46);
        cards.add(46);
        cards.add(46);
        player.playerMajiangs = cards;
        // Assert
        assertEquals(true, player.isGang(46));
    }
}
