package baghchal;

public abstract class AbstractPawn implements Comparable<AbstractPawn>{
	
	private Coordinates position;
	
	public AbstractPawn(int x,int y) {
		this.position = new Coordinates(x,y);
	}

	public Coordinates getPosition() {
		return position;
	}

	public void setPosition(Coordinates position) {
		this.position = position;
	}
	
	public abstract void move(Move move);
	
	
}
