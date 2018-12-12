package baghchal.IA;

import baghchal.Board;
import baghchal.Coordinates;
import baghchal.Move;

public abstract class IAPlayer {
	
	protected Board board;
	
	IAPlayer(Board board) {
		this.board = board;
	}
	
	public abstract Move iaAction();
	protected abstract Move randomMoves();

}
