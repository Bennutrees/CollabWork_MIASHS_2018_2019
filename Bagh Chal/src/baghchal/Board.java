package baghchal;

public class Board {

	private static Board plateau = new Board();
	private Square[][] squaresOnBoard;
	private ChalPawn[] chalsOnBoard;
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
		this.chalsOnBoard = new ChalPawn[nbChalsToPlace]; //Faire une liste plutôt qu'un tableau
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

	public ChalPawn[] getChalsOnBoard() {
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

	public void onMoreChalInGame() {
		this.nbChalsToPlace--;
	}

}