package baghchal.IA;

import java.util.ArrayList;
import java.util.List;
import baghchal.BaghPawn;
import baghchal.Board;
import baghchal.Move;

public class BaghIA extends IAPlayer{
	
	public BaghIA(Board board) {
		super(board);
	}

	@Override
	public Move iaAction() {
		BaghChalMinMax minmaxIA = new BaghChalMinMax(new Board(this.board), BaghChalMinMax.BAGH_TURN);
		Move move = this.randomEatingMove();
		if(move == null) {
			move = minmaxIA.pickPerfectMove(100);
//			move = this.randomMoves();
			move = new Move(move.getStart(), move.getFinish(), this.board);
		}
		System.out.println("Selected move : " + move);
		return move;
	}

	/****************************************************************************************************/
	/********************************************* Heritage *********************************************/
	/****************************************************************************************************/
	@Override
	protected Move randomMoves() {
		List<Move> moves = BaghPawn.allPossibleBaghsMoves(this.board);
		if(!moves.isEmpty()) {
			int rand = (int)(Math.random() * moves.size());
			return moves.get(rand);
		}
		return null;
	}
	
	
	/****************************************************************************************************/
	/******************************************* Random eating ******************************************/
	/****************************************************************************************************/
	private Move randomEatingMove() {
		List<Move> moves = this.allEatingMoves();
		if(!moves.isEmpty()) {
			int rand = (int)(Math.random() * moves.size());
			return moves.get(rand);
		}
		return null;
	}
	
	private List<Move> allEatingMoves() {
		BaghPawn[] baghs = this.board.getBaghsOnBoard();
		
		List<Move> eatMoves = new ArrayList<Move>();
		for(int i=0; i<4; i++) {
			eatMoves.addAll(baghs[i].possibleEatMoves());
		}
		return eatMoves;
	}
//
//	public List<Move> allSimpleMoves() {
//		BaghPawn[] baghs = this.board.getBaghsOnBoard();
//		
//		List<Move> eatMoves = new ArrayList<Move>();
//		for(int i=0; i<4; i++) {
//			eatMoves.addAll(baghs[i].possibleMoves());
//		}
//		return eatMoves;
//	}

	
}
