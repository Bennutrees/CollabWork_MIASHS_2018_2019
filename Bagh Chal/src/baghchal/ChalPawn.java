package baghchal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ChalPawn extends AbstractPawn {
	
	private boolean isVulnerable;
	
	//Constructor
	public ChalPawn(int x, int y, Board board) {
		super(x, y, board);
		this.isVulnerable = this.calculateVulnerability(board);
	}

	//Methods
	public String toString(){
		return "c";
	}
	
	private boolean calculateVulnerability(Board board) {
		HashMap<Square, AbstractPawn> pawnsMap = board.getPawnsMap();
        Set<Square> squaresOnMap = pawnsMap.keySet();
        for (Square currentSquare : squaresOnMap) {
        	boolean isAroundThisSquare = (Math.abs(currentSquare.getPosition().getX() - this.getPosition().getX()) == 1)
        									|| (Math.abs(currentSquare.getPosition().getY() - this.getPosition().getY()) == 1);
        	if (isAroundThisSquare) {
        		AbstractPawn currentPawn = pawnsMap.get(currentSquare);
        		if (currentPawn instanceof BaghPawn) {
					return ((BaghPawn) currentPawn).possibleEatMoves() != null;
				}
        	}
        }
        return false;
	}
	
	public boolean getIsVulnerable() {
		return this.isVulnerable;
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
	        
	        for (Square currentSquare : squaresOnMap) {
	        	if (currentSquare.getIsAvailable()) 
	        		possibleActions.add(new Move(currentSquare.getPosition(), board));
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
