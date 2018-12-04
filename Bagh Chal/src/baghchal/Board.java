package baghchal;

import java.util.ArrayList;

public class Board {

	private static Board plateau = new Board();
	private Square[][] squaresOnBoard;
	private ArrayList<ChalPawn> chalsOnBoard;
	private BaghPawn[] baghsOnBoard;
	private int nbChalsToPlace;
	private int nbFreeBaghs;
	private int nbEatenChals;
	
	//Constructor
	private Board() {
		this.nbChalsToPlace = 20;
		this.nbFreeBaghs = 4;
		this.nbEatenChals = 0;
		
		this.squaresOnBoard = this.createSquaresMap(5);
		this.chalsOnBoard = new ArrayList<ChalPawn>();
		this.baghsOnBoard = new BaghPawn[nbFreeBaghs];
		this.initBaghs();
	}
	
	private Square[][] createSquaresMap(int size) {
		this.squaresOnBoard = new Square[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				this.squaresOnBoard[i][j] = new Square(i,j);
			}
		}
		return squaresOnBoard;
	}

	private void initBaghs() {
		int baghCoordinates[][] = { {0,0}, {4,0}, {0,4}, {4,4} };
		int row = 0, column = 1;
		for(int i=0; i<4; i++) {
			BaghPawn newBagh = new BaghPawn(baghCoordinates[i][row], baghCoordinates[i][column]);
			this.baghsOnBoard[i] = newBagh;
			this.squaresOnBoard[baghCoordinates[i][row]][baghCoordinates[i][column]].setPawn(newBagh);
		}
	}


	//Methods
	public static Board getBoard() {
		return plateau;
	}

	public Square[][] getSquaresOnBoard() {
		return this.squaresOnBoard;
	}

	public ArrayList<ChalPawn> getChalsOnBoard() {
		return this.chalsOnBoard;
	}

	public BaghPawn[] getBaghOnBoard() {
		return this.baghsOnBoard;
	}

	public int getNbEatenChals() {
		return this.nbEatenChals;
	}

	public int getNbChalsToPlace() {
		return nbChalsToPlace;
	}

	public int getNbBaghsFree() {
		return nbFreeBaghs;
	}

	public void addChal(Coordinates crd) {
    	ChalPawn cp = new ChalPawn(crd.getRow(), crd.getColumn());
		this.chalsOnBoard.add(cp);
		this.squaresOnBoard[crd.getRow()][crd.getColumn()].setPawn(cp);
		this.nbChalsToPlace--;
	}

	public void eatChal(Coordinates crd) {
		this.chalsOnBoard.remove(this.squaresOnBoard[crd.getRow()][crd.getColumn()].getPawn());
		this.squaresOnBoard[crd.getRow()][crd.getColumn()].setPawn(null);
		this.nbEatenChals++;
		System.out.println(this.nbEatenChals);
	}

}