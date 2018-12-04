package baghchal;
import java.util.*;

public class Board {
	
	private static Board board = new Board();
	
	private Hashtable<Coordinates, Square> squaresOnBoard;
	private HashMap<Coordinates, AbstractPawn> pawnsOnBoard;
	private int nbChalsToPlace;
	private int nbBlockedBaghs;
	private int nbEatenChals;
	
	
	//Constructor
	private Board() {
		nbChalsToPlace = 20;
		nbBlockedBaghs = 0;
		nbEatenChals = 0;
		
		squaresOnBoard = this.createSquaresMap(5);
		pawnsOnBoard = this.createPawnsMap();
		this.initBaghs(pawnsOnBoard);
	}
	
	private Hashtable<Coordinates, Square> createSquaresMap(int size) {
		Hashtable<Coordinates, Square> squaresOnBoard = new Hashtable<Coordinates, Square>();
		int i = 0, j = 0;
		while (i < size) {
			while (j < size) {
				Square square = new Square(i,j);
				squaresOnBoard.put(square.getPosition(), square);
				j+=1;
			}
			i+=1;
		}
		return squaresOnBoard;
	}
	
	private HashMap<Coordinates, AbstractPawn> createPawnsMap() {
		HashMap<Coordinates, AbstractPawn> pawnsOnBoard = new HashMap<Coordinates, AbstractPawn>();
		return pawnsOnBoard;
	}
	
	private void initBaghs(HashMap<Coordinates, AbstractPawn> pawnsMap) {
		BaghPawn baghOne = new BaghPawn(0,0);
		BaghPawn baghTwo = new BaghPawn(0,4);
		BaghPawn baghThree = new BaghPawn(4,0);
		BaghPawn baghFour = new BaghPawn(4,4);
		
		pawnsMap.put(baghOne.getPosition(), baghOne);
		pawnsMap.put(baghTwo.getPosition(), baghTwo);
		pawnsMap.put(baghThree.getPosition(), baghThree);
		pawnsMap.put(baghFour.getPosition(), baghFour);
	}
	
	
	//Methods
	public static Board getBoard() {
		return board;
	}
	
	public int getNbChalsToPlace() {
		return this.nbChalsToPlace;
	}
	
	public int getNbBlockedBaghs() {
		return this.nbBlockedBaghs;
	}
	
	public int getNbEatenChals() {
		return this.nbEatenChals;
	}

	public void setNbChalsToPlace(int nbChalsToPlace) {
		this.nbChalsToPlace = nbChalsToPlace;
	}

	public void setNbBlockedBaghs(int nbBlockedBaghs) {
		this.nbBlockedBaghs = nbBlockedBaghs;
	}

	public void setNbEatenChals(int nbEatenChals) {
		this.nbEatenChals = nbEatenChals;
	}
	
	
	
	
	public String toString() {
		return null;
	}
	
	
	
	public static void main(String[] args) {
		Board board = new Board();
		System.out.println(	board.toString());
	}
}