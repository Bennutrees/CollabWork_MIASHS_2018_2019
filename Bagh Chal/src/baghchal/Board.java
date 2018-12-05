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
		this.squaresOnBoard = this.createSquaresMap();
		this.chalsOnBoard = new ArrayList<ChalPawn>();
		this.baghsOnBoard = new BaghPawn[nbFreeBaghs];
		this.initBaghs();
	}

	private Square[][] createSquaresMap() {
		this.squaresOnBoard = new Square[5][5];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
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
		for(int i = 0; i < 4; i++) {
			BaghPawn newBagh = new BaghPawn(baghCoordinates[i][row], baghCoordinates[i][column]);
			Square associatedSquare = this.squaresOnBoard[baghCoordinates[i][row]][baghCoordinates[i][column]];
			this.baghsOnBoard[i] = newBagh;
			associatedSquare.setAvailability(false);
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

	public BaghPawn[] getBaghsOnBoard() {
		return this.baghsOnBoard;
	}

	public HashMap<Square, AbstractPawn> getPawnsMap() {
		return this.pawnsMap;
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
		ChalPawn newChal = new ChalPawn(position.getX(), position.getY());
		Square associatedSquare = this.squaresOnBoard[position.getX()][position.getY()];
		this.chalsOnBoard.add(newChal);
		associatedSquare.setAvailability(false);
		this.pawnsMap.put(associatedSquare, newChal);
		this.nbChalsToPlace--;
	}

	public void eatChal(Coordinates position) {
		Square associatedSquare = this.squaresOnBoard[position.getX()][position.getY()];
		this.chalsOnBoard.remove(this.pawnsMap.get(associatedSquare));
		this.pawnsMap.put(associatedSquare, null);
		associatedSquare.setAvailability(true);
		this.nbEatenChals++;
		System.out.println(this.nbEatenChals);
	}

	public int freeBagh() {
		int freeBagh = 4;
		for(int i=0; i<4; i++) {
			ArrayList<Coordinates> list = this.baghsOnBoard[i].allPossibleMoves();
			if(list.isEmpty())
				freeBagh--;
		}
		return freeBagh;
	}

}