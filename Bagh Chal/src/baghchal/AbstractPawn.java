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
		Direction[] direction = Direction.getPossibleDirection(this.position.getRow(), this.position.getColumn());
		for (Direction dir : direction) {
			int dx = this.position.getRow()+dir.dx;
			int dy = this.position.getColumn()+dir.dy;
			//Si il peut y aller et si la position est libre
			if(Move.canMoveInDirection(this.position,dir) &&
					Board.getBoard().getSquaresOnBoard()[dx][dy].getIsAvailable()) {
				possibleMoves.add(new Coordinates(dx,dy));
			}

		}
		return possibleMoves;
	}

}
