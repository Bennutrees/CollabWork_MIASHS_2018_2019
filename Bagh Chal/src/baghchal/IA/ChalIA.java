package baghchal.IA;

import baghchal.Board;
import baghchal.Coordinates;

public class ChalIA extends IAPlayer{

	private Board board;

	public ChalIA() {
		this.board = Board.getBoard();
	}

	@Override
	public Coordinates[] iaAction() {
		Coordinates[] movement = new Coordinates[2];

		if(this.isFirstMove()) {
			movement[0] = this.selectRandomOpeningMoveLV2();
		}

		return movement;

	}


	/****************************************************************************************************/
	/***************************************** private methodes *****************************************/
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
        int r = (int) (Math.random() * 5);
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

	private boolean isFirstMove() {
		return (this.board.getNbChalsToPlace() == 20);
	}

}
