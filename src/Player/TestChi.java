package Player;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class TestChi {

    @Test
    public void testChiWithBothNeighboursCorrect() {
        // Arrange
        ArrayList<Integer> playerMajiangs = new ArrayList<>();
        playerMajiangs.add(5);
        playerMajiangs.add(6);
        playerMajiangs.add(7);
        // Act
        Movement chi = new Movement(6);
        // Assert
        assertEquals(1, chi.ChiNumber);
    }


    @Test
    public void testChiWithBothNeighboursWrong() {
        // Arrange
        ArrayList<Integer> playerMajiangs = new ArrayList<>();
        playerMajiangs.add(5);
        playerMajiangs.add(6);
        playerMajiangs.add(9);
        // Act
        Movement chi = new Movement(6);
        // Assert
        assertEquals(0, chi.ChiNumber);
    }


    @Test
    public void testChiWithRightNeighbourCorrect() {
        // Arrange
        ArrayList<Integer> playerMajiangs = new ArrayList<>();
        playerMajiangs.add(5);
        playerMajiangs.add(8);
        playerMajiangs.add(9);
        // Act
        Movement chiR = new Movement(7);
        // Assert
        assertEquals(1, chiR.ChiNumber);
    }

    @Test
    public void testChiWithRightNeighbourWrong() {
        // Arrange
        ArrayList<Integer> playerMajiangs = new ArrayList<>();
        playerMajiangs.add(5);
        playerMajiangs.add(7);
        playerMajiangs.add(8);
        // Act
        Movement chiR = new Movement(7);
        // Assert
        assertEquals(0, chiR.ChiNumber);
    }


    @Test
    public void testChiWithLeftNeighbourCorrect() {
        // Arrange
        ArrayList<Integer> playerMajiangs = new ArrayList<>();
        playerMajiangs.add(5);
        playerMajiangs.add(6);
        playerMajiangs.add(9);
        // Act
        Movement chiL = new Movement(7);
        // Assert
        assertEquals(1, chiL.ChiNumber);
    }


    @Test
    public void testChiWithLeftNeighbourWrong() {
        // Arrange
        ArrayList<Integer> playerMajiangs = new ArrayList<>();
        playerMajiangs.add(6);
        playerMajiangs.add(7);
        playerMajiangs.add(9);
        // Act
        Movement chiL = new Movement(7);
        // Assert
        assertEquals(0, chiL.ChiNumber);
    }
}
