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

    public MinefieldImpl(int fieldSize, int numberOfMines) {
        this.fieldSize = fieldSize;
        this.numberOfMines = numberOfMines;
        this.discoveredCells = new ArrayList<>(fieldSize * fieldSize - numberOfMines);
        this.flags = new LinkedList<>();
    }

    @Override
    public int getNumberOfAdjacentMines(int row, int column) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNumberOfAdjacentMines'");
    }

    void tryInitializeMinefield(Pair<Integer, Integer> excludedPosition) {
        if (firstCellDiscovered) {
            return;
        }
        mines = Stream.generate(() -> new Pair<>(random.nextInt(fieldSize), random.nextInt(fieldSize)))
                        .filter(position -> !position.equals(excludedPosition))
                        .distinct()
                        .limit(numberOfMines)
                        .toList();
        firstCellDiscovered = true;
    }

    private void checkOutOfBounds(Pair<Integer, Integer> position) {
        if (position.getX() < 0 || position.getX() >= fieldSize ||
                position.getY() < 0 || position.getY() >= fieldSize) {
            throw new IllegalArgumentException("Position out of bounds.");
        }
    }

    private void checkCellIsDiscovered(Pair<Integer, Integer> position) {
        if(discoveredCells.contains(position)) {
            throw new IllegalArgumentException("Cell already discovered."); // TODO: maybe just return false?
        }
    }

    @Override
    public boolean discover(int row, int column) throws IllegalArgumentException {
        var position = new Pair<>(row, column);
        checkOutOfBounds(position);
        checkCellIsDiscovered(position);
        tryInitializeMinefield(position);
        if (mines.contains(position)) {
            return true;
        } else {
            discoveredCells.add(position);
            return false;
        }
    }

    @Override
    public boolean toggleFlag(int row, int column) {
        var position = new Pair<>(row, column);
        checkOutOfBounds(position);
        checkCellIsDiscovered(position);
        if (flags.contains(position)) {
            flags.remove(position);
            return false;
        } else {
            flags.add(position);
            return true;
        }
    }

}
