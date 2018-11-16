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
		nbChalsToPlace = 20;
		nbBaghsFree = 4;
		nbEatenChals = 0;
		squaresOnBoard = new Square[5][5];
		chalsOnBoard = new ChalPawn[nbChalsToPlace]; //Faire une liste plutôt qu'un tableau
		baghOnBoard = new BaghPawn[nbBaghsFree];
	}
	
	public static Board getPlateau() {
		return plateau;
	}	
	
}