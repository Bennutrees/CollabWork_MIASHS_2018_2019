package baghchal;

import java.util.ArrayList;

public class BaghPawn extends AbstractPawn {
	
	//Constructor
	public BaghPawn(int x, int y) {
		super(x, y);
	}
	
	
	//Methods
	public ArrayList<Coordinates> possibleEatMoves() {
		ArrayList<Coordinates> possibleMoves = new ArrayList<Coordinates>();
		Direction[] direction = Direction.getPossibleDirection(this.position.getRow(), this.position.getColumn());
		for (Direction dir : direction) {
			int dx = this.position.getRow()+dir.dx;
			int dy = this.position.getColumn()+dir.dy;

			//Si il peut y aller, si la position est occupé et s'il est occupé par un ChalPawn. Et si la position encore apres est libre.
			if(Move.canMoveInDirection(this.position,dir)
					&& Board.getBoard().getSquaresOnBoard()[dx][dy].getIsAvailable() == false
					&& Board.getBoard().getSquaresOnBoard()[dx][dy].getPawn() instanceof ChalPawn
					&& dx+dir.dx >= 0 && dx+dir.dx < 5 && dy+dir.dy >= 0 && dy+dir.dy < 5
					&& Board.getBoard().getSquaresOnBoard()[dx+dir.dx][dy+dir.dy].getIsAvailable()) {
				possibleMoves.add(new Coordinates(dx+dir.dx,dy+dir.dy));
			}

		}
		System.out.println(possibleMoves);
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
