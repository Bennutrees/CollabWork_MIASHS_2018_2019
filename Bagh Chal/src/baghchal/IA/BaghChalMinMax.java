package baghchal.IA;

import java.util.*;
import java.util.Map.Entry;

import baghchal.*;

public class BaghChalMinMax extends MinMax<Move> {

	private ArrayList<ChalPawn> chalsOnBoard;
	private BaghPawn[] baghsOnBoard;
	private HashMap<Square, AbstractPawn> pawnsMap;
    private int nbChalsToPlace;
	private Board board;

    public BaghChalMinMax(Board board) {
    	this.board = board;
        this.pawnsMap = board.getPawnsMap();
        this.chalsOnBoard = board.getChalsOnBoard();
        this.baghsOnBoard = board.getBaghsOnBoard();
        this.nbChalsToPlace = board.getNbChalsToPlace();
        
//        System.out.println(board + " / " + Board.getBoard());
//        System.out.println(chalsOnBoard + " / " + Board.getBoard().getChalsOnBoard());
//        System.out.println(baghsOnBoard + " / " + Board.getBoard().getBaghsOnBoard());
//        nbChalsToPlace--;
//        System.out.println(nbChalsToPlace + " / " + Board.getBoard().getNbChalsToPlace());
    }

    
    @Override
    public int getCurrentScore() {
        return -((5 * getNbChalsOnBoard()) + getNbVulnerableChals());
    }
    
    private int getNbChalsOnBoard() {
        return chalsOnBoard.size();
    }

    private int getNbVulnerableChals() {
        int nbVulnerableChals = 0;        
        for (BaghPawn baghPawn : baghsOnBoard) {
        	nbVulnerableChals += baghPawn.possibleEatMoves().size();
        }
        return nbVulnerableChals;
    }

    
    @Override
    public List<Move> listAllLegalMoves() {
        List<Move> moves = isMinTurn() ? listAllChalMoves() : listAllBaghMoves();
        System.out.println("moves : " + moves);
        Collections.shuffle(moves);
        return moves;
    }

    private List<Move> listAllChalMoves() {
        return nbChalsToPlace < 20 ? listAllChalPhase1Moves() : listAllChalPhase2Moves();
    }

    private List<Move> listAllChalPhase1Moves() {
        List<Move> phase1ChalsMoves = new ArrayList<Move>();
        Set<Square> squaresOnMap = pawnsMap.keySet();
        
        for (Square actualSquare : squaresOnMap) {
        	if (actualSquare.getIsAvailable()) phase1ChalsMoves.add(new Move(actualSquare.getPosition(), this.board));
        }
        return phase1ChalsMoves;
    }

    private List<Move> listAllChalPhase2Moves() {
        List<Move> phase2ChalsMoves = new ArrayList<Move>();
        Set<Square> squaresOnMap = pawnsMap.keySet();
        
        for (Square actualSquare : squaresOnMap) {
        	AbstractPawn actualPawn = pawnsMap.get(actualSquare);
        	if (actualPawn instanceof ChalPawn) {
        		phase2ChalsMoves.addAll(actualPawn.allPossibleMoves());
        	}
        }
        return phase2ChalsMoves;
    }

    private List<Move> listAllBaghMoves() {
        List<Move> baghMoves = new ArrayList<Move>();
        Set<Square> squaresOnMap = pawnsMap.keySet();
        
        for (Square actualSquare : squaresOnMap) {
        	AbstractPawn actualPawn = pawnsMap.get(actualSquare);
        	if (actualPawn instanceof BaghPawn) {
        		baghMoves.addAll(actualPawn.allPossibleMoves());
        	}
        }
        return baghMoves;
    }

    
    @Override
    public void moveAction(Move move) {
		boolean isPuttingNewChal = move.getStart() == move.getFinish();
		
        if (isPuttingNewChal) this.board.addChal(move.getStart());
        else move.doMove();
        this.board.affichage();
        for (BaghPawn baghPawn : baghsOnBoard) {
            System.out.println(baghPawn.getPosition().getX() + " : " + baghPawn.getPosition().getY());
		}
    }
}
