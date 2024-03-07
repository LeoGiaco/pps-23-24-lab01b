package e2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class MinefieldImpl implements Minefield {

    private final int fieldSize;
    private final int numberOfMines;
    private List<Pair<Integer, Integer>> mines;
    private final List<Pair<Integer, Integer>> flags;
    private final List<Pair<Integer, Integer>> discoveredCells;
    private boolean firstCellDiscovered = false;
    private final Random random = new Random();
    private boolean trippedMine = false;

    public MinefieldImpl(int fieldSize, int numberOfMines) {
        if (fieldSize <= 0 || numberOfMines <= 0 || numberOfMines >= fieldSize * fieldSize) {
            throw new IllegalArgumentException("Invalid parameters given.");
        }
        this.fieldSize = fieldSize;
        this.numberOfMines = numberOfMines;
        this.discoveredCells = new ArrayList<>(fieldSize * fieldSize - numberOfMines);
        this.flags = new LinkedList<>();
    }

    private void checkOutOfBounds(Pair<Integer, Integer> position) {
        if (position.getX() < 0 || position.getX() >= this.fieldSize ||
                position.getY() < 0 || position.getY() >= this.fieldSize) {
            throw new IllegalArgumentException("Position out of bounds.");
        }
    }

    private void checkCellIsDiscovered(Pair<Integer, Integer> position) {
        if(this.isDiscovered(position)) {
            throw new IllegalArgumentException("Cell already discovered.");
        }
    }

    @Override
    public int getNumberOfAdjacentMines(int row, int column) throws IllegalArgumentException {
        return this.getNumberOfAdjacentMines(new Pair<>(row, column));
    }

    @Override
    public int getNumberOfAdjacentMines(Pair<Integer, Integer> position) throws IllegalArgumentException {
        checkOutOfBounds(position);
        return (int) this.mines.stream()
                        .map(minePosition -> new Pair<>(minePosition.getX() - position.getX(), minePosition.getY() - position.getY()))
                        .filter(mineDistance -> Math.abs(mineDistance.getX()) <= 1 && Math.abs(mineDistance.getY()) <= 1)
                        .count();
    }

    @Override
    public boolean peek(int row, int column) throws IllegalArgumentException {
        return this.peek(new Pair<>(row, column));
    }

    @Override
    public boolean peek(Pair<Integer, Integer> position) throws IllegalArgumentException {
        checkOutOfBounds(position);
        tryPlaceMines(position);
        return this.mines.contains(position);
    }
    
    void tryPlaceMines(Pair<Integer, Integer> excludedPosition) {
        if (this.firstCellDiscovered) {
            return;
        }
        this.mines = Stream.generate(() -> new Pair<>(this.random.nextInt(fieldSize), random.nextInt(fieldSize)))
                        .filter(position -> !position.equals(excludedPosition))
                        .distinct()
                        .limit(this.numberOfMines)
                        .toList();
        this.firstCellDiscovered = true;
    }

    @Override
    public boolean discover(int row, int column) throws IllegalArgumentException {
        return this.discover(new Pair<>(row, column));
    }
    
    @Override
    public boolean discover(Pair<Integer, Integer> position) throws IllegalArgumentException {
        checkCellIsDiscovered(position);
        if (this.peek(position)) {
            this.trippedMine = true;
            return true;
        } else {
            discoveredCells.add(position);
            return false;
        }
    }

    @Override
    public boolean isDiscovered(int row, int column) throws IllegalArgumentException {
        return this.isDiscovered(new Pair<>(row, column));
    }

    @Override
    public boolean isDiscovered(Pair<Integer, Integer> position) throws IllegalArgumentException {
        checkOutOfBounds(position);
        return this.discoveredCells.contains(position);
    }

    @Override
    public boolean toggleFlag(int row, int column) {
        return this.toggleFlag(new Pair<>(row, column));
    }
    
    @Override
    public boolean toggleFlag(Pair<Integer, Integer> position) throws IllegalArgumentException {        
        checkOutOfBounds(position);
        checkCellIsDiscovered(position);
        if (this.flags.contains(position)) {
            this.flags.remove(position);
            return false;
        } else {
            this.flags.add(position);
            return true;
        }
    }

    @Override
    public boolean isFlagged(int row, int column) throws IllegalArgumentException {
        return this.isFlagged(new Pair<>(row, column));
    }

    @Override
    public boolean isFlagged(Pair<Integer, Integer> position) throws IllegalArgumentException {
        checkOutOfBounds(position);
        return this.flags.contains(position);
    }

    @Override
    public boolean isCleared() {
        return !trippedMine && this.discoveredCells.size() + this.numberOfMines == this.fieldSize * this.fieldSize;
    }

}
