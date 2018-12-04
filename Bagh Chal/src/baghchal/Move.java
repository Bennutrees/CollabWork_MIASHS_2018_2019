package baghchal;

public class Move {

	private Coordinates src;
	private Coordinates dest;

	//TODO: Reprise de code a signaler
	public Move(Coordinates src, Coordinates dest) {
		this.src = src;
		this.dest = dest;
	}

	public static boolean canMoveInDirection(Coordinates src, Direction direction) {
        if (direction == null) {
            return false;
        }
        switch (direction) {
        case N:
            return src.getColumn() > 0;
        case E:
            return src.getRow() < 4;
        case S:
            return src.getColumn() < 4;
        case W:
            return src.getRow() > 0;
        case NE:
            return (src.getRow() == 1 && src.getColumn() == 1) || (src.getRow() == 2 && src.getColumn() == 2) || (src.getRow() == 3 && src.getColumn() == 3) || (src.getRow() == 0 && src.getColumn() == 4)
                    || (src.getRow() == 1 && src.getColumn() == 3) || (src.getRow() == 3 && src.getColumn() == 1) || (src.getRow() == 0 && src.getColumn() == 2) || (src.getRow() == 2 && src.getColumn() == 4);
        case NW:
            return (src.getRow() == 1 && src.getColumn() == 3) || (src.getRow() == 2 && src.getColumn() == 2) || (src.getRow() == 3 && src.getColumn() == 1) || (src.getRow() == 1 && src.getColumn() == 1)
                    || (src.getRow() == 3 && src.getColumn() == 3) || (src.getRow() == 4 && src.getColumn() == 4) || (src.getRow() == 2 && src.getColumn() == 4) || (src.getRow() == 4 && src.getColumn() == 2);
        case SE:
            return (src.getRow() == 0 && src.getColumn() == 0) || (src.getRow() == 1 && src.getColumn() == 1) || (src.getRow() == 2 && src.getColumn() == 2) || (src.getRow() == 3 && src.getColumn() == 3)
                    || (src.getRow() == 0 && src.getColumn() == 2) || (src.getRow() == 1 && src.getColumn() == 3) || (src.getRow() == 2 && src.getColumn() == 0) || (src.getRow() == 3 && src.getColumn() == 1);
        case SW:
            return (src.getRow() == 4 && src.getColumn() == 0) || (src.getRow() == 3 && src.getColumn() == 1) || (src.getRow() == 2 && src.getColumn() == 2) || (src.getRow() == 1 && src.getColumn() == 3)
                    || (src.getRow() == 1 && src.getColumn() == 1) || (src.getRow() == 2 && src.getColumn() == 0) || (src.getRow() == 4 && src.getColumn() == 2) || (src.getRow() == 3 && src.getColumn() == 3);
        default:
            return false;
        }
	}

	public boolean isEatingMove() {
        return Math.abs(src.getRow() - dest.getRow()) > 1 || Math.abs(src.getColumn() - dest.getColumn()) > 1;
	}

	public Coordinates getBoardCoordsOfGoatBeingEaten() {
        int x = (src.getRow() + dest.getRow()) / 2;
        int y = (src.getColumn() + dest.getColumn()) / 2;
        return new Coordinates(x, y);
	}

	public void doMove() {
		Square[][] square =	Board.getBoard().getSquaresOnBoard();
		AbstractPawn pawn= square[src.getRow()][src.getColumn()].getPawn();
		square[src.getRow()][src.getColumn()].setPawn(null);
		square[dest.getRow()][dest.getColumn()].setPawn(pawn);
		pawn.setPosition(dest.getRow(),dest.getColumn());
	}

}
