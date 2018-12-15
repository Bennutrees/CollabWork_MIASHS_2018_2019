package baghchal;

import baghchal.IA.*;
import baghchal.UI.GameTable;

public class Game {
	public final static int NO_IA = 0;
	public final static int BAGH_IA = -1;
	public final static int CHAL_IA = 1;
	public final static int BOTH_IA = 2;

	private Board board;
	private GameTable gameTable;
	private boolean currentPlayer = true; //True = Chals Player & False = Bash Player
	private int iaRole ;
	private IAPlayer iaPlayer;
	private IAPlayer secondIAPlayer;

	//Constructor
	public Game(GameTable game, Board board) {
		this.board = board;
		this.gameTable = game;
		this.iaRole = NO_IA;
	}

	public Game(GameTable game, int iaRole, Board board) {
		this.board = board;
		this.gameTable = game;
		if(iaRole >= CHAL_IA && iaRole <= BAGH_IA) {
			throw new IllegalArgumentException("Not a correct value for IA");
		}
		this.iaRole  = iaRole;

		if(this.iaRole == BAGH_IA) {
			this.iaPlayer = new BaghIA(this.board);
		}
		else if(this.iaRole == CHAL_IA) {
			this.iaPlayer = new ChalIA(this.board);
		}
		else {
			this.iaPlayer = new ChalIA(this.board);
			this.secondIAPlayer = new BaghIA(this.board);
		}	
	}

	//Methods
	public void play(){
//		this.affichage();

		if(!this.haveWinner()) {

			switch(this.iaRole) {
				case NO_IA:
					if(this.currentPlayer) {
						System.out.println("Chal Turn");
						this.chalPlayerTurn();
					}
					else {
						System.out.println("Bhag Turn");
						this.baghPlayerTurn();
					}
					break;
				case BAGH_IA:
					if(this.currentPlayer) {
						System.out.println("Chal Turn");
						this.chalPlayerTurn();
					}
					else {
						System.out.println("Bhag Turn");
						this.baghIATurn();
					}
					break;
				case CHAL_IA:
					if(this.currentPlayer) {
						System.out.println("Chal Turn");
						this.chalIATurn();
					}
					else {
						System.out.println("Bhag Turn");
						this.baghPlayerTurn();
					}
					break;
				case BOTH_IA:
					if(this.currentPlayer) {
						System.out.println("Chal Turn");
						this.chalIATurn();
					}
					else {
						System.out.println("Bhag Turn");
						this.secondIAPlayerTurn();
					}
					break;
				default:
					throw new IllegalArgumentException("Not a correct value for IA");
			}

		}
		else{
			if(this.currentPlayer) 
				System.out.println("Baghs win");
			else
				System.out.println("Chals win");
			this.gameTable.endGame();
			
		}

	}

	/****************************************************************************************************/
	/***************************************** private methodes *****************************************/
	/****************************************************************************************************/

	private boolean haveWinner(){
		this.board.calculateNbFreeBaghs();
		if(this.board.getNbFreeBaghs() == 0 || this.board.getNbEatenChals() == 5){
			return true;
		}
		return false;
	}

	private void changePlayer() {
		if(this.currentPlayer)
			this.currentPlayer = false;
		else
			this.currentPlayer = true;
		this.play();
	}

	/****************************************************************************************************/
	/******************************************* Human player *******************************************/
	/****************************************************************************************************/

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


	/****************************************************************************************************/
	/********************************************* IA player ********************************************/
	/****************************************************************************************************/

	/** Chal placement turns */
	private void chalIATurn() {
		Move move = this.iaPlayer.iaAction();
		this.gameTable.drawMoves(move, true);
		move.doMove();
		this.changePlayer();
	}

	/** Bagh turns */
	private void baghIATurn() {
		Move move = this.iaPlayer.iaAction();
		this.gameTable.drawMoves(move, false);
		Coordinates eatenChal = move.doMove();
		if(eatenChal != null) {
			this.gameTable.removeDraw(eatenChal);
		}
		this.changePlayer();
	}
	
	private void secondIAPlayerTurn() {
		Move move = this.secondIAPlayer.iaAction();
		this.gameTable.drawMoves(move, false);
		Coordinates eatenChal = move.doMove();
		if(eatenChal != null) {
			this.gameTable.removeDraw(eatenChal);
		}
		this.changePlayer();
	}

}
