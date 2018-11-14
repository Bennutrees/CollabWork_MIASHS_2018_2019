package baghchal;

public class DiagonalSquare extends BasicSquare {
	
	public DiagonalSquare(int x,int y) {
		super(x,y);
	}
	
	@Override
	public boolean isNeighbour(AbstractSquare neighbourSquare) {
		int x1 = this.position.getLigne();
		int y1 = this.position.getColonne();
		int x2 = neighbourSquare.position.getLigne();
		int y2 = neighbourSquare.position.getColonne();
		
		boolean horizontallyAligned = Math.abs(x1-x2) == 1 && y1 == y2;
		boolean verticallyAligned = Math.abs(y1-y2) == 1 && x1 == x2;
		boolean diagonallyAligned = Math.abs(x1-x2) == 1 && Math.abs(y1-y2) == 1;
		
		return horizontallyAligned || verticallyAligned || diagonallyAligned;
	}
}
