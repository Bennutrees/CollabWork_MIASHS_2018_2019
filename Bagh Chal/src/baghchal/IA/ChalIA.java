package baghchal.IA;

import java.util.ArrayList;
import java.util.List;

import baghchal.BaghPawn;
import baghchal.Board;
import baghchal.ChalPawn;
import baghchal.Coordinates;
import baghchal.Direction;
import baghchal.Move;
import baghchal.Square;

public class ChalIA extends IAPlayer{

	public ChalIA(Board board) {
		super(board);
	}

	@Override
	public Move iaAction() {
		Move move;

		if(this.board.getNbChalsToPlace() > 0) {
			move = this.positioningAction();
		}
		else {
			move = this.movingAction();
		}
//		System.out.println("Selected move : " + move);
		return move;
	}
	
	
	private Move positioningAction() {
		int IA_level = 2;
		Move move = null;
		//Le premier pion à un endroit sûr
		if(this.isFirstMove()) {
			move = new Move(this.selectRandomOpeningMoveLV2(), this.board);
		}
		else {
			System.out.println("-------------------------------------------------");
			List<ChalPawn> chals = this.board.getChalsOnBoard();
			//On protège d'abord nos pions déjà posé.
			move = this.findChalToProtect(chals);
			System.out.println("chal to protect : " + move);
			//On prend les coins disponible
			if(move == null) {
				move = this.takeAngle();
				System.out.println("take angle : " + move);
			}
			//On pose un pion à un endroit stratégique au bord.
			if(move == null) {
				move = this.takeBorderPosition(chals);
				System.out.println("take strategic border : " + move);
			}
			//On pose là où il y a le moins de risque possible.
			//TODO: A décomment si ChalPawn getAnglesOfAttack foncitonne.
//			if(move == null) {
//				move = this.safestPlace();
//				System.out.println("Safest Place : " + move);
//			}
			if(move == null) {
				BaghChalMinMax minmaxIA = new BaghChalMinMax(new Board(this.board), BaghChalMinMax.CHAL_TURN);
				move =  minmaxIA.selectBestMove(100,IA_level);
				move = new Move(move.getStart(), this.board);
				System.out.println("minmax : " + move);
			}
		}
		return move;
	}
	
	/*Protect Chall if the position is not a vulnerable position*/
	private Move findChalToProtect(List<ChalPawn> chals) {
		BaghPawn[] baghs = this.board.getBaghsOnBoard();
		ChalPawn vulnerableChal = null;
		BaghPawn baghPawn = null;
		for (ChalPawn chal : chals) {
			if(chal.isThreaten() ) {
				Square chalSquare = this.board.getSquaresOnBoard()[chal.getPosition().getX()][chal.getPosition().getY()];
				vulnerableChal = chal;
				for (BaghPawn bagh : baghs) {
					Square baghSquare = this.board.getSquaresOnBoard()[bagh.getPosition().getX()][bagh.getPosition().getY()];
					if(chalSquare.isNeighbour(baghSquare)) {
						baghPawn = bagh;
					}
				}
			}
			if(vulnerableChal != null) {
				Move saveMove = this.saveChalWithPlacement(vulnerableChal, baghPawn);
				if (saveMove != null)
					return saveMove;
			}
		}
		return null;
	}
	
	/*Calculate the position for save*/
	private Move saveChalWithPlacement(ChalPawn vulnerableChal, BaghPawn agresivBagh) {
		int x = agresivBagh.getPosition().getX() - vulnerableChal.getPosition().getX();
		int y = agresivBagh.getPosition().getY() - vulnerableChal.getPosition().getY();
		Direction baghDirection = Direction.getDirection(x, y);
		Direction opositeDirection = Direction.getOpositeDirection(baghDirection);
		
		int dx = vulnerableChal.getPosition().getX() + opositeDirection.dx;
		int dy = vulnerableChal.getPosition().getY() + opositeDirection.dy;
		
		ChalPawn tempChal = new ChalPawn(dx, dy, this.board);
		if(tempChal.isThreaten()) {
			return null;
		}
		try {
			return new Move(new Coordinates(dx, dy), this.board);
		}
		catch (IllegalArgumentException e){
			return null;
		}
	}
	
	private Move takeAngle() {
		int[][] boardAngle = {{0,0} , {0,4} , {4,4}, {4,0}};
		for (int i = 0; i < 4; i++) {
			int[] posi = boardAngle[i];
			Square square = this.board.getSquaresOnBoard()[posi[0]][posi[1]];
			if(square.getIsAvailable())
				return new Move(new Coordinates(posi[0], posi[1]), this.board);
		}
		return null;
	}	
	
	/*Place Chal on border and next to other Chal*/
	private Move takeBorderPosition(List<ChalPawn> chals) {
		for (ChalPawn chal : chals) {
			Square mySquare = this.board.getSquaresOnBoard()[chal.getPosition().getX()][chal.getPosition().getY()];
			if(mySquare.getIsBorder()) {
				ArrayList<Direction> possibleDirections = mySquare.getSquareAllowedDirections();
				for (Direction direction : possibleDirections) {
					int dx = chal.getPosition().getX()+direction.dx;
					int dy = chal.getPosition().getY()+direction.dy;
					Square possibleSquare = this.board.getSquaresOnBoard()[dx][dy];
					if(possibleSquare.getIsBorder() && possibleSquare.getIsAvailable()) {
						return new Move(new Coordinates(dx, dy), this.board);
					}
				}
			}
		}
		System.out.println("on cherche un bord classieu");
		ArrayList<Square> borderSquares = this.board.getBorderSquares();
		for (Square square : borderSquares) {
			int x = square.getPosition().getX();
			int y = square.getPosition().getY();
			if(this.board.getSquaresOnBoard()[x][y].getIsAvailable() && !new ChalPawn(x, y, this.board).isThreaten())
				return new Move(new Coordinates(square.getPosition().getX(), square.getPosition().getY()), this.board);
		}
		return null;
	}
	
	private Move safestPlace() {
		ArrayList<Square> freeSquares = this.board.getFreeSquares();
		if(freeSquares.isEmpty())
			return null;
		int safeInd = 10;
		ArrayList<Square> possiblePosition = null;
		for (Square square : freeSquares) {
			ChalPawn chal = new ChalPawn(square.getPosition().getX(), square.getPosition().getY(), this.board);
			ArrayList<Square> angleOfAttack = chal.getAnglesOfAttack();
			if(safeInd > angleOfAttack.size()) {
				safeInd = angleOfAttack.size();
				possiblePosition = angleOfAttack;
			}
		}
		if(possiblePosition != null) {
			int rand = (int) (Math.random() * safeInd);
			Square selectedSquare = possiblePosition.get(rand);
			return new Move(selectedSquare.getPosition(), this.board);
		}
		return null;
	}
	
	private Move movingAction() {
		Move move;
		move = this.randomMoves();
		return move;
	}

	/****************************************************************************************************/
	/********************************************* Heritage *********************************************/
	/****************************************************************************************************/
	@Override
	protected Move randomMoves() {
		ArrayList<ChalPawn> chals = this.board.getChalsOnBoard();

		int random = (int)(Math.random() * chals.size()-1);
		ChalPawn chal = chals.get(random);

		List<Move> moves = ChalPawn.everyChalPossibleMoves(this.board);

		while(moves.isEmpty()) {
			random = (int)(Math.random() * chals.size()-1);
			chal = chals.get(random);
			moves = chal.possibleMoves();
		}

		random = (int)(Math.random() * moves.size()-1);

		return moves.get(random);
	}
	
	@Override
	protected List<Move> everyPossibleMoves() {
		return ChalPawn.everyChalPossibleMoves(board);
	}
	
	/****************************************************************************************************/
	/******************************************* Random moves *******************************************/
	/****************************************************************************************************/
    private Coordinates selectRandomOpeningMoveLV1() {
        int r = (int) (Math.random() * 5);
        switch (r) {
        case 0:
            return new Coordinates(2, 0);
        case 1:
            return new Coordinates(2, 4);
        case 2:
            return new Coordinates(0, 2);
        case 3:
            return new Coordinates(4, 2);
        default:
            return new Coordinates(2, 2);
        }
    }

    private Coordinates selectRandomOpeningMoveLV2() {
        int r = (int) (Math.random() * 4);
        switch (r) {
        case 0:
            return new Coordinates(2, 0);
        case 1:
            return new Coordinates(2, 4);
        case 2:
            return new Coordinates(0, 2);
        default:
            return new Coordinates(4, 2);
        }
    }

    private Coordinates selectRandomPositionBecauseImStupid() { //TODO: rename avant de rendre
    	Square[][] squares = this.board.getSquaresOnBoard();
    	Coordinates select = null;
    	while(select == null) {
    		int randX = (int) (Math.random() * 5);
    		int randY = (int) (Math.random() * 5);

    		if(squares[randX][randY].getIsAvailable()) {
    			select = new Coordinates(randX, randY);
    		}
    	}
		return select;
    }

	private boolean isFirstMove() {
		return (this.board.getNbChalsToPlace() == 20);
	}

}
