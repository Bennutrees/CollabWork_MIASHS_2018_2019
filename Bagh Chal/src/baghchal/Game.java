package baghchal;

import java.io.IOException;

import baghchal.IA.*;
import baghchal.UI.GameTable;
import baghchal.UI.Menu;

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
	private Menu menu;

	//Constructor
	public Game(GameTable game, Board board, Menu menu) {
		this.board = board;
		this.gameTable = game;
		this.iaRole = NO_IA;
		this.menu = menu;
	}

	public Game(GameTable game, int iaRole, Board board, Menu menu) {
		this.board = board;
		this.gameTable = game;
		this.menu = menu;
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
	public void play() throws IOException{
//		this.affichage();

		if(!this.haveWinner()) {

			switch(this.iaRole) {
				case NO_IA:
					if(this.currentPlayer) {
						this.chalPlayerTurn();
					}
					else {
						this.baghPlayerTurn();
					}
					break;
				case BAGH_IA:
					if(this.currentPlayer) {
						this.chalPlayerTurn();
					}
					else {
						this.baghIATurn();
					}
					break;
				case CHAL_IA:
					if(this.currentPlayer) {
						this.chalIATurn();
					}
					else {
						this.baghPlayerTurn();
					}
					break;
				case BOTH_IA:
					if(this.currentPlayer) {
						this.chalIATurn();
					}
					else {
						this.secondIAPlayerTurn();
					}
					break;
				default:
					throw new IllegalArgumentException("Not a correct value for IA");
			}

		}
		else{
			if(this.currentPlayer) 
				this.menu.BackToMenu("Baghs win");
			else
				this.menu.BackToMenu("Chals win");
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

	private void changePlayer() throws IOException {
		if(this.currentPlayer) {
			this.currentPlayer = false;
//			System.out.println("Bhag Turn");
		}
		else {
			this.currentPlayer = true;
//			System.out.println("Chal Turn");
		}
		this.play();
	}

	/****************************************************************************************************/
	/******************************************* Human player *******************************************/
	/****************************************************************************************************/

	private void chalPlayerTurn() {
		if(this.board.getNbChalsToPlace() > 0)
			this.gameTable.chalPlayerPlacement(e -> {
				try {
					changePlayer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		else
			this.gameTable.chalPlayerTurnSelect(e -> chalPlayerTurnMove());
	}

	private void chalPlayerTurnMove() {
		this.gameTable.chalPlayerMove(e -> {
			try {
				changePlayer();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

	private void baghPlayerTurn() {
		this.gameTable.baghPlayerSelect(e -> baghPlayerTurnMove());
	}

	private void baghPlayerTurnMove() {
		this.gameTable.baghPlayerMove(e -> {
			try {
				changePlayer();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}, e -> baghPlayerTurnMove());
	}


	/****************************************************************************************************/
	/********************************************* IA player ********************************************/
	/****************************************************************************************************/

	/** Chal placement turns 
	 * @throws IOException */
	private void chalIATurn() throws IOException {
		Move move = this.iaPlayer.iaAction();
		this.gameTable.drawMoves(move, true);
		move.doMove();
		this.changePlayer();
	}

	/** Bagh turns 
	 * @throws IOException */
	private void baghIATurn() throws IOException {
		Move move = this.iaPlayer.iaAction();
		this.gameTable.drawMoves(move, false);
		Coordinates eatenChal = move.doMove();
		if(eatenChal != null) {
			this.gameTable.removeDraw(eatenChal);
		}
		this.changePlayer();
	}
	
	private void secondIAPlayerTurn() throws IOException {
		Move move = this.secondIAPlayer.iaAction();
		this.gameTable.drawMoves(move, false);
		Coordinates eatenChal = move.doMove();
		if(eatenChal != null) {
			this.gameTable.removeDraw(eatenChal);
		}
		this.changePlayer();
	}

}
