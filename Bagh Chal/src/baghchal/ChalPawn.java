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
		int squaresArroundIndex = associatedSquare.getHasNorthWest() ? 0 :
			associatedSquare.getHasNorth() ? 1 :
			associatedSquare.getHasNorthEast() ? 2 :
			associatedSquare.getHasWest() ? 3 : 4;

		System.out.println(squaresArroundIndex);

		ArrayList<Square> squaresAround = this.squaresAround(1);
		Iterator<Square> squareIterator = squaresAround.iterator();
		System.out.println("Dans getAnglesOfAttack : ");
		while (squareIterator.hasNext() && squaresArroundIndex < 4) {
			Square currentSquare = squaresAround.get(squaresArroundIndex);
			boolean oppositeSquareExist = squaresAround.get(7 - squaresArroundIndex) instanceof Square;

			if (oppositeSquareExist) {
				Square oppositeSquare = squaresAround.get(7 - squaresArroundIndex);
				if (currentSquare.getIsAvailable() && oppositeSquare.getIsAvailable()) {
					anglesOfAttack.add(squaresArroundIndex,currentSquare);
					System.out.println(currentSquare.toString());
					anglesOfAttack.add(7 - squaresArroundIndex, oppositeSquare);
					System.out.println(oppositeSquare.toString());
				}
			}
			squaresArroundIndex++;
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

	public static void main(String[] args) {
		Board board = new Board();
		ChalPawn testChal = new ChalPawn(1,1,board);
		ArrayList<Square> anglesOfAttack = testChal.getAnglesOfAttack();
		Iterator iterator = anglesOfAttack.iterator();
		int index = 0;
		while (iterator.hasNext() && index < anglesOfAttack.size()) {
			Square square = anglesOfAttack.get(index);
			System.out.println(square.toString());
			index++;
		}
	}
}
