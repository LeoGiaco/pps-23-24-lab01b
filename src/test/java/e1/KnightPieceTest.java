package e1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KnightPieceTest {

    private Piece knightPiece;
    private static final Pair<Integer, Integer> START_POSITION = new Pair<Integer, Integer>(2, 2);
    private static final List<Pair<Integer, Integer>> RELATIVE_POSITIONS = List.of(
        new Pair<>(1, 2),
        new Pair<>(1, -2),
        new Pair<>(2, 1),
        new Pair<>(2, -1),
        new Pair<>(-1, 2),
        new Pair<>(-1, -2),
        new Pair<>(-2, 1),
        new Pair<>(-2, -1)
    );

    @BeforeEach
    void init() {
        knightPiece = new KnightPiece(START_POSITION);
    }

    @Test
    void testKnightCanDoLegalMoves() {
        assertTrue(RELATIVE_POSITIONS.stream()
            .map(relPosition -> {
                return this.knightPiece.canMove(START_POSITION.getX() + relPosition.getX(), START_POSITION.getY() + relPosition.getY());
            })
            .allMatch(canMove -> canMove));
    }

    @Test
    void testKnightActuallyDoesLegalMoves() {
        assertTrue(RELATIVE_POSITIONS.stream()
        .map(position -> {
            init();
            int x = START_POSITION.getX() + position.getX();
            int y = START_POSITION.getY() + position.getY();
            this.knightPiece.move(x, y);
            return this.knightPiece.getX() == x && this.knightPiece.getY() == y;
        })
        .allMatch(canMove -> canMove));
    }

    @Test
    void testKnightCantDoIllegalMoves() {
        var newBadPosition = new Pair<>(0, 0);
        assertFalse(this.knightPiece.canMove(newBadPosition.getX(), newBadPosition.getY()));
    }

    @Test
    void testKnightCantActuallyDoIllegalMoves() {
        var newBadPosition = new Pair<>(0, 0);
        this.knightPiece.move(newBadPosition.getX(), newBadPosition.getY());
        assertFalse(this.knightPiece.getX() == newBadPosition.getX() && this.knightPiece.getY() == newBadPosition.getY());
    }
}
