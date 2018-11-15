package baghchal;

public abstract class AbstractSquare {
	
	protected Coordinates position;
	protected boolean availability;
	
	public AbstractSquare(int x,int y) {
		this.position = new Coordinates(x,y);
		this.availability = true;
	}
	
	public Coordinates getPosition() {
		return position;
	}
	
	public boolean isAvailable() {
		return availability;
	}
	
	public abstract boolean isNeighbour(AbstractSquare neighbourSquare);
	public abstract boolean isBorder(AbstractSquare borderSquare);
	
}
