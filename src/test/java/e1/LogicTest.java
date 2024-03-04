package e1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

    private static final int BOARD_SIZE = 5;
    private static final Pair<Integer, Integer> PAWN_POS = new Pair<Integer,Integer>(1, 2);
    private static final Pair<Integer, Integer> KNIGHT_POS = new Pair<Integer,Integer>(0, 0);
    private Logics logics;

    @BeforeEach
    void init() {
        logics = new LogicsImpl(BOARD_SIZE, PAWN_POS, KNIGHT_POS);
    }

    @Test
    void testPiecesCorrectlyPlaced() {
        assertAll(
            () -> assertTrue(logics.hasPawn(PAWN_POS.getX(), PAWN_POS.getY())),
            () -> assertTrue(logics.hasKnight(KNIGHT_POS.getX(), KNIGHT_POS.getY()))
        );
    }

    @Test
    void testKnightCanDoLegalMove() {
        Pair<Integer, Integer> newPosition = new Pair<Integer, Integer>(2, 1);
        logics.hit(newPosition.getX(), newPosition.getY());
        assertTrue(logics.hasKnight(newPosition.getX(), newPosition.getY()));
    }
    
    @Test
    void testKnightCantDoIllegalMoves() {
        Pair<Integer, Integer> newPosition = new Pair<Integer, Integer>(1, 0);
        logics.hit(newPosition.getX(), newPosition.getY());
        assertFalse(logics.hasKnight(newPosition.getX(), newPosition.getY()));
    }

    @Test
    void testKnightCantMoveOutOfBounds() {
        Pair<Integer, Integer> newInvalidPosition = new Pair<Integer, Integer>(-1, 2);
        assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(newInvalidPosition.getX(), newInvalidPosition.getY()));
    }

    @Test
    void testKnightCanCapturePawn() {        
        assertTrue(logics.hit(PAWN_POS.getX(), PAWN_POS.getY()));
    }
}