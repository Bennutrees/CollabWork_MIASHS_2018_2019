package baghchal;

import baghchal.UI.GameTable;
import baghchal.UI.MyPane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

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
		if(this.haveWinner()) {
			if(this.currentPlayer) {
				chalPlayerTurn();
				System.out.println("Chal Turn");
			}
			else {
				baghPlayerTurn();
				System.out.println("Bhag Turn");
			}

		}
		else
			System.out.println("win");
	}

	private boolean haveWinner(){
		if(this.board.getNbBaghsFree() != 0 || this.board.getNbEatenChals() != 5){
			return true;
		}
		return false;
	}


	private void chalPlayerTurn() {
		if(this.board.getNbChalsToPlace() > 0) {
			this.gt.chalPlayerPlacement(e -> endChalPlayerTurn());
		}
	}

	private void endChalPlayerTurn() {
		if(this.board.getNbChalsToPlace() > 0) {
			this.board.onMoreChalInGame();
		}
		this.changePlayer();
	}


	private void baghPlayerTurn() {
		this.gt.baghPlayerSelect(e -> baghPlayerTurnMove());
	}

	private void baghPlayerTurnMove() {
		this.gt.baghPlayerMove(e -> endBaghPlayerTurn());
	}

	private void endBaghPlayerTurn() {
		this.changePlayer();
	}

	private void changePlayer() {
		if(this.currentPlayer)
			this.currentPlayer = false;
		else
			this.currentPlayer = true;
		this.play();
	}

}
