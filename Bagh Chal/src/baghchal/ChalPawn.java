package baghchal;

import java.util.ArrayList;
import java.util.List;

public class ChalPawn extends AbstractPawn {
	
	//Constructor
	public ChalPawn(int x, int y) {
		super(x, y);
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
