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
            return src.getColonne() > 0;
        case E:
            return src.getLigne() < 4;
        case S:
            return src.getColonne() < 4;
        case W:
            return src.getLigne() > 0;
        case NE:
            return (src.getLigne() == 1 && src.getColonne() == 1) || (src.getLigne() == 2 && src.getColonne() == 2) || (src.getLigne() == 3 && src.getColonne() == 3) || (src.getLigne() == 0 && src.getColonne() == 4)
                    || (src.getLigne() == 1 && src.getColonne() == 3) || (src.getLigne() == 3 && src.getColonne() == 1) || (src.getLigne() == 0 && src.getColonne() == 2) || (src.getLigne() == 2 && src.getColonne() == 4);
        case NW:
            return (src.getLigne() == 1 && src.getColonne() == 3) || (src.getLigne() == 2 && src.getColonne() == 2) || (src.getLigne() == 3 && src.getColonne() == 1) || (src.getLigne() == 1 && src.getColonne() == 1)
                    || (src.getLigne() == 3 && src.getColonne() == 3) || (src.getLigne() == 4 && src.getColonne() == 4) || (src.getLigne() == 2 && src.getColonne() == 4) || (src.getLigne() == 4 && src.getColonne() == 2);
        case SE:
            return (src.getLigne() == 0 && src.getColonne() == 0) || (src.getLigne() == 1 && src.getColonne() == 1) || (src.getLigne() == 2 && src.getColonne() == 2) || (src.getLigne() == 3 && src.getColonne() == 3)
                    || (src.getLigne() == 0 && src.getColonne() == 2) || (src.getLigne() == 1 && src.getColonne() == 3) || (src.getLigne() == 2 && src.getColonne() == 0) || (src.getLigne() == 3 && src.getColonne() == 1);
        case SW:
            return (src.getLigne() == 4 && src.getColonne() == 0) || (src.getLigne() == 3 && src.getColonne() == 1) || (src.getLigne() == 2 && src.getColonne() == 2) || (src.getLigne() == 1 && src.getColonne() == 3)
                    || (src.getLigne() == 1 && src.getColonne() == 1) || (src.getLigne() == 2 && src.getColonne() == 0) || (src.getLigne() == 4 && src.getColonne() == 2) || (src.getLigne() == 3 && src.getColonne() == 3);
        default:
            return false;
        }
	}

	public boolean isEatingMove() {
        return Math.abs(src.getLigne() - dest.getLigne()) > 1 || Math.abs(src.getColonne() - dest.getColonne()) > 1;
	}

	public Coordinates getBoardCoordsOfGoatBeingEaten() {
        int x = (src.getLigne() + dest.getLigne()) / 2;
        int y = (src.getColonne() + dest.getColonne()) / 2;
        return new Coordinates(x, y);
	}

	public void doMove() {
		Square[][] square =	Board.getBoard().getSquaresOnBoard();
		AbstractPawn pawn= square[src.getLigne()][src.getColonne()].getPawn();
		square[src.getLigne()][src.getColonne()].setPawn(null);
		square[dest.getLigne()][dest.getColonne()].setPawn(pawn);
	}

}
