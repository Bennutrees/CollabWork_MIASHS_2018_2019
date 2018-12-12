package baghchal;

import java.util.ArrayList;
import java.util.List;

public class ChalPawn extends AbstractPawn {
	
	//Constructor
	public ChalPawn(int x, int y, Board board) {
		super(x, y, board);
	}

	//Methods
	public String toString(){
		return "c";
	}

	@Override
	public List<Move> allPossibleMoves() {
		List<Move> possibleMoves = new ArrayList<Move>();
		possibleMoves.addAll(possibleMoves());
		return possibleMoves;
	}
}
