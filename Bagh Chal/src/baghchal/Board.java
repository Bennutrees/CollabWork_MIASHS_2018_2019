package baghchal;

import java.util.ArrayList;

public class Board {

	private static Board plateau = new Board();
	private Square[][] squaresOnBoard;
	private ArrayList<ChalPawn> chalsOnBoard;
	private BaghPawn[] baghOnBoard;
	private int nbChalsToPlace;
	private int nbBaghsFree;
	private int nbEatenChals;

	private Board() {
		this.nbChalsToPlace = 20;
		this.nbBaghsFree = 4;
		this.nbEatenChals = 0;
		this.squaresOnBoard = new Square[5][5];
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				this.squaresOnBoard[i][j] = new Square(i,j);
			}
		}
		this.chalsOnBoard = new ArrayList<ChalPawn>();
		this.baghOnBoard = new BaghPawn[nbBaghsFree];

		this.baghsInitPosition();
	}

	private void baghsInitPosition(){
		int position[][] = { {0,0}, {4,0}, {0,4}, {4,4} };
		for(int i=0; i<4; i++) {
			BaghPawn bp = new BaghPawn(position[i][0], position[i][1]);
			this.baghOnBoard[i] = bp;
			this.squaresOnBoard[position[i][0]][position[i][1]].setPawn(bp);
		}
	}

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
		return this.baghOnBoard;
	}

	public int getNbEatenChals() {
		return this.nbEatenChals;
	}

	public int getNbChalsToPlace() {
		return nbChalsToPlace;
	}

	public int getNbBaghsFree() {
		return nbBaghsFree;
	}

	public void addChal(Coordinates crd) {
    	ChalPawn cp = new ChalPawn(crd.getLigne(), crd.getColonne());
		this.chalsOnBoard.add(cp);
		this.squaresOnBoard[crd.getLigne()][crd.getColonne()].setPawn(cp);
		this.nbChalsToPlace--;
	}

	public void eatChal(Coordinates crd) {
		this.chalsOnBoard.remove(this.squaresOnBoard[crd.getLigne()][crd.getColonne()].getPawn());
		this.squaresOnBoard[crd.getLigne()][crd.getColonne()].setPawn(null);
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