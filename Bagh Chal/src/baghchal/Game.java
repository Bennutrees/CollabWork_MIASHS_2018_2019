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

		this.affichage();

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
		else{
			System.out.println("win");
			this.gt.endGame();
		}


	}

	private boolean haveWinner(){
		if(this.board.getNbBaghsFree() != 0 && this.board.getNbEatenChals() != 5){
			return true;
		}
		return false;
	}

	private void affichage() {
		System.out.println("_______________");
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				String p = " ";
				if(this.board.getSquaresOnBoard()[i][j].isAvailable() == false){
					p = this.board.getSquaresOnBoard()[i][j].getPawn().toString();
				}
				System.out.print("|"+p);
			}
			System.out.println("|");
			System.out.println("_______________");
		}
	}


	private void chalPlayerTurn() {
		if(this.board.getNbChalsToPlace() > 0)
			this.gt.chalPlayerPlacement(e -> changePlayer());
		else
			this.gt.chalPlayerTurnSelect(e -> chalPlayerTurnMove());
	}

	private void chalPlayerTurnMove() {
		this.gt.chalPlayerMove(e -> changePlayer());
	}

	private void baghPlayerTurn() {
		this.gt.baghPlayerSelect(e -> baghPlayerTurnMove());
	}

	private void baghPlayerTurnMove() {
		this.gt.baghPlayerMove(e -> changePlayer(), e ->baghPlayerTurnMove());
	}

	private void changePlayer() {
		if(this.currentPlayer)
			this.currentPlayer = false;
		else
			this.currentPlayer = true;
		this.play();
	}

}
