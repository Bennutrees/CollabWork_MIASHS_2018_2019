package baghchal;

public class Coordinates implements Comparable<Coordinates> {

	private int row;
	private int column;

	//Constructor
	public Coordinates(int row, int column) {
		this.row = row;
		this.column = column;
		if (column < 0 || column > 4 || row > 4 || row < 0) {
			throw new IllegalArgumentException("Coordonnées en dehors du Plateau");
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


	public boolean equals(Coordinates position) {
		return position.getY() == this.getY() && position.getX() == this.getX();
	}

	public int compareTo(Coordinates position) {
		int result ;
		if ((result = this.getX() - position.getX()) != 0 )
			return (result);
		else
			return (this.getY() - position.getY());
	}


	public String toString() {

		String coord = "";

		coord += this.getY() + ",";
		coord += this.getX();

		return coord;
	}

}