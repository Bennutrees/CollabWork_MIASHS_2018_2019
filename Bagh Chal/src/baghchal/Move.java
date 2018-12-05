package baghchal;

import java.util.HashMap;

public class Move {

	private Coordinates start;
	private Coordinates finish;

	public Move(Coordinates start, Coordinates finish) {
		this.start = start;
		this.finish = finish;
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
		//Remove pawn from startSquare
		pawnsMap.put(startSquare, null);
		startSquare.setAvailability(true);
		//Add pawn to finishSquare
		selectedPawn.setPosition(finish.getX(),finish.getY());
		pawnsMap.put(finishSquare, selectedPawn);
		finishSquare.setAvailability(false);
	}
}
