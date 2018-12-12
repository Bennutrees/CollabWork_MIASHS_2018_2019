package baghchal.IA;

import java.util.ArrayList;
import java.util.List;

import baghchal.Board;
import baghchal.ChalPawn;
import baghchal.Coordinates;
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
			if(this.isFirstMove()) {
				move = new Move(this.selectRandomOpeningMoveLV2(), this.board);
			}
			else {
				BaghChalMinMax minmaxIA = new BaghChalMinMax(this.board);
//				move = new Move(this.selectRandomPositionBecauseImStupid(), this.board);
				move =  minmaxIA.pickPerfectMove(10);
				move = new Move(move.getStart(), this.board);
			}
		}
		else {
			move = this.randomMoves();
		}
		return move;

	}


	/****************************************************************************************************/
	/***************************************** private methodes *****************************************/
	/****************************************************************************************************/
	@Override
	protected Move randomMoves() {
		ArrayList<ChalPawn> chals = this.board.getChalsOnBoard();

		int random = (int)(Math.random() * chals.size()-1);
		ChalPawn chal = chals.get(random);

		List<Move> moves = chal.possibleMoves();

		while(moves.isEmpty()) {
			random = (int)(Math.random() * chals.size()-1);
			chal = chals.get(random);
			moves = chal.possibleMoves();
		}

		random = (int)(Math.random() * moves.size()-1);

		return moves.get(random);
	}

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
