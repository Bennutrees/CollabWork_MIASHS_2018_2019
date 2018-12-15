package baghchal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {

	private Square[][] squaresOnBoard;
	private ArrayList<ChalPawn> chalsOnBoard;
	private BaghPawn[] baghsOnBoard;
	private HashMap<Square, AbstractPawn> pawnsMap;

	private int nbChalsToPlace;
	private int nbFreeBaghs;
	private int nbEatenChals;

	//Constructor
	public Board() {
		this.nbChalsToPlace = 20;
		this.nbFreeBaghs = 4;
		this.nbEatenChals = 0;

		this.pawnsMap = new HashMap<Square, AbstractPawn>();
		this.squaresOnBoard = this.createSquaresMap();
		this.chalsOnBoard = new ArrayList<ChalPawn>();
		this.baghsOnBoard = new BaghPawn[4];
		this.initBaghs();
	}
	
	public Board(Board board) {
		nbChalsToPlace = board.getNbChalsToPlace();
		nbFreeBaghs = board.getNbFreeBaghs();
		nbEatenChals = board.getNbEatenChals();

		chalsOnBoard = new ArrayList<ChalPawn>();
		baghsOnBoard = new BaghPawn[4];
		
		pawnsMap = new HashMap<Square, AbstractPawn>();
		squaresOnBoard = createSquaresMap();
		this.reproductionOfMaps(board.getChalsOnBoard(), board.getBaghsOnBoard());
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
	
	private void reproductionOfMaps(ArrayList<ChalPawn> originalsChals, BaghPawn[] originalBaghs) {
		
		for (ChalPawn chalPawn : originalsChals) {
			ChalPawn newChal = new ChalPawn(chalPawn.getPosition().getX(), chalPawn.getPosition().getY(), this);
			Square associatedSquare = this.squaresOnBoard[chalPawn.getPosition().getX()][chalPawn.getPosition().getY()];
			this.chalsOnBoard.add(newChal);
			associatedSquare.setAvailability(false);
			this.pawnsMap.put(associatedSquare, newChal);
		}
		int i = 0;
		for (BaghPawn baghPawn : originalBaghs) {
			BaghPawn newBagh = new BaghPawn(baghPawn.getPosition().getX(), baghPawn.getPosition().getY(), this);
			Square associatedSquare = this.squaresOnBoard[baghPawn.getPosition().getX()][baghPawn.getPosition().getY()];
			this.baghsOnBoard[i] = newBagh;
			associatedSquare.setAvailability(false);
			this.pawnsMap.put(associatedSquare, newBagh);
			i++;
		}
	}

	private void initBaghs() {
		int baghCoordinates[][] = { {0,0}, {4,0}, {0,4}, {4,4} };
		int row = 0, column = 1;
		for(int i = 0; i < 4; i++) {
			BaghPawn newBagh = new BaghPawn(baghCoordinates[i][row], baghCoordinates[i][column], this);
			Square associatedSquare = this.squaresOnBoard[baghCoordinates[i][row]][baghCoordinates[i][column]];
			this.baghsOnBoard[i] = newBagh;
			associatedSquare.setAvailability(false);
			this.pawnsMap.put(associatedSquare, newBagh);
		}
	}

	public void affichage() {
		System.out.println("------------");
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				String p = " ";
				Square thisSquare = this.getSquaresOnBoard()[i][j];
				if(thisSquare.getIsAvailable() == false){
					p = this.getPawnsMap().get(thisSquare).toString();
				}
				System.out.print("|"+p);
			}
			System.out.println("|");
			System.out.println("------------");
		}
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
		ChalPawn newChal = new ChalPawn(position.getX(), position.getY(), this);
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
	}

	public void calculateNbFreeBaghs() {
		int nbFreeBaghs = 4;
		for(int i = 0; i < 4; i++) {
			List<Move> list = this.baghsOnBoard[i].allPawnPossibleMoves();
			if(list.isEmpty())
				nbFreeBaghs--;
		}
		this.nbFreeBaghs = nbFreeBaghs;
	}
}