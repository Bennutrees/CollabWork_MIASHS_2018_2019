package baghchal;

public abstract class AbstractSquare {
	
	protected Coordinates position;
	
	public AbstractSquare(int x,int y) {
		this.position = new Coordinates(x,y);
	}
	
	public Coordinates getPosition() {
		return position;
	}
	
	public abstract boolean isNeighbour(AbstractSquare neighbourSquare);
	public abstract boolean isBorder(AbstractSquare borderSquare);
	
}
