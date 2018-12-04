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
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setPosition(int x, int y) {
		this.row = x;
		this.column = y;
	}


	public boolean equals(Coordinates position) {
		return position.getColumn() == this.getColumn() && position.getRow() == this.getRow();
	}

	public int compareTo(Coordinates position) {
		int result ;
		if ((result = this.getRow() - position.getRow()) != 0 )
			return (result);
		else
			return (this.getColumn() - position.getColumn());
	}


	public String toString() {

		String coord = "";

		coord += this.getColumn() + ",";
		coord += this.getRow();

		return coord;
	}

}