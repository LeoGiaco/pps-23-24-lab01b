package e2;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MinefieldTest {
    
    private static final int FIELD_SIZE = 4;
    private static final int NUMBER_OF_MINES = 5;
    private static final int MAX_NUMBER_OF_ADJACENT_MINES = 8;
    private Minefield field;

    @BeforeEach
    void init() {
        field = new MinefieldImpl(FIELD_SIZE, NUMBER_OF_MINES);
    }

    @Test
    void testParametersMustMakeSense() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new MinefieldImpl(-1, 0)),
            () -> assertThrows(IllegalArgumentException.class, () -> new MinefieldImpl(0, 0)),
            () -> assertThrows(IllegalArgumentException.class, () -> new MinefieldImpl(FIELD_SIZE, -1)),
            () -> assertThrows(IllegalArgumentException.class, () -> new MinefieldImpl(FIELD_SIZE, 0)),
            () -> assertThrows(IllegalArgumentException.class, () -> new MinefieldImpl(FIELD_SIZE, FIELD_SIZE * FIELD_SIZE))
        );
    }

    @Test
    void testAllCellsAreInitiallyNotDiscovered() {
        assertTrue(Stream.iterate(0, i -> i + 1)
                            .limit(FIELD_SIZE * FIELD_SIZE)
                            .map(num -> new Pair<>(num / FIELD_SIZE, num % FIELD_SIZE))
                            .map(field::isDiscovered)
                            .allMatch(isDiscovered -> !isDiscovered));
    }

    @Test
    void testAllCellsAreInitiallyNotFlagged() {
        assertTrue(Stream.iterate(0, i -> i + 1)
                            .limit(FIELD_SIZE * FIELD_SIZE)
                            .map(num -> new Pair<>(num / FIELD_SIZE, num % FIELD_SIZE))
                            .map(field::isFlagged)
                            .allMatch(isFlagged -> !isFlagged));
    }

    @Test
    void testPeekAndDiscoveryAreConsistent() {
        var position = new Pair<>(1, 1);
        assertEquals(field.peek(position), field.discover(position));
    }

    @Test
    void testFirstDiscoveredCellIsNeverAMine() {
        var position = new Pair<>(1, 1);
        assertFalse(field.discover(position));
    }
    
    @Test
    void testCellIsFlaggedCorrectly() {
        var position = new Pair<>(1, 1);
        assertTrue(field.toggleFlag(position));
    }
    
    @Test
    void testCellIsUnflaggedCorrectly() {
        var position = new Pair<>(1, 1);
        field.toggleFlag(position);
        assertFalse(field.toggleFlag(position));
    }

    void createFieldWithOnlyOneFreeCell(int fieldSize) {
        int numberOfMines = fieldSize * fieldSize - 1;
        field = new MinefieldImpl(fieldSize, numberOfMines);
    }
    
    @Test
    void testCheckNumberOfAdjacentMinesIsCorrect() {
        var discoveredPosition = new Pair<>(1, 1);
        createFieldWithOnlyOneFreeCell(FIELD_SIZE);
        field.discover(discoveredPosition);
        assertEquals(MAX_NUMBER_OF_ADJACENT_MINES, field.getNumberOfAdjacentMines(discoveredPosition.getX(), discoveredPosition.getY()));
    }

    @Test
    void testCheckFieldIsInitiallyNotCleared() {
        assertFalse(field.isCleared());
    }

    @Test
    void testCheckVictory() {
        var discoveredPosition = new Pair<>(1, 1);
        createFieldWithOnlyOneFreeCell(FIELD_SIZE);
        field.discover(discoveredPosition);
        assertTrue(field.isCleared());
    }

    @Test
    void testCheckDefeat() {
        var discoveredPosition = new Pair<>(1, 1);
        var discoveredPosition2 = new Pair<>(1, 2);
        createFieldWithOnlyOneFreeCell(FIELD_SIZE);
        field.discover(discoveredPosition);
        field.discover(discoveredPosition2);
        assertFalse(field.isCleared());
    }
}
