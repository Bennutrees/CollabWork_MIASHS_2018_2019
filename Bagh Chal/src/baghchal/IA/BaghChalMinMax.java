package baghchal.IA;

import java.util.*;
import java.util.Map.Entry;

import baghchal.*;

public class BaghChalMinMax extends MinMax<Move> {

	private ArrayList<ChalPawn> chalsOnBoard;
	private BaghPawn[] baghsOnBoard;
	private HashMap<Square, AbstractPawn> pawnsMap;
    private int nbChalsToPlace;

    public BaghChalMinMax() {
        this.pawnsMap = Board.getBoard().getPawnsMap();
        this.chalsOnBoard = Board.getBoard().getChalsOnBoard();
        this.baghsOnBoard = Board.getBoard().getBaghsOnBoard();
        this.nbChalsToPlace = Board.getBoard().getNbChalsToPlace();
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
        	if (actualSquare.getIsAvailable()) phase1ChalsMoves.add(new Move(actualSquare.getPosition()));
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
        if (move.isUnusedGoatBeingDropped) {
            squareContents[move.dest.x][move.dest.y] = SquareContents.GOAT;
        } else {
            if (squareContents[move.src.x][move.src.y] == SquareContents.TIGER) {
                boolean isTakingMove = Math.abs(move.src.x - move.dest.x) > 1 || Math.abs(move.src.y - move.dest.y) > 1;
                if (isTakingMove) {
                    int takenGoatX = (move.src.x + move.dest.x) / 2;
                    int takenGoatY = (move.src.y + move.dest.y) / 2;
                    squareContents[takenGoatX][takenGoatY] = SquareContents.EMPTY;
                }
            }

            squareContents[move.dest.x][move.dest.y] = squareContents[move.src.x][move.src.y];
            squareContents[move.src.x][move.src.y] = SquareContents.EMPTY;
        }
    }
}
