package baghchal;

public class Square {

	private Coordinates position;
	private boolean isAvailable;
	private boolean isBorder;
	private boolean isDiagonal;
	
	//Constructor
	public Square(int x,int y) {
		this.position = new Coordinates(x,y);
		this.isAvailable = true;
		this.isBorder = this.isBorder();
		this.isDiagonal = this.isDiagonal();
	}

	private boolean isBorder() {
		int x = this.getPosition().getRow();
		int y = this.getPosition().getColumn();

		return x == 0 || y == 0 || x == 4 || y == 4;
	}

	private boolean isDiagonal() {
		int x = this.getPosition().getRow();
		int y = this.getPosition().getColumn();

		return (x + y) % 2 == 0;
	}

	
	//Methods
	public Coordinates getPosition() {
		return position;
	}
	
	public boolean getIsAvailable() {
		return isAvailable;
	}

	public boolean getIsBorder() {
		return isBorder;
	}
	
	public boolean getIsDiagonal() {
		return this.isDiagonal;
	}
	
	public void setAvailable(boolean newAvailability) {
		this.isAvailable = newAvailability;
	}

	
	public boolean isNeighbour(Square neighbourSquare) {
		int x1 = this.getPosition().getRow();
		int y1 = this.getPosition().getColumn();
		int x2 = neighbourSquare.getPosition().getRow();
		int y2 = neighbourSquare.getPosition().getColumn();
		boolean horizontallyAligned = Math.abs(x1-x2) == 1 && y1 == y2;
		boolean verticallyAligned = Math.abs(y1-y2) == 1 && x1 == x2;
		boolean diagonallyAligned = Math.abs(x1-x2) == 1 && Math.abs(y1-y2) == 1;

		if (!(this.isDiagonal && neighbourSquare.isDiagonal)) {
			return horizontallyAligned || verticallyAligned;
		}
		else {
			return diagonallyAligned;
		}
	}

}