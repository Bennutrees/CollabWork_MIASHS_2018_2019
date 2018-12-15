package baghchal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ChalPawn extends AbstractPawn {
	
	private boolean isVulnerable;
	
	//Constructor
	public ChalPawn(int x, int y, Board board) {
		super(x, y, board);
		this.isVulnerable = this.calculateVulnerability();
	}

	//Methods
	public String toString(){
		return "c";
	}
	
	private boolean calculateVulnerability() {
		ArrayList<Square> squaresAround = this.squaresAround(1);
		
		Iterator<Square> squareIterator = squaresAround.iterator();
		int squareIndex = 0;
		while (squareIterator.hasNext()) {
			Square currentSquare = squaresAround.get(squareIndex);
    		AbstractPawn currentPawn = board.getPawnsMap().get(currentSquare);
    		if (currentPawn instanceof BaghPawn) {
				return ((BaghPawn) currentPawn).possibleEatMoves() != null;
			}
    		squareIndex++;
        }
        return false;
	}
	
	public boolean getIsVulnerable() {
		return this.isVulnerable;
	}
	
	public int getAnglesOfAttack() {
		int nbOfAngles = 0;
		ArrayList<Square> squaresAround = this.squaresAround(1);
		
		Iterator<Square> squareIterator = squaresAround.iterator();
		int squareIndex = 0;
		while (squareIterator.hasNext() && squareIndex < 4) {
			Square currentSquare = squaresAround.get(squareIndex);
			boolean oppositeSquareExist = squaresAround.get(7 - squareIndex) instanceof Square;
			
			if (currentSquare.getIsAvailable() && oppositeSquareExist) {
				Square oppositeSquare = squaresAround.get(7 - squareIndex);
				if (oppositeSquare.getIsAvailable()) {
					nbOfAngles += 2;
				}
			}
			squareIndex++;
		}
		return nbOfAngles;
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
