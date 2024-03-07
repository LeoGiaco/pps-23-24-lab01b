package e1;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final Piece pawn;
	private Piece knight;
	private final Random random = new Random();
	private final int size;
	 
    public LogicsImpl(int size){
    	this.size = size;
        this.pawn = new ImmobilePawnPiece(this.randomEmptyPosition());
        this.knight = new KnightPiece(this.randomEmptyPosition());	
    }

	public LogicsImpl(int size, Pair<Integer, Integer> pawnPosition, Pair<Integer, Integer> knightPosition) {
		this.size = size;
		this.pawn = new ImmobilePawnPiece(pawnPosition.getX(), pawnPosition.getY());
		this.knight = new KnightPiece(knightPosition.getX(), knightPosition.getY());
	}
    
	private final Pair<Integer,Integer> randomEmptyPosition(){
    	Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(size),this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawn!=null && this.pawn.getPosition().equals(pos) ? randomEmptyPosition() : pos;
    }
    
	@Override
	public boolean hit(int row, int col) {
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		knight.move(row, col);
		return knight.getPosition().equals(pawn.getPosition());
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.getPosition().equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.getPosition().equals(new Pair<>(row,col));
	}
	
}
