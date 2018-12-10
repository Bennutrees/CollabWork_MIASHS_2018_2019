package baghchal;

import java.util.concurrent.TimeUnit;

import baghchal.IA.*;
import baghchal.UI.GameTable;

public class Game {
	public final static int NO_IA = 0;
	public final static int BAGH_IA = -1;
	public final static int CHAL_IA = 1;
	public final static int BOTH_IA = 2;

	private Board board = Board.getBoard();
	private GameTable gameTable;
	private boolean currentPlayer = true; //True = Chals Player & False = Bash Player
	private int iaRole ;
	private IAPlayer iaPlayer;
	private IAPlayer secondIAPlayer;

	//Constructor
	public Game(GameTable game) {
		this.gameTable = game;
		this.iaRole = NO_IA;
	}

	public Game(GameTable game, int iaRole) {
		this.gameTable = game;
		if(iaRole >= CHAL_IA && iaRole <= BAGH_IA) {
			throw new IllegalArgumentException("Not a correct value for IA");
		}
		this.iaRole  = iaRole;

		if(this.iaRole == BAGH_IA) {
			this.iaPlayer = new BaghIA();
		}
		if(this.iaRole == CHAL_IA) {
			this.iaPlayer = new ChalIA();
		}
		else {
			this.iaPlayer = new ChalIA();
			this.secondIAPlayer = new BaghIA();
		}
		
	}

	//Methods
	public void play(){
		this.affichage();

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
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				default:
					throw new IllegalArgumentException("Not a correct value for IA");
			}

		}
		else{
			System.out.println("win");
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
//				System.out.print("|"+thisSquare.getPosition().getX()+","+thisSquare.getPosition().getY());
			}
			System.out.println("|");
			System.out.println("_______________");
		}
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
		if(this.board.getNbChalsToPlace() == 0) {
			this.chalIATurnMove();
		}
		else {
			Move move = this.iaPlayer.iaAction();
			this.board.addChal(move.getFinish());
			this.gameTable.drawMoves(move, true);
		}
		this.changePlayer();
	}

	/** Chal movement turns */
	private void chalIATurnMove() {
		Move move = this.iaPlayer.iaAction();
		this.gameTable.drawMoves(move, true);
		move.doMove();
	}

	/** Bagh turns */
	private void baghIATurn() {
		Move move = this.iaPlayer.iaAction();
		System.out.println(move);
		this.gameTable.drawMoves(move, false);
		Coordinates eatenChal = move.doMove();
		if(eatenChal != null) {
			this.gameTable.removeDraw(eatenChal);
		}
		this.changePlayer();
		
		
//		Coordinates[] movement = this.iaPlayer.iaAction();
//		this.gameTable.drawMoves(movement, false);
//		Move mv = new Move(movement[0], movement[1]);
//		Coordinates eatenChal = mv.doMove();
//		if(eatenChal != null) {
//			this.gameTable.removeDraw(eatenChal);
//		}
//
//		this.gameTable.drawMoves(movement, false);
//
//		this.changePlayer();
	}
	
	private void secondIAPlayerTurn() {
		Move move = this.secondIAPlayer.iaAction();
		this.gameTable.drawMoves(move, false);
		Coordinates eatenChal = move.doMove();
		if(eatenChal != null) {
			this.gameTable.removeDraw(eatenChal);
		}
		this.changePlayer();
		
//		Coordinates[] movement = this.secondIAPlayer.iaAction();
//		this.gameTable.drawMoves(movement, false);
//		Move mv = new Move(movement[0], movement[1]);
//		Coordinates eatenChal = mv.doMove();
//		if(eatenChal != null) {
//			this.gameTable.removeDraw(eatenChal);
//		}
//
//		this.gameTable.drawMoves(movement, false);
//
//		this.changePlayer();
	}

}
