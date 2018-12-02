package baghchal;

public class Square {

	private Coordinates position;
	private boolean isAvailable;
	private boolean isBorder;
	private boolean isDiagonal;

	private AbstractPawn pawn;

	public Square(int x,int y) {
		this.position = new Coordinates(x,y);
		this.setAvailable(true);
		this.isBorder = this.calculBorder();
		this.isDiagonal = this.isDiagonal();
	}

	public Coordinates getPosition() {
		return position;
	}

	private boolean calculBorder() {
		int x = this.getPosition().getLigne();
		int y = this.getPosition().getColonne();

		return x == 0 || y == 0 || x == 4 || y == 4;
	}

	private boolean isDiagonal() {
		int x = this.getPosition().getLigne();
		int y = this.getPosition().getColonne();

		return (x + y) % 2 == 0;
	}

	public boolean isNeighbour(Square neighbourSquare) {
		int x1 = this.getPosition().getLigne();
		int y1 = this.getPosition().getColonne();
		int x2 = neighbourSquare.getPosition().getLigne();
		int y2 = neighbourSquare.getPosition().getColonne();
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

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public boolean isBorder() {
		return isBorder;
	}

	public AbstractPawn getPawn() {
		return pawn;
	}

	public void setPawn(AbstractPawn pawn) {
		this.pawn = pawn;
	}

}