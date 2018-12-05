package baghchal;

import baghchal.UI.GameTable;

public class Game {

	private Board board;
	private GameTable gameTable;
	private boolean currentPlayer; //True = Chals Player & False = Bash Player

	//Constructor
	public Game(Board board, GameTable gameTable) {
		this.board = board;
		this.gameTable = gameTable;
		this.currentPlayer = true;
	}


	//Methods
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
			this.gameTable.endGame();
		}


	}

	private boolean haveWinner(){
		if(this.board.getNbFreeBaghs() != 0 && this.board.getNbEatenChals() != 5){
			return true;
		}
		return false;
	}

	private void affichage() {
		System.out.println("_______________");
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				String p = " ";
				Square thisSquare = this.board.getSquaresOnBoard()[i][j];
				if(thisSquare.getIsAvailable() == false){
					p = this.board.getPawnsMap().get(thisSquare).toString();
				}
				System.out.print("|"+p);
			}
			System.out.println("|");
			System.out.println("_______________");
		}
	}


	private void chalPlayerTurn() {
		if(this.board.getNbChalsToPlace() > 0)
			this.gameTable.chalPlayerPlacement(e -> changePlayer());
		else
			this.gameTable.chalPlayerTurnSelect(e -> chalPlayerTurnMove());
	}

	private void chalPlayerTurnMove() {
		this.gameTable.chalPlayerMove(e -> changePlayer());
	}

	private void baghPlayerTurn() {
		this.gameTable.baghPlayerSelect(e -> baghPlayerTurnMove());
	}

	private void baghPlayerTurnMove() {
		this.gameTable.baghPlayerMove(e -> changePlayer(), e -> baghPlayerTurnMove());
	}

	private void changePlayer() {
		if(this.currentPlayer)
			this.currentPlayer = false;
		else
			this.currentPlayer = true;
		this.play();
	}

}
