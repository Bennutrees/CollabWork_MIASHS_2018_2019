package baghchal.IA;

import java.util.*;
import baghchal.*;

public class BaghChalMinMax {

    public static final int BAGH_TURN = 1;
    public static final int CHAL_TURN = -1;
    private int player;

    public static final int CHAL_VICTORY = Integer.MAX_VALUE;
    public static final int STALEMATE = 0;
    public static final int BAGH_VICTORY = Integer.MIN_VALUE;

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

    public Move selectBestMove(int maxSearchDepth, int IA_level) throws ImpossibleMoveException {
        if (maxSearchDepth <= 0) {
            throw new ImpossibleMoveException();
        }

        List<Move> moves = everyPawnPossibleMoves();
        if (moves.isEmpty()) {
            throw new ImpossibleMoveException();
        }
        else if (moves.size() == 1) {
            return moves.get(0);
        }

        int bestScore = player == BaghChalMinMax.BAGH_TURN ? BaghChalMinMax.CHAL_VICTORY : BaghChalMinMax.BAGH_VICTORY;
        Move bestMove = null;

        for (Move move : moves) {
            BaghChalMinMax temporaryBoard = this.clone();
            int score = temporaryBoard.simulateMove(maxSearchDepth - 1, IA_level, new AlphaBeta());
            
            switch (player) {
            case BaghChalMinMax.BAGH_TURN :
            	if (score > bestScore || bestMove == null) {
            		bestScore = score;
            		bestMove = move;
            	}
            case BaghChalMinMax.CHAL_TURN :
            	if (score < bestScore || bestMove == null) {
            		bestScore = score;
            		bestMove = move;
            	}
            default :
            	// TODO Error handling
            	break;
            }
        }
        return bestMove;
    }

    private int simulateMove(int maxSearchDepth, int IA_level, AlphaBeta alphaBeta) {
        int currentScore = getSituationScore(IA_level);
        if (maxSearchDepth == 0 || currentScore == BaghChalMinMax.CHAL_VICTORY || currentScore == BaghChalMinMax.BAGH_VICTORY) {
            return currentScore;
        }
        List<Move> moves = everyPawnPossibleMoves();
        if (moves.isEmpty()) {
            return BaghChalMinMax.STALEMATE;
        }
        int bestScore = player == BaghChalMinMax.BAGH_TURN ? BaghChalMinMax.CHAL_VICTORY : BaghChalMinMax.BAGH_VICTORY;
        for (Move move : moves) {
            BaghChalMinMax temporaryBoard = this.clone();
            temporaryBoard.doMove(move);
            int score = temporaryBoard.simulateMove(maxSearchDepth - 1, IA_level, alphaBeta);

            switch(alphaBeta.pruning(score)) {
                case 1 :
                	return score;
                case 2 :
                	alphaBeta.beta = score;
                    break;
                case 3 :
                	alphaBeta.alpha = score;
                    break;
                default :
                	// TODO Error handling
                    break;
            }
            
            switch (player) {
            case BaghChalMinMax.BAGH_TURN :
            	if (score > bestScore) {
            		bestScore = score;
            	}
            	break;
            case BaghChalMinMax.CHAL_TURN :
            	if (score < bestScore) {
            		bestScore = score;
            	}
            	break;
            default :
            	// TODO Error handling
            	break;
            }
        }
        return bestScore;
    }
    
    public BaghChalMinMax clone() {
        return new BaghChalMinMax(new Board(this.board), this.player);
    }

    /**************************************************/
    public int getSituationScore(int IA_level) {
    	int score = getNbThreatenChals() - 5 * getNbChalsOnBoard();
    	switch (IA_level) {
    	case 0 :
    		return score;
    	case 1 :
    		return score + getGlobalVulnerabilityLevel();
    	case 2 :
    		return score + getGlobalVulnerabilityLevel() - getNbFreeBaghs();
    	default :
    		return score;
    	}
    }

    private int getNbChalsOnBoard() {
        return chalsOnBoard.size();
    }

    private int getNbThreatenChals() {
        int nbVulnerableChals = 0;
        for (ChalPawn chalPawn : chalsOnBoard) {
            nbVulnerableChals += chalPawn.isThreaten() ? 1 : 0;
        }
        return nbVulnerableChals;
    }
    
    private int getGlobalVulnerabilityLevel() {
        int VulnerabilityLevel = 0;
        for (ChalPawn chalPawn : chalsOnBoard) {
            VulnerabilityLevel += chalPawn.getVulnerabilityLevel();
        }
        return VulnerabilityLevel;
    }
    
    private int getNbFreeBaghs() {
    	int nbFreeBaghs = 4;
    	for (BaghPawn baghPawn : baghsOnBoard) {
			if (baghPawn.isBlocked())
				nbFreeBaghs--;
		}
		return nbFreeBaghs;
    }
    
    /**************************************************/

    public List<Move> everyPawnPossibleMoves() {
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
        int alpha = BaghChalMinMax.CHAL_VICTORY;
        int beta = BaghChalMinMax.BAGH_VICTORY;

        private int pruning(int score) {
	        if (player == BaghChalMinMax.BAGH_TURN) {
	            if (score < alpha) {
	                return 1;
	            }
	            else if (score < beta) {
	                return 2;
	            }
	        }
	        else {
	            if (score > beta) {
	                return 1;
	            }
	            else if (score > alpha) {
	                return 3;
	            }
	        }
			return score;
	    }
    }
}