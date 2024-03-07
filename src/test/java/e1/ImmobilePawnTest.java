package e1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ImmobilePawnTest {
    private Piece pawnPiece;
    private static final Pair<Integer, Integer> START_POSITION = new Pair<Integer, Integer>(2, 2);
    private static final int BOARD_SIZE = 5;

    @BeforeEach
    void init() {
        pawnPiece = new ImmobilePawnPiece(START_POSITION);
    }

    Stream<Pair<Integer, Integer>> getStreamOfPositions(final int size) {
        return Stream.iterate(0, i -> i+1)
                        .limit(size * size)
                        .map(num -> new Pair<Integer, Integer>(num / size, num % size));
    }

    @Test
    void testPawnCantMove() {
        assertTrue(getStreamOfPositions(BOARD_SIZE)
                    .map(position -> pawnPiece.canMove(position.getX(), position.getY()))
                    .allMatch(canMove -> !canMove));
    }

    @Test
    void testPawnCantActuallyMove() {
        assertTrue(getStreamOfPositions(BOARD_SIZE)
                    .map(position -> {
                        pawnPiece.move(position.getX(), position.getY());
                        return pawnPiece.getX() == START_POSITION.getX() && pawnPiece.getY() == START_POSITION.getY();
                    }).allMatch(hasntMoved -> hasntMoved));
    }
    
}
