package e2;

public interface Minefield {

    /**
     * Returns the number of mines in the adjacent cells.
     * @param row The row of the cell.
     * @param column The column of the cell.
     * @throws IllegalArgumentException if the cell is out of bounds.
     */
    int getNumberOfAdjacentMines(int row, int column) throws IllegalArgumentException;
    
    /**
     * Returns the number of mines in the adjacent cells.
     * @param position The position of the cell.
     * @throws IllegalArgumentException if the cell is out of bounds.
     */
    int getNumberOfAdjacentMines(Pair<Integer, Integer> position) throws IllegalArgumentException;

    /**
     * Checks if the selected cell has a mine.
     * @param row The row of the cell.
     * @param column The column of the cell.
     * @return {@code true} if the cell has a mine, {@code false} otherwise.
     * @throws IllegalArgumentException if the cell is out of bounds.
     */
    boolean peek(int row, int column) throws IllegalArgumentException;
    
    /**
     * Checks if the selected cell has a mine.
     * @param position The position of the cell.
     * @return {@code true} if the cell has a mine, {@code false} otherwise.
     * @throws IllegalArgumentException if the cell is out of bounds.
     */
    boolean peek(Pair<Integer, Integer> position) throws IllegalArgumentException;

    /**
     * Discovers the selected cell.
     * @param row The row of the cell.
     * @param column The column of the cell.
     * @return {@code true} if the cell has a mine, {@code false} otherwise.
     * @throws IllegalArgumentException if the cell has already been discovered or if it is out of bounds.
     */
    boolean discover(int row, int column) throws IllegalArgumentException;
    
    /**
     * Discovers the selected cell.
     * @param position The position of the cell.
     * @return {@code true} if the cell has a mine, {@code false} otherwise.
     * @throws IllegalArgumentException if the cell has already been discovered or if it is out of bounds.
     */
    boolean discover(Pair<Integer, Integer> position) throws IllegalArgumentException;
    
    /**
     * Checks if a cell is already discovered.
     * @param row The row of the cell.
     * @param column The column of the cell.
     * @return {@code true} if the cell is discovered, {@code false} otherwise.
     * @throws IllegalArgumentException if the cell is out of bounds.
     */
    boolean isDiscovered(int row, int column) throws IllegalArgumentException;
    
    /**
     * Checks if a cell is already discovered.
     * @param position The position of the cell.
     * @return {@code true} if the cell is discovered, {@code false} otherwise.
     * @throws IllegalArgumentException if the cell is out of bounds.
     */
    boolean isDiscovered(Pair<Integer, Integer> position) throws IllegalArgumentException;

    /**
     * Toggles the flag on the selected cell.
     * @param row The row of the cell.
     * @param column The column of the cell.
     * @return {@code true} if a flag is placed, {@code false} if it's removed.
     * @throws IllegalArgumentException if the cell has already been discovered or if it is out of bounds.
     */
    boolean toggleFlag(int row, int column) throws IllegalArgumentException;
    
    /**
     * Toggles the flag on the selected cell.
     * @param position The position of the cell.
     * @return {@code true} if a flag is placed, {@code false} if it's removed.
     * @throws IllegalArgumentException if the cell has already been discovered or if it is out of bounds.
     */
    boolean toggleFlag(Pair<Integer, Integer> position) throws IllegalArgumentException;

        /**
     * Checks if a cell is flagged.
     * @param row The row of the cell.
     * @param column The column of the cell.
     * @return {@code true} if the cell is flagged, {@code false} otherwise.
     * @throws IllegalArgumentException if the cell is out of bounds.
     */
    boolean isFlagged(int row, int column) throws IllegalArgumentException;
    
    /**
     * Checks if a cell is flagged.
     * @param position The position of the cell.
     * @return {@code true} if the cell is flagged, {@code false} otherwise.
     * @throws IllegalArgumentException if the cell is out of bounds.
     */
    boolean isFlagged(Pair<Integer, Integer> position) throws IllegalArgumentException;


    /**
     * Checks if all the empty cells have been discovered and no mines have been tripped.
     * @return {@code true} if the game is won, {@code false} otherwise.
     */
    boolean isCleared();

}
