package baghchal.IA;

import baghchal.Coordinates;

public abstract class IAPlayer {
	public abstract Coordinates[] iaAction();
	protected abstract Coordinates[] randomMoves();
}
