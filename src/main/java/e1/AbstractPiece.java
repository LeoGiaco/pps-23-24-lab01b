package e1;

public abstract class AbstractPiece implements Piece {

    Pair<Integer, Integer> position;

    public AbstractPiece(int row, int column) {
        this.position = new Pair<>(row, column);
    }

    public AbstractPiece(Pair<Integer, Integer> position) {
        this.position = new Pair<>(position.getX(), position.getY());
    }

    @Override
    public abstract boolean canMove(int row, int column);

    @Override
    public int getX() {
        return position.getX();
    }

    @Override
    public int getY() {
        return position.getY();   
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return new Pair<>(this.getX(), this.getY());
    }

    @Override
    public void move(int row, int column) {
        if (canMove(row, column)) {
            this.position = new Pair<Integer,Integer>(row, column);
        }
    }
    
}
