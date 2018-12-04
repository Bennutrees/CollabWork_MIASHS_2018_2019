package baghchal;

import java.util.ArrayList;

public class BaghPawn extends AbstractPawn {
	
	//Constructor
	public BaghPawn(int x, int y) {
		super(x, y);
	}
	
	
	//Methods
	public ArrayList<Coordinates> possibleEatMoves() {
		ArrayList<Coordinates> possibleMoves = new ArrayList<Coordinates>();
		Direction[] direction = Direction.getPossibleDirection(this.position.getRow(), this.position.getColumn());
		
		for (Direction dir : direction) {
			int dx = this.position.getRow()+dir.dx;
			int dy = this.position.getColumn()+dir.dy;

			boolean directionIsPossible = Move.canMoveInDirection(this.position,dir);
			boolean squareIsOccupiedByPawn = Board.getBoard().getSquaresOnBoard()[dx][dy].getIsAvailable() == false;
			boolean pawnIsChal = Board.getBoard().getSquaresOnBoard()[dx][dy].getPawn() instanceof ChalPawn;
			boolean moveIsInBoardRange = dx+dir.dx >= 0 && dx+dir.dx < 5 && dy+dir.dy >= 0 && dy+dir.dy < 5;
			boolean nextSquareIsAvailable = Board.getBoard().getSquaresOnBoard()[dx+dir.dx][dy+dir.dy].getIsAvailable();
			
			if(directionIsPossible && squareIsOccupiedByPawn && pawnIsChal && moveIsInBoardRange && nextSquareIsAvailable) {
				possibleMoves.add(new Coordinates(dx+dir.dx,dy+dir.dy));
			}

		}
		System.out.println(possibleMoves);
		return possibleMoves;
	}

	public ArrayList<Coordinates> allPossibleMoves() {
		ArrayList<Coordinates> possibleMoves = new ArrayList<Coordinates>();
		possibleMoves.addAll(possibleEatMoves());
		possibleMoves.addAll(possibleMoves());
		return possibleMoves;
	}
	
	
	public String toString(){
		return "t";
	}

}
