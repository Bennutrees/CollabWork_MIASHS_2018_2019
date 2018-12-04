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
		Square[][] squaresOnBoard = Board.getBoard().getSquaresOnBoard();
		int x = this.position.getRow();
		int y = this.position.getColumn();
		Direction[] direction = Direction.getPossibleDirection(squaresOnBoard[x][y]);
		
		for (Direction dir : direction) {
			int dx = x+dir.dx;
			int dy = y+dir.dy;
			
			boolean directionIsPossible = Move.canMoveInDirection(this.position,dir);
			boolean squareIsAvailable = squaresOnBoard[dx][dy].getIsAvailable();
			
			if(directionIsPossible && squareIsAvailable) {
				possibleMoves.add(new Coordinates(dx,dy));
			}

		}
		return possibleMoves;
	}

}
