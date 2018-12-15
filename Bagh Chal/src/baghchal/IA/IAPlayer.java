package baghchal.IA;

import java.util.List;

import baghchal.Board;
import baghchal.Move;

public abstract class IAPlayer {
	
	protected Board board;
	
	IAPlayer(Board board) {
		this.board = board;
	}
	
	public abstract Move iaAction();
	protected abstract Move randomMoves();
	protected abstract List<Move> everyPossibleMoves();

}
