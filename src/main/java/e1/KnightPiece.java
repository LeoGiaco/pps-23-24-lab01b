package e1;

public class KnightPiece extends AbstractPiece {

    public KnightPiece(int row, int column) {
        super(row, column);
    }

    public KnightPiece(Pair<Integer, Integer> position) {
        super(position);
    }

    @Override
    public boolean canMove(int row, int column) {
        int diffRow = Math.abs(row - this.getX());
        int diffColumn = Math.abs(column - this.getY());
        return diffRow != 0 && diffColumn != 0 && diffRow + diffColumn == 3;
    }
    
}
