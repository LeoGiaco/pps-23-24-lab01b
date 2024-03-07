package e2;

public class LogicsImpl implements Logics {

    Minefield field;

    public LogicsImpl(int size, int numberOfMines) {
        this.field = new MinefieldImpl(size, numberOfMines);
    }

    @Override
    public boolean checkCell(int row, int column) throws IllegalArgumentException {
        return this.field.peek(row, column);
    }

    @Override
    public boolean checkCell(Pair<Integer, Integer> position) throws IllegalArgumentException {
        return this.field.peek(position);
    }

    @Override
    public int getMinesAdjacentToCell(int row, int columns) throws IllegalArgumentException {
        return this.field.getNumberOfAdjacentMines(row, columns);
    }

    @Override
    public int getMinesAdjacentToCell(Pair<Integer, Integer> position) throws IllegalArgumentException {
        return this.field.getNumberOfAdjacentMines(position);
    }

    @Override
    public boolean discoverCell(int row, int column) throws IllegalArgumentException {
        return this.discoverCell(new Pair<>(row, column));
    }

    @Override
    public boolean discoverCell(Pair<Integer, Integer> position) throws IllegalArgumentException {
        boolean foundMine = this.field.discover(position);
        if (!foundMine && this.getMinesAdjacentToCell(position) == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    var pos = new Pair<>(position.getX() + i, position.getY() + j);
                    try {
                        if (!this.isCellDiscovered(pos)) {
                            this.discoverCell(pos);
                        }
                    } catch (IllegalArgumentException e) {}
                }
            }
        }
        return foundMine;
    }
    
    @Override
    public boolean isCellDiscovered(int row, int column) throws IllegalArgumentException {
        return this.field.isDiscovered(row, column);
    }

    @Override
    public boolean isCellDiscovered(Pair<Integer, Integer> position) throws IllegalArgumentException {
        return this.field.isDiscovered(position);
    }

    @Override
    public boolean toggleFlagOnCell(int row, int column) throws IllegalArgumentException {
        return this.field.toggleFlag(row, column);
    }
    
    @Override
    public boolean toggleFlagOnCell(Pair<Integer, Integer> position) throws IllegalArgumentException {
        return this.field.toggleFlag(position);
    }
    
    @Override
    public boolean isCellFlagged(int row, int column) throws IllegalArgumentException {
        return this.field.isFlagged(row, column);
    }

    @Override
    public boolean isCellFlagged(Pair<Integer, Integer> position) throws IllegalArgumentException {
        return this.field.isFlagged(position);
    }

    @Override
    public boolean victory() {
        return this.field.isCleared();
    }

}
