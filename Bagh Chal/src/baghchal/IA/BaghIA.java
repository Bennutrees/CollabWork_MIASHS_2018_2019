package baghchal.IA;

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
		BaghChalMinMax minmaxIA = new BaghChalMinMax(new Board(this.board));
		Move move = this.randomEatingMove();
		if(move == null) {
			move = minmaxIA.pickPerfectMove(10);
//			move = this.randomMoves();
			move = new Move(move.getStart(), move.getFinish(), this.board);
		}
		return move;
	}

	@Override
	protected Move randomMoves() {
		BaghPawn[] baghs = this.board.getBaghsOnBoard();
		int random = (int)(Math.random() * 3);
		BaghPawn bagh = baghs[random];
		List<Move> moves = bagh.possibleMoves();

		while(moves.isEmpty()) {
			random = (int)(Math.random() * 3);
			bagh = baghs[random];
			moves = bagh.allPossibleMoves();
		}

		random = (int)(Math.random() * moves.size()-1);

		return moves.get(random);
	}
	
	private Move randomEatingMove() {
		BaghPawn[] baghs = this.board.getBaghsOnBoard();

		int rand = (int)(Math.random() * 3);
		for(int i=rand; i<4+rand; i++) {
			List<Move> eatMoves = baghs[i%4].possibleEatMoves();
			if(!eatMoves.isEmpty()) {
				rand = (int)(Math.random() * (eatMoves.size()-1));
				return eatMoves.get(rand);
			}
		}
		return null;
	}
	
}
