package baghchal;

import baghchal.UI.GameTable;

public class Game {
	private Board board;
	private GameTable gt;
	private boolean currentPlayer; //True = Chals Player & False = Bash Player

	public Game(Board board, GameTable gameTable) {
		this.board = board;
		this.gt = gameTable;
		this.currentPlayer = true;
	}

	public void play(){
		if(this.currentPlayer)
			chalPlayerTurn();
		else
			baghPlayerTurn();
	}

	private boolean haveWinner(){
		if(this.board.getNbBaghsFree() != 0 || this.board.getNbEatenChals() != 5){
			return true;
		}
		return false;
	}

	private void chalPlayerTurn() {
		if(this.board.getNbChalsToPlace() > 0) {
			gt.chalPlayerPlacement();
		}
	}

	private void baghPlayerTurn(){

	}

}
