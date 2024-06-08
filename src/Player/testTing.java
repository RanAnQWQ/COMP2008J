package Player;
import TingHelper.TingListener;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple test to check if the TingListener class runs correctly
 * This class contains a JUnit test case to verify the functionality of the TingListener and Player classes.
 * It checks whether players are in a "Ting" state (one tile away from winning) and prints out their Ting tiles.
 * Author: Ran An
 */
public class testTing {
    @Test
    public void ting() {
        // Initialize four human players
        HumanPlayer player1 = new HumanPlayer();
        HumanPlayer player2 = new HumanPlayer();
        HumanPlayer player3 = new HumanPlayer();
        HumanPlayer player4 = new HumanPlayer();

        // Initialize the TingListener
        TingListener Listener = new TingListener();

        // Set up player1's tiles
        player1.playerMajiangs = new ArrayList<>(Arrays.asList(11, 11, 13, 12, 12));
        player1.cardsToDisplay = new ArrayList<>(Arrays.asList(21, 21, 21, 23, 24, 25, 31, 32, 33));

        // Set up player2's tiles
        player2.playerMajiangs = new ArrayList<>(Arrays.asList(11, 12, 13, 14, 16));
        player2.cardsToDisplay = new ArrayList<>(Arrays.asList(15, 15, 15, 21, 21, 21, 21, 31, 32, 33));

        // Set up player3's tiles
        player3.playerMajiangs = new ArrayList<>(Arrays.asList(11, 12, 13, 32, 33, 36, 14, 14));
        player3.cardsToDisplay = new ArrayList<>(Arrays.asList(15, 15, 15, 21, 21, 21, 21));

        // Set up player4's tiles
        player4.playerMajiangs = new ArrayList<>(Arrays.asList(14, 14, 22, 21, 26, 37, 38, 39, 43, 44));
        player4.cardsToDisplay = new ArrayList<>(Arrays.asList(23, 24, 25));

        // Assert and print Ting tiles for player1
        Assert.assertTrue(player1.isTing());
        for (Integer tingTile : player1.TingTiles) {
            System.out.println(tingTile);
        }

        // Assert and print Ting tiles for player2
        Assert.assertTrue(player2.isTing());
        for (Integer tingTile : player2.TingTiles) {
            System.out.println(tingTile);
        }

        // Assert and print Ting tiles for player3
        Assert.assertTrue(player3.isTing());
        for (Integer tingTile : player3.TingTiles) {
            System.out.println(tingTile);
        }

        // Assert and print Ting tiles for player4 (expected to not be in Ting state)
        Assert.assertFalse(player4.isTing());
        for (Integer tingTile : player4.TingTiles) {
            System.out.println(tingTile);
        }

    }
}
