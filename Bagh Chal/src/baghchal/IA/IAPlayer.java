package baghchal.IA;

import baghchal.Coordinates;
import baghchal.Move;

public abstract class IAPlayer {
	public abstract Move iaAction();
	protected abstract Move randomMoves();
}
