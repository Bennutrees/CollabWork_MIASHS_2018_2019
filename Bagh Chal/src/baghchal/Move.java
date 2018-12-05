package baghchal;

import java.util.HashMap;

public class Move {

	private Coordinates start;
	private Coordinates finish;

	public Move(Coordinates start, Coordinates finish) {
		this.start = start;
		this.finish = finish;
	}

	public static boolean canMoveInDirection(Square initialSquare, Direction direction) {
        if (direction == null) {
            return false;
        }
        switch (direction) {
	        case N:
	            return initialSquare.getHasNorth();
	        case E:
	        	return initialSquare.getHasEast();
	        case S:
	        	return initialSquare.getHasSouth();
	        case W:
	        	return initialSquare.getHasWest();
	        case NE:
	        	return initialSquare.getHasNorthEast();
	        case NW:
	        	return initialSquare.getHasNorthWest();
	        case SE:
	        	return initialSquare.getHasSouthEast();
	        case SW:
	        	return initialSquare.getHasSouthWest();
	        default:
	            return false;
        }
	}

	public boolean isEatingMove() {
        return Math.abs(start.getX() - finish.getX()) > 1 || Math.abs(start.getY() - finish.getY()) > 1;
	}

	public Coordinates getEatenChalPosition() {
        int x = (start.getX() + finish.getX()) / 2;
        int y = (start.getY() + finish.getY()) / 2;
        return new Coordinates(x, y);
	}

	public void doMove() {
		HashMap<Square, AbstractPawn> pawnsMap = Board.getBoard().getPawnsMap();
		Square[][] squaresOnBoard = Board.getBoard().getSquaresOnBoard();
		Square startSquare = squaresOnBoard[start.getX()][start.getY()];
		Square finishSquare = squaresOnBoard[finish.getX()][finish.getY()];
		
		AbstractPawn selectedPawn = pawnsMap.get(startSquare);
		pawnsMap.put(startSquare, null);
		selectedPawn.setPosition(finish.getX(),finish.getY());
		pawnsMap.put(finishSquare, selectedPawn);
	}
}
