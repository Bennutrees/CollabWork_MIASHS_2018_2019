package baghchal;

import java.util.HashMap;

public class Move {

	private Coordinates start;
	private Coordinates finish;
	
	public Move(Coordinates finish) {
		this.start = finish;
		this.finish = finish;
	}
	
	public Move(Coordinates start, Coordinates finish) {
		this.start = start;
		this.finish = finish;
	}

	public boolean isEatingChalMove() {
        return Math.abs(start.getX() - finish.getX()) > 1 || Math.abs(start.getY() - finish.getY()) > 1;
	}

	private Coordinates getEatenChalPosition() {
        int x = (start.getX() + finish.getX()) / 2;
        int y = (start.getY() + finish.getY()) / 2;
        return new Coordinates(x, y);
	}

	/**
	 * Return null if no Chal is eaten, else return eaten chal position*/
	public Coordinates doMove() {
		Coordinates eatenChal = null;
		Board board = Board.getBoard();

		HashMap<Square, AbstractPawn> pawnsMap = board.getPawnsMap();
		Square[][] squaresOnBoard = board.getSquaresOnBoard();
		Square startSquare = squaresOnBoard[start.getX()][start.getY()];
		Square finishSquare = squaresOnBoard[finish.getX()][finish.getY()];
		
		if(this.isEatingChalMove()) {
			eatenChal = this.getEatenChalPosition();
        	board.eatChal(eatenChal);
		}
		
		AbstractPawn selectedPawn = pawnsMap.get(startSquare);
		//Remove pawn from startSquare
		pawnsMap.put(startSquare, null);
		startSquare.setAvailability(true);
		//Add pawn to finishSquare
		System.out.println(selectedPawn);
		selectedPawn.setPosition(finish.getX(),finish.getY());
		pawnsMap.put(finishSquare, selectedPawn);
		finishSquare.setAvailability(false);

		return eatenChal;
	}
	
	public Coordinates getStart() {
		return start;
	}

	public Coordinates getFinish() {
		return finish;
	}
	
	public String toString() {
		return start.toString() + " : " + finish.toString();
	}
}
