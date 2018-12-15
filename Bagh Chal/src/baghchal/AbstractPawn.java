package baghchal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public abstract class AbstractPawn {

	protected Coordinates position;
	protected Board board;

	public AbstractPawn(int x,int y, Board board) {
		this.position = new Coordinates(x,y);
		this.board = board;
	}

	public Coordinates getPosition() {
		return position;
	}

	public void setPosition(int x, int y) {
		this.position.setPosition(x,y);
	}
	
	public ArrayList<Square> squaresAround(int range) {
		ArrayList<Square> squaresAround = new ArrayList<Square>();
		
		HashMap<Square, AbstractPawn> pawnsMap = this.board.getPawnsMap();
        Set<Square> squaresOnMap = pawnsMap.keySet();

        for (Square currentSquare : squaresOnMap) {
        	boolean isAroundThisSquare = (Math.abs(currentSquare.getPosition().getX() - this.getPosition().getX()) <= range)
        									|| (Math.abs(currentSquare.getPosition().getY() - this.getPosition().getY()) <= range);
        	if (isAroundThisSquare) {
        		squaresAround.add(currentSquare);
        	}
        }
        return squaresAround;
	}

	public List<Move> possibleMoves() {
		List<Move> possibleMoves = new ArrayList<Move>();

		final int pawnX1 = this.getPosition().getX();
		final int pawnY1 = this.getPosition().getY();
		Square[][] squaresOnBoard = this.board.getSquaresOnBoard();
		Square associatedSquareToPawn = squaresOnBoard[pawnX1][pawnY1];

		for (Direction dir : associatedSquareToPawn.getSquareAllowedDirections()) {
			final int pawnX2 = pawnX1 + dir.dx;
			final int pawnY2 = pawnY1 + dir.dy;
			boolean squareIsAvailable = squaresOnBoard[pawnX2][pawnY2].getIsAvailable();

			if(squareIsAvailable) {
				possibleMoves.add(new Move(this.getPosition(),new Coordinates(pawnX2, pawnY2), this.board));
			}
		}
		return possibleMoves;
	}
	
	public abstract List<Move> allPawnPossibleMoves();
}
