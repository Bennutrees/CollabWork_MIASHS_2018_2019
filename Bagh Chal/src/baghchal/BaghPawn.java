package baghchal;

import java.util.ArrayList;
import java.util.HashMap;

public class BaghPawn extends AbstractPawn {
		
	//Constructor
	public BaghPawn(int x, int y) {
		super(x, y);
	}
	
	
	//Methods
	public ArrayList<Coordinates> possibleEatMoves() {
		ArrayList<Coordinates> possibleMoves = new ArrayList<Coordinates>();
		
		int x = this.position.getX();
		int y = this.position.getY();
		HashMap<Square, AbstractPawn> pawnsMap = Board.getBoard().getPawnsMap();
		Square[][] squaresOnBoard = Board.getBoard().getSquaresOnBoard();
		Square associatedSquareToBagh = squaresOnBoard[x][y];
		
		Direction[] direction = Direction.getSquarePossibleDirections(associatedSquareToBagh);
		
		for (Direction dir : direction) {
			int dx = x + dir.dx;
			int dy = y + dir.dy;

			boolean directionIsPossible = associatedSquareToBagh.movePossibleToward(dir);
			
			if (directionIsPossible) {
				Square associatedSquareToChal = squaresOnBoard[dx][dy];

				boolean squareIsOccupiedByPawn = squaresOnBoard[dx][dy].getIsAvailable() == false;
				boolean pawnIsChal = pawnsMap.get(squaresOnBoard[dx][dy]) instanceof ChalPawn;
				boolean moveIsInBoardRange = !associatedSquareToChal.getIsBorder() ;
				
				if (squareIsOccupiedByPawn && pawnIsChal && moveIsInBoardRange) {
					boolean nextSquareIsAvailable = squaresOnBoard[dx + dir.dx][dy + dir.dy].getIsAvailable();
					
					if(nextSquareIsAvailable) {
						possibleMoves.add(new Coordinates(dx + dir.dx,dy + dir.dy));
					}
				}
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
