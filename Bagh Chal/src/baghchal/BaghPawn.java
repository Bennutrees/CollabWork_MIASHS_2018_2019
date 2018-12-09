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


		for (Direction dir : associatedSquareToBagh.getSquareAllowedDirections()) {
			int dx = x + dir.dx;
			int dy = y + dir.dy;
			Square associatedSquareToChal = squaresOnBoard[dx][dy];

			boolean squareIsOccupiedByPawn = associatedSquareToChal.getIsAvailable() == false;
			boolean pawnIsChal = pawnsMap.get(associatedSquareToChal) instanceof ChalPawn;
			boolean moveIsInBoardRange;
			Coordinates nextCoordinates = null;

			try {
				moveIsInBoardRange = true;
				nextCoordinates = new Coordinates(dx + dir.dx,dy + dir.dy);
			}
			catch(IllegalArgumentException e){
				moveIsInBoardRange = false;
			}

			if (squareIsOccupiedByPawn && pawnIsChal && moveIsInBoardRange) {
				boolean nextSquareIsAvailable = squaresOnBoard[dx + dir.dx][dy + dir.dy].getIsAvailable();

				if(nextSquareIsAvailable) {
					possibleMoves.add(nextCoordinates);
				}
			}

		}
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
