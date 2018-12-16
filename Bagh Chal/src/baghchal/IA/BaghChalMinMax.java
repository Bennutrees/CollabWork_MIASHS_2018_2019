package baghchal.IA;

import java.util.*;

import baghchal.*;

public class BaghChalMinMax {
	
    public static final int BAGH_TURN = 1;
    public static final int CHAL_TURN = -1;

    public static final int UNLIMITED_SEARCH_DEPTH = -1;
    public static final int CHAL_HAS_WON = Integer.MAX_VALUE;
    public static final int STALE_MATE = 0;
    public static final int MAX_HAS_WON = Integer.MIN_VALUE;

    private int player;// = BaghChalMinMax.BAGH_TURN; // Must always be 1 or -1
    
    private Board board;
    private ArrayList<ChalPawn> chalsOnBoard;
	private BaghPawn[] baghsOnBoard;
    
    public BaghChalMinMax(Board board, int baghOrChal) {
    	this.player = baghOrChal;
    	this.board = board;
        this.chalsOnBoard = board.getChalsOnBoard();
        this.baghsOnBoard = board.getBaghsOnBoard();
    }
    
    public final boolean isMinTurn() {
        return player == BaghChalMinMax.CHAL_TURN;
    }

    public Move pickPerfectMove(int maxSearchDepth) throws ImpossibleMoveException {
        if (maxSearchDepth <= 0) {
            throw new ImpossibleMoveException();
        }

        List<Move> moves = listAllLegalMoves();
        if (moves.isEmpty()) {
            throw new ImpossibleMoveException();
        } else if (moves.size() == 1) {
            return moves.get(0);
        }

        int bestScore = player == BaghChalMinMax.BAGH_TURN ? BaghChalMinMax.CHAL_HAS_WON : BaghChalMinMax.MAX_HAS_WON;
        Move bestMove = null;

        for (Move move : moves) {
        	BaghChalMinMax tempBoard = this.clone();
            int score =
                    tempBoard.evaluate(maxSearchDepth == BaghChalMinMax.UNLIMITED_SEARCH_DEPTH ? BaghChalMinMax.UNLIMITED_SEARCH_DEPTH : maxSearchDepth - 1,
                            new AlphaBeta());
            if (score * player > bestScore || bestMove == null) {
                bestScore = score * player;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private int evaluate(int maxSearchDepth, AlphaBeta alphaBeta) {
        int currentScore = getCurrentScore();
        if (currentScore == BaghChalMinMax.CHAL_HAS_WON || currentScore == BaghChalMinMax.MAX_HAS_WON) {
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
                if (player == BaghChalMinMax.BAGH_TURN) {
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
            if (score == BaghChalMinMax.CHAL_HAS_WON && player == -1) {
                return BaghChalMinMax.CHAL_HAS_WON;
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
    	return new BaghChalMinMax(new Board(this.board), this.player);
    }

    /**************************************************/
	public int getCurrentScore() {
        return -(5 * getNbChalsOnBoard() + getNbVulnerableChals());
    }
	
    private int getNbChalsOnBoard() {
        return chalsOnBoard.size();
    }

    private int getNbVulnerableChals() {
        int nbVulnerableChals = 0;        
        for (ChalPawn chalPawn : chalsOnBoard) {
        	nbVulnerableChals += chalPawn.isVulnerable() ? 1 : 0;
        }
        return nbVulnerableChals;
    }
    /**************************************************/
    
    public List<Move> listAllLegalMoves() {
        List<Move> moves;
        if(isMinTurn())
        	moves = ChalPawn.everyChalPossibleMoves(board);
        else {
        	moves = BaghPawn.everyBaghPossibleMoves(board);
//            Collections.shuffle(moves);
        }
        return moves;
    }

    /**************************************************/
    
    public final void doMove(Move move) {
    	move.doMove();
        player *= -1;
    }

    private class AlphaBeta {
        int alpha = BaghChalMinMax.CHAL_HAS_WON;
        int beta = BaghChalMinMax.MAX_HAS_WON;
    }
}
