package baghchal.IA;

import java.util.ArrayList;
import java.util.List;

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
			move = this.positionAction();
		}
		else {
			move = this.moveAction();
		}
		System.out.println("Selected move : " + move);
		return move;
	}
	
	
	private Move positionAction() {
		Move move = null;
		//Le premier pion à un endroit sûr
		if(this.isFirstMove()) {
			move = new Move(this.selectRandomOpeningMoveLV2(), this.board);
		}
		else {
			List<ChalPawn> chals = this.board.getChalsOnBoard();
			//On protège d'abord nos pions déjà posé.
			move = this.foundChalToProtect(chals);
			//On prend les coins disponible
			if(move == null) {
				move = this.takeAngle();
			}
			//On pose un pion à un endroit stratégique au bord.
			if(move == null) {
				move = this.takeBorderPosition(chals);
			}
			//On pose là où il y a le moins de risque possible.
			
			
			else {
				BaghChalMinMax minmaxIA = new BaghChalMinMax(new Board(this.board), BaghChalMinMax.CHAL_TURN);
				move =  minmaxIA.pickPerfectMove(100);
				System.out.println(move);
				move = new Move(move.getStart(), this.board);
			}
		}
		return move;
	}
	
	private Move foundChalToProtect(List<ChalPawn> chals) {
		ChalPawn vulnerableChal = null;
		Square BaghSquare;
		for (ChalPawn chal : chals) {
			if(chal.isVulnerable()) {
				vulnerableChal = chal;
				
			}
			if(vulnerableChal != null) {
				return this.saveChalWithPlacement(vulnerableChal);
			}
		}
		return null;
	}
	
	@SuppressWarnings("null")
	private Move saveChalWithPlacement(ChalPawn vulnerableChal) {
		List<Square> squares = vulnerableChal.squaresAround(1);
		
		//TODO: trouver la case ou placer la chèvre pour le sauver
		Square squareForSave = null;//TODO: lahaut
		if(new ChalPawn(squareForSave.getPosition().getX(), squareForSave.getPosition().getY(),this.board).isVulnerable()) {
			return null;
		}
		return new Move(squareForSave.getPosition(), this.board);
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
		return null;
	}
	
	private Move moveAction() {
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
