package baghchal.IA;

import java.util.ArrayList;

import baghchal.BaghPawn;
import baghchal.Board;
import baghchal.Coordinates;

public class BaghIA extends IAPlayer{

	private Board board;

	public BaghIA() {
		this.board = Board.getBoard();
	}

	@Override
	public Coordinates[] iaAction() {
		Coordinates[] movement = this.randomMoves();
		return movement;
	}

	@Override
	protected Coordinates[] randomMoves() {
		Coordinates[] coords = new Coordinates[2];
		BaghPawn[] baghs = this.board.getBaghsOnBoard();
		int random = (int)(Math.random() * 3);
		BaghPawn bagh = baghs[random];
		ArrayList<Coordinates> moves = bagh.possibleMoves();

		while(moves.isEmpty()) {
			random = (int)(Math.random() * 3);
			bagh = baghs[random];
			moves = bagh.allPossibleMoves();
		}

		random = (int)(Math.random() * moves.size()-1);
		coords[0] = bagh.getPosition();
		coords[1] = moves.get(random);

		return coords;
	}

}
