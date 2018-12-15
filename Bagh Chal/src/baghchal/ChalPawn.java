package baghchal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
	public List<Move> allPawnPossibleMoves() {
		List<Move> possibleMoves = new ArrayList<Move>();
		possibleMoves.addAll(possibleMoves());
		return possibleMoves;
	}
	
	public static List<Move> allPossibleChalsMoves(Board board) {
		List<Move> possibleActions = new ArrayList<Move>();
		if(board.getNbChalsToPlace() > 0) {
	        Set<Square> squaresOnMap = board.getPawnsMap().keySet();
	        
	        for (Square actualSquare : squaresOnMap) {
	        	if (actualSquare.getIsAvailable()) 
	        		possibleActions.add(new Move(actualSquare.getPosition(), board));
	        }
		}
		else {
			ArrayList<ChalPawn> chals = board.getChalsOnBoard();
			for (ChalPawn chal : chals) {
				possibleActions.addAll(chal.allPawnPossibleMoves());
			}
		}
		return possibleActions;
	}
}
