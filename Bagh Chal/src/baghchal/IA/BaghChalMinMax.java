package baghchal.IA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import baghchal.AbstractPawn;
import baghchal.BaghPawn;
import baghchal.Board;
import baghchal.ChalPawn;
import baghchal.ImpossibleMoveException;
import baghchal.Move;
import baghchal.Square;

public class BaghChalMinMax {
    private static final int MAX_TURN = 1;
    private static final int MIN_TURN = -1;

    public static final int UNLIMITED_SEARCH_DEPTH = -1;
    public static final int MINI_HAS_WON = Integer.MAX_VALUE;
    public static final int STALE_MATE = 0;
    public static final int MAX_HAS_WON = Integer.MIN_VALUE;

    private int player = BaghChalMinMax.MAX_TURN; // Must always be 1 or -1
    private boolean isChal;
    
    private Board board;
    private ArrayList<ChalPawn> chalsOnBoard;
	private BaghPawn[] baghsOnBoard;
	private HashMap<Square, AbstractPawn> pawnsMap;
    private int nbChalsToPlace;
    
    public BaghChalMinMax(Board board, boolean isChal) {
    	this.isChal = isChal;
    	this.board = board;
    	this.pawnsMap = board.getPawnsMap();
        this.chalsOnBoard = board.getChalsOnBoard();
        this.baghsOnBoard = board.getBaghsOnBoard();
        this.nbChalsToPlace = board.getNbChalsToPlace();
    }
    
    public final boolean isMinTurn() {
        return player == BaghChalMinMax.MIN_TURN;
    }

    public Move pickPerfectMove(int maxSearchDepth) throws ImpossibleMoveException {
        if (maxSearchDepth <= 0) {
            throw new ImpossibleMoveException();
        }

        List<Move> moves = listAllLegalMoves();
        System.out.println(moves);
        if (moves.isEmpty()) {
            throw new ImpossibleMoveException();
        } else if (moves.size() == 1) {
            return moves.get(0);
        }

        int bestScore = player == BaghChalMinMax.MAX_TURN ? BaghChalMinMax.MINI_HAS_WON : BaghChalMinMax.MAX_HAS_WON;
        Move bestMove = null;

        for (Move move : moves) {
        	BaghChalMinMax tempBoard = this.clone();
            int score =
                    tempBoard.evaluate(maxSearchDepth == BaghChalMinMax.UNLIMITED_SEARCH_DEPTH ? BaghChalMinMax.UNLIMITED_SEARCH_DEPTH : maxSearchDepth - 1,
                            new AlphaBeta());
            if (score * player < bestScore || bestMove == null) {
                bestScore = score * player;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private int evaluate(int maxSearchDepth, AlphaBeta alphaBeta) {
        int currentScore = getCurrentScore();
        if (currentScore == BaghChalMinMax.MINI_HAS_WON || currentScore == BaghChalMinMax.MAX_HAS_WON) {
            return currentScore;
        }
        List<Move> moves = listAllLegalMoves();
        if (moves.isEmpty()) {
            return BaghChalMinMax.STALE_MATE;
        }
        int bestScore = 0;
        for (Move move : moves) {
        	BaghChalMinMax tempBoard = this.clone();
            tempBoard.doMove(move);
            int score;
            if (maxSearchDepth == 0) {
                score = tempBoard.getCurrentScore();
            } else {
                score =
                        tempBoard.evaluate(maxSearchDepth == BaghChalMinMax.UNLIMITED_SEARCH_DEPTH ? BaghChalMinMax.UNLIMITED_SEARCH_DEPTH : maxSearchDepth - 1,
                                alphaBeta);

                // Alpha-beta pruning
                if (player != BaghChalMinMax.MIN_TURN) {
                    if (score < alphaBeta.alpha) {
                        return score;
                    } else if (score < alphaBeta.beta) {
                        alphaBeta.beta = score;
                    }
                } else {
                    if (score > alphaBeta.beta) {
                        return score;
                    } else if (score > alphaBeta.alpha) {
                        alphaBeta.alpha = score;
                    }
                }
            }
            if (score == BaghChalMinMax.MINI_HAS_WON && player == -1) {
                return BaghChalMinMax.MINI_HAS_WON;
            } else if (score == BaghChalMinMax.MAX_HAS_WON && player == 1) {
                return BaghChalMinMax.MAX_HAS_WON;
            }
            if (score * player > bestScore) {
                bestScore = score * player;
            }
        }
        return bestScore;
    }

    public BaghChalMinMax clone() {
    	return new BaghChalMinMax(new Board(this.board), this.isChal);
    }

    /**************************************************/
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
    /**************************************************/
    
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
    /**************************************************/
    
    public void moveAction(Move move) {
		boolean isPuttingNewChal = move.getStart() == move.getFinish();
		
        if (isPuttingNewChal) this.board.addChal(move.getStart());
        else move.doMove();
    }

    public final void doMove(Move move) {
        moveAction(move);
        player *= -1;
    }

    private class AlphaBeta {
        int alpha = BaghChalMinMax.MINI_HAS_WON;
        int beta = BaghChalMinMax.MAX_HAS_WON;
    }
}
