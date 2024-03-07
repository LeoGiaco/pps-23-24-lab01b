package e2;

public interface Minefield {

    // TODO: variants that accept a Pair as parameter.

    /**
     * Returns the number of mines in the adjacent cells.
     * @param row The row of the cell.
     * @param column The column of the cell.
     * @throws IllegalArgumentException if the cell is out of bounds.
     */
    int getNumberOfAdjacentMines(int row, int column) throws IllegalArgumentException;
    
    /**
     * Discovers the selected cell.
     * @param row The row of the cell.
     * @param column The column of the cell.
     * @return {@code true} if the cell has a mine, {@code false} otherwise.
     * @throws IllegalArgumentException if the cell has already been discovered or if it is out of bounds.
     */
    boolean discover(int row, int column) throws IllegalArgumentException;
    
    /**
     * Toggles the flag on the selected cell.
     * @param row The row of the cell.
     * @param column The column of the cell.
     * @return {@code true} if a flag is placed, {@code false} if it's removed.
     * @throws IllegalArgumentException if the cell has already been discovered or if it is out of bounds.
     */
    boolean toggleFlag(int row, int column) throws IllegalArgumentException;

}
