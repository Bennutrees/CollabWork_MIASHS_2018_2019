package baghchal.IA;

import java.util.ArrayList;
import java.util.List;

import baghchal.BaghPawn;
import baghchal.Board;
import baghchal.Coordinates;
import baghchal.Move;

public class BaghIA extends IAPlayer{

	private Board board;

	public BaghIA() {
		this.board = Board.getBoard();
	}

	@Override
	public Move iaAction() {
		Move move = this.randomMoves();
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

}
