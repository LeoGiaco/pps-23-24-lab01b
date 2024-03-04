package e1;

public interface Piece {

    int getX();
    int getY();
    /**
     * Verifies if the piece can be moved to the given row and column.
     * @return {@code true} if the movement is allowed, {@code false} otherwise.
     */
    boolean canMove(int row, int column);
    /**
     * Attempts to move the piece on the given row and columns.
     */
    void move(int row, int column);
}
