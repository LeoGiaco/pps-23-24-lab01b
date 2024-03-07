package e1;

public class ImmobilePawnPiece extends AbstractPiece {
    
    public ImmobilePawnPiece(int row, int column) {
        super(row, column);
    }

    public ImmobilePawnPiece(Pair<Integer, Integer> position) {
        super(position);
    }

    @Override
    public boolean canMove(int row, int column) {
        return false;
    }
    
}
