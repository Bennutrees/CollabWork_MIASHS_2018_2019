package baghchal.IA;

import java.util.ArrayList;

import baghchal.Board;
import baghchal.ChalPawn;
import baghchal.Coordinates;
import baghchal.Square;

public class ChalIA extends IAPlayer{

	private Board board;

	public ChalIA() {
		this.board = Board.getBoard();
	}

	@Override
	public Coordinates[] iaAction() {
		Coordinates[] movement;


		if(this.board.getNbChalsToPlace() > 0) {
			movement = new Coordinates[2];
			if(this.isFirstMove()) {
				movement[0] = this.selectRandomOpeningMoveLV2();
			}
			else {
				movement[0] = this.selectRandomPositionBecauseImStupid();
			}
		}
		else {
			movement = this.randomMoves();
		}
		return movement;

	}


	/****************************************************************************************************/
	/***************************************** private methodes *****************************************/
	/****************************************************************************************************/
	@Override
	protected Coordinates[] randomMoves() {
		Coordinates[] coords = new Coordinates[2];
		ArrayList<ChalPawn> chals = this.board.getChalsOnBoard();

		int random = (int)(Math.random() * chals.size()-1);
		ChalPawn chal = chals.get(random);

		ArrayList<Coordinates> moves = chal.possibleMoves();

		while(moves.isEmpty()) {
			random = (int)(Math.random() * chals.size()-1);
			chal = chals.get(random);
			moves = chal.possibleMoves();
		}

		random = (int)(Math.random() * moves.size()-1);
		coords[0] = chal.getPosition();
		coords[1] = moves.get(random);

		return coords;
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
