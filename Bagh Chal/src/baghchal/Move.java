package baghchal;

public class Move {

	private Coordinates start;
	private Coordinates finish;

	//TODO: Reprise de code a signaler
	public Move(Coordinates start, Coordinates finish) {
		this.start = start;
		this.finish = finish;
	}

	public static boolean canMoveInDirection(Coordinates position, Direction direction) {
        if (direction == null) {
            return false;
        }
        switch (direction) {
        case N:
            return position.getColumn() > 0;
        case E:
            return position.getRow() < 4;
        case S:
            return position.getColumn() < 4;
        case W:
            return position.getRow() > 0;
        case NE:
            return (position.getRow() == 1 && position.getColumn() == 1) || (position.getRow() == 2 && position.getColumn() == 2) || (position.getRow() == 3 && position.getColumn() == 3) || (position.getRow() == 0 && position.getColumn() == 4)
                    || (position.getRow() == 1 && position.getColumn() == 3) || (position.getRow() == 3 && position.getColumn() == 1) || (position.getRow() == 0 && position.getColumn() == 2) || (position.getRow() == 2 && position.getColumn() == 4);
        case NW:
            return (position.getRow() == 1 && position.getColumn() == 3) || (position.getRow() == 2 && position.getColumn() == 2) || (position.getRow() == 3 && position.getColumn() == 1) || (position.getRow() == 1 && position.getColumn() == 1)
                    || (position.getRow() == 3 && position.getColumn() == 3) || (position.getRow() == 4 && position.getColumn() == 4) || (position.getRow() == 2 && position.getColumn() == 4) || (position.getRow() == 4 && position.getColumn() == 2);
        case SE:
            return (position.getRow() == 0 && position.getColumn() == 0) || (position.getRow() == 1 && position.getColumn() == 1) || (position.getRow() == 2 && position.getColumn() == 2) || (position.getRow() == 3 && position.getColumn() == 3)
                    || (position.getRow() == 0 && position.getColumn() == 2) || (position.getRow() == 1 && position.getColumn() == 3) || (position.getRow() == 2 && position.getColumn() == 0) || (position.getRow() == 3 && position.getColumn() == 1);
        case SW:
            return (position.getRow() == 4 && position.getColumn() == 0) || (position.getRow() == 3 && position.getColumn() == 1) || (position.getRow() == 2 && position.getColumn() == 2) || (position.getRow() == 1 && position.getColumn() == 3)
                    || (position.getRow() == 1 && position.getColumn() == 1) || (position.getRow() == 2 && position.getColumn() == 0) || (position.getRow() == 4 && position.getColumn() == 2) || (position.getRow() == 3 && position.getColumn() == 3);
        default:
            return false;
        }
	}

	public boolean isEatingMove() {
        return Math.abs(start.getRow() - finish.getRow()) > 1 || Math.abs(start.getColumn() - finish.getColumn()) > 1;
	}

	public Coordinates getEatenChalPosition() {
        int x = (start.getRow() + finish.getRow()) / 2;
        int y = (start.getColumn() + finish.getColumn()) / 2;
        return new Coordinates(x, y);
	}

	public void doMove() {
		Square[][] square =	Board.getBoard().getSquaresOnBoard();
		AbstractPawn pawn= square[start.getRow()][start.getColumn()].getPawn();
		square[start.getRow()][start.getColumn()].setPawn(null);
		square[finish.getRow()][finish.getColumn()].setPawn(pawn);
		pawn.setPosition(finish.getRow(),finish.getColumn());
	}

}
