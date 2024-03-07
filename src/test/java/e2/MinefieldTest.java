package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MinefieldTest {
    
    private static final int FIELD_SIZE = 4;
    private static final int NUMBER_OF_MINES = 5;
    private Minefield field;

    @BeforeEach
    void init() {
        field = new MinefieldImpl(FIELD_SIZE, NUMBER_OF_MINES);
    }

    @Test
    void testFirstDiscoveredCellIsNeverAMine() {
        var position = new Pair<>(1, 1);
        assertFalse(field.discover(position.getX(), position.getY()));
    }
    
    @Test
    void testCellIsFlaggedCorrectly() {
        var position = new Pair<>(1, 1);
        assertTrue(field.toggleFlag(position.getX(), position.getY()));
    }
    
    @Test
    void testCellIsUnflaggedCorrectly() {
        var position = new Pair<>(1, 1);
        field.toggleFlag(position.getX(), position.getY());
        assertFalse(field.toggleFlag(position.getX(), position.getY()));
    }

    @Test
    void testCheckNumberOfAdjacentMinesIsCorrect() {
        int fieldSize = 3;
        int numberOfMines = 8;
        var discoveredPosition = new Pair<>(1, 1);
        field = new MinefieldImpl(fieldSize, numberOfMines);
        field.discover(discoveredPosition.getX(), discoveredPosition.getY());
        assertEquals(numberOfMines, field.getNumberOfAdjacentMines(discoveredPosition.getX(), discoveredPosition.getY()));
    }
}
