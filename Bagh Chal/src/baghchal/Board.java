package baghchal;

public class Board {
	
	private static Board plateau = new Board();
	private ChalPawn[] chalsOnBoard;
	private BaghPawn[] baghOnBoard;
	private int nbChalsToPlace;
	private int nbBaghsFree;
	private int nbEatenChals;
	
	private Board() {
		chalsOnBoard = new ChalPawn[25];
		baghOnBoard = new BaghPawn[25];
		nbChalsToPlace = 20;
		nbBaghsFree = 4;
		nbEatenChals = 0;
	}
	
	public static Board getPlateau() {
		return plateau;
	}	
	
}