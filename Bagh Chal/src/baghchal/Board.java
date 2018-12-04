package baghchal;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {

	private static Board plateau = new Board();
	private Square[][] squaresOnBoard;
	private ArrayList<ChalPawn> chalsOnBoard;
	private BaghPawn[] baghsOnBoard;
	private HashMap<Square, AbstractPawn> pawnsMap;
	
	private int nbChalsToPlace;
	private int nbFreeBaghs;
	private int nbEatenChals;
	
	//Constructor
	private Board() {
		this.nbChalsToPlace = 20;
		this.nbFreeBaghs = 4;
		this.nbEatenChals = 0;

		this.pawnsMap = new HashMap<Square, AbstractPawn>();
		this.squaresOnBoard = this.createSquaresMap(5);
		this.chalsOnBoard = new ArrayList<ChalPawn>();
		this.baghsOnBoard = new BaghPawn[nbFreeBaghs];
		this.initBaghs();
	}
	
	private Square[][] createSquaresMap(int size) {
		this.squaresOnBoard = new Square[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				Square newSquare = new Square(i,j);
				this.squaresOnBoard[i][j] = newSquare;
				this.pawnsMap.put(newSquare, null);
			}
		}
		return squaresOnBoard;
	}

	private void initBaghs() {		
		int baghCoordinates[][] = { {0,0}, {4,0}, {0,4}, {4,4} };
		int row = 0, column = 1;
		for(int i=0; i<4; i++) {
			
			BaghPawn newBagh = new BaghPawn(baghCoordinates[i][row], baghCoordinates[i][column]);
			Square associatedSquare = this.squaresOnBoard[baghCoordinates[i][row]][baghCoordinates[i][column]];
			
			this.baghsOnBoard[i] = newBagh;
			this.pawnsMap.put(associatedSquare, newBagh);
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

	public int getNbFreeBaghs() {
		return nbFreeBaghs;
	}

	public void addChal(Coordinates position) {
		ChalPawn newChal = new ChalPawn(position.getRow(), position.getColumn());
		Square associatedSquare = this.squaresOnBoard[position.getRow()][position.getColumn()];
		
		this.chalsOnBoard.add(newChal);
		this.pawnsMap.put(associatedSquare, newChal);
		this.nbChalsToPlace--;
	}

	public void eatChal(Coordinates position) {
		Square associatedSquare = this.squaresOnBoard[position.getRow()][position.getColumn()];
		this.chalsOnBoard.remove(this.pawnsMap.get(associatedSquare));
		this.nbEatenChals++;
		System.out.println(this.nbEatenChals);
	}

	public int freeBagh() {
		int freeBagh = 4;
		for(int i=0; i<4; i++) {
			ArrayList<Coordinates> list = this.baghOnBoard[i].allPosibleMoves();
			if(list.isEmpty())
				freeBagh--;
		}
		return freeBagh;
	}

}