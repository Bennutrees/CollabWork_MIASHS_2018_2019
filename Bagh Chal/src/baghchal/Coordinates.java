package baghchal;

public class Coordinates {

	private int row;
	private int column;

	//Constructor
	public Coordinates(int row, int column) {
		this.row = row;
		this.column = column;
		if (column < 0 || column > 4 || row > 4 || row < 0) {
			throw new IllegalArgumentException("Coordinates out of Board range");
		}
	}


	//Methods
	public int getX() {
		return row;
	}

	public int getY() {
		return column;
	}

	public void setPosition(int x, int y) {
		this.row = x;
		this.column = y;
	}
}