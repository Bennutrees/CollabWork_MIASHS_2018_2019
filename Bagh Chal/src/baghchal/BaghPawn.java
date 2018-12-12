package baghchal;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class BaghPawn extends AbstractPawn {

	//Constructor
	public BaghPawn(int x, int y, Board board) {
		super(x, y, board);
	}


	//Methods
	public List<Move> possibleEatMoves() {
		List<Move> possibleMoves = new ArrayList<Move>();

		final int baghX = this.getPosition().getX();
		final int baghY = this.getPosition().getY();
		HashMap<Square, AbstractPawn> pawnsMap = this.board.getPawnsMap();
		Square[][] squaresOnBoard = this.board.getSquaresOnBoard();
		Square associatedSquareToBagh = squaresOnBoard[baghX][baghY];


		for (Direction dir : associatedSquareToBagh.getSquareAllowedDirections()) {
			final int chalX = baghX + dir.dx;
			final int chalY = baghY + dir.dy;
			Square associatedSquareToChal = squaresOnBoard[chalX][chalY];

			boolean squareIsOccupiedByPawn = associatedSquareToChal.getIsAvailable() == false;
			boolean pawnIsChal = pawnsMap.get(associatedSquareToChal) instanceof ChalPawn;
			boolean moveIsInBoardRange;
			Coordinates nextCoordinates = null;

			try {
				moveIsInBoardRange = true;
				nextCoordinates = new Coordinates(chalX + dir.dx,chalY + dir.dy);
			}
			catch(IllegalArgumentException e){
				moveIsInBoardRange = false;
			}

			if (squareIsOccupiedByPawn && pawnIsChal && moveIsInBoardRange) {
				boolean nextSquareIsAvailable = squaresOnBoard[chalX + dir.dx][chalY + dir.dy].getIsAvailable();

				if(nextSquareIsAvailable) {
					possibleMoves.add(new Move(this.getPosition(),nextCoordinates, this.board));
				}
			}

		}
		return possibleMoves;
	}

	@Override
	public List<Move> allPossibleMoves() {
		List<Move> possibleMoves = new ArrayList<Move>();
		possibleMoves.addAll(possibleEatMoves());
		possibleMoves.addAll(possibleMoves());
		return possibleMoves;
	}


	public String toString(){
		return "t";
	}

}
