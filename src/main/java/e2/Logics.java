package e2;

public interface Logics {
    
    boolean checkCell(int row, int column) throws IllegalArgumentException;
    
    boolean checkCell(Pair<Integer, Integer> position) throws IllegalArgumentException;

    int getMinesAdjacentToCell(int row, int columns) throws IllegalArgumentException;
    
    int getMinesAdjacentToCell(Pair<Integer, Integer> position) throws IllegalArgumentException;

    boolean discoverCell(int row, int column) throws IllegalArgumentException;

    boolean discoverCell(Pair<Integer, Integer> position) throws IllegalArgumentException; 

    boolean isCellDiscovered(int row, int column) throws IllegalArgumentException;

    boolean isCellDiscovered(Pair<Integer, Integer> position) throws IllegalArgumentException; 

    boolean toggleFlagOnCell(int row, int column) throws IllegalArgumentException;
    
    boolean toggleFlagOnCell(Pair<Integer, Integer> position) throws IllegalArgumentException;

    boolean isCellFlagged(int row, int column) throws IllegalArgumentException;
    
    boolean isCellFlagged(Pair<Integer, Integer> position) throws IllegalArgumentException;

    boolean victory();
}
