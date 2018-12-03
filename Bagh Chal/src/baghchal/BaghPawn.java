package baghchal;

import java.util.ArrayList;

public class BaghPawn extends AbstractPawn {

	public BaghPawn(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Coordinates> possibleEatMoves() {
		ArrayList<Coordinates> possibleMoves = new ArrayList<Coordinates>();
		Direction[] direction = Direction.getPossibleDirection(this.position.getLigne(), this.position.getColonne());
		for (Direction dir : direction) {
			int dx = this.position.getLigne()+dir.dx;
			int dy = this.position.getColonne()+dir.dy;

			//Si il peut y aller, si la position est occupé et s'il est occupé par un ChalPawn. Et si la position encore apres est libre.
			if(Move.canMoveInDirection(this.position,dir)
					&& Board.getBoard().getSquaresOnBoard()[dx][dy].isAvailable() == false
					&& Board.getBoard().getSquaresOnBoard()[dx][dy].getPawn() instanceof ChalPawn
					&& Board.getBoard().getSquaresOnBoard()[dx+dir.dx][dy+dir.dy].isAvailable()) {
				possibleMoves.add(new Coordinates(dx+dir.dx,dy+dir.dy));
			}

		}
		return possibleMoves;
	}

	public ArrayList<Coordinates> allPosibleMoves() {
		ArrayList<Coordinates> possibleMoves = new ArrayList<Coordinates>();
		possibleMoves.addAll(possibleEatMoves());
		possibleMoves.addAll(possibleMoves());
		return possibleMoves;
	}

	public String toString(){
		return "t";
	}

}
