package baghchal;

import java.util.ArrayList;

public abstract class AbstractPawn {

	protected Coordinates position;

	public AbstractPawn(int x,int y) {
		this.position = new Coordinates(x,y);
	}

	public Coordinates getPosition() {
		return position;
	}

	public void setPosition(int x, int y) {
		this.position.setPosition(x,y);
	}

	public ArrayList<Coordinates> possibleMoves() {
		ArrayList<Coordinates> possibleMoves = new ArrayList<Coordinates>();

		int x = this.position.getX();
		int y = this.position.getY();
		Square[][] squaresOnBoard = Board.getBoard().getSquaresOnBoard();
		Square associatedSquareToPawn = squaresOnBoard[x][y];

		for (Direction dir : associatedSquareToPawn.getSquareAllowedDirections()) {
			int dx = x + dir.dx;
			int dy = y + dir.dy;
			boolean squareIsAvailable = squaresOnBoard[dx][dy].getIsAvailable();

			if(squareIsAvailable) {
				possibleMoves.add(new Coordinates(dx,dy));
			}

		}
		return possibleMoves;
	}

}
