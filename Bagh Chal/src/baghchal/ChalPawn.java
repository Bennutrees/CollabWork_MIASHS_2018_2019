package baghchal;

import java.util.*;

public class ChalPawn extends AbstractPawn {

	//Constructor
	public ChalPawn(int x, int y, Board board) {
		super(x, y, board);
	}

	//Methods
	public String toString(){
		return "c";
	}

	public boolean isVulnerable() {
		ArrayList<Square> squaresAround = this.squaresAround(1);

		for (Square currentSquare : squaresAround) {
			AbstractPawn currentPawn = board.getPawnsMap().get(currentSquare);
    		if (currentPawn instanceof BaghPawn) {
    			int x = currentPawn.getPosition().getX() - this.getPosition().getX();
    			int y = currentPawn.getPosition().getY() - this.getPosition().getY();
    			Direction baghDirection = Direction.getDirection(x,y);
    			Direction opositeDirection = Direction.getOpositeDirection(baghDirection);

    			int dx = this.getPosition().getX() + opositeDirection.dx;
    			int dy = this.getPosition().getY() + opositeDirection.dy;

    			try {
    				return this.board.getSquaresOnBoard()[dx][dy].getIsAvailable();
    			}
    			catch (ArrayIndexOutOfBoundsException e){
    			}
			}
		}
        return false;
	}

	public ArrayList<Square> getAnglesOfAttack() {
		ArrayList<Square> anglesOfAttack = new ArrayList<Square>();

		Square[][] squaresOnBoard = this.board.getSquaresOnBoard();
		Square associatedSquare = squaresOnBoard[this.getPosition().getX()][this.getPosition().getY()];
		
		int squareAroundIndex = 0;
		ArrayList<Square> squaresAround = this.squaresAround(1);
		Square[][] squaresAroundTable = new Square[3][3];
		for(int i = 0; i <= 2; i++) {
		    for(int j = 0; j <= 2; j++) {
		    	if (squareAroundIndex >= squaresAround.size()) {
		    		squaresAroundTable[i][j] = null;
		    	}
		    	else {
		    		int relativeX = squaresAround.get(squareAroundIndex).getPosition().getX() - this.getPosition().getX();
			    	int relativeY = squaresAround.get(squareAroundIndex).getPosition().getY() - this.getPosition().getY();
			    	if ((relativeX == j - 1) && (relativeY == i - 1)) {
			    		squaresAroundTable[i][j] = squaresAround.get(squareAroundIndex);
				        squareAroundIndex++;
			    	}
			    	else {
			    		squaresAroundTable[i][j] = null;
			    	}
		    	}
		    	
		    }
		}
		
		int squareIndex = 0;
		for (int x = 0; x <= 2; x++) {
			for (int y = 0; y <=2; y++) {
				if (squareIndex < 4) {
					Square currentSquare = squaresAroundTable[x][y];
					int oppositex = x == 0 ? 2 : x == 2 ? 0 : 1;
					int oppositey = y == 0 ? 2 : y == 2 ? 0 : 1;
					Square oppositeSquare = squaresAroundTable[oppositex][oppositey];
					if (!(currentSquare == null) && currentSquare.getIsAvailable()) {
						if (!(oppositeSquare == null) && currentSquare.getIsAvailable()) {
							anglesOfAttack.add(currentSquare);
							anglesOfAttack.add(oppositeSquare);
						}
					}
				}
				squareIndex++;
			}
		}		
		return anglesOfAttack;
	}

	@Override
	public List<Move> selectedPawnEveryPossibleMoves() {
		List<Move> possibleMoves = new ArrayList<Move>();
		possibleMoves.addAll(possibleMoves());
		return possibleMoves;
	}

	public static List<Move> everyChalPossibleMoves(Board board) {
		List<Move> moves = new ArrayList<Move>();
		if(board.getNbChalsToPlace() > 0) {
	        Set<Square> squaresOnMap = board.getPawnsMap().keySet();

	        for (Square currentSquare : squaresOnMap) {
	        	if (currentSquare.getIsAvailable())
	        		moves.add(new Move(currentSquare.getPosition(), board));
	        }
		}
		else {
			ArrayList<ChalPawn> chals = board.getChalsOnBoard();
			for (ChalPawn chal : chals) {
				moves.addAll(chal.selectedPawnEveryPossibleMoves());
			}
		}
		return moves;
	}

//	public static void main(String[] args) {
//		Board board = new Board();
//		ChalPawn testChal = new ChalPawn(1,1,board);
//		ArrayList<Square> anglesOfAttack = testChal.getAnglesOfAttack();
//		Iterator iterator = anglesOfAttack.iterator();
//		int index = 0;
//		while (iterator.hasNext() && index < anglesOfAttack.size()) {
//			Square square = anglesOfAttack.get(index);
//			System.out.println(square.toString());
//			index++;
//		}
//	}
}
