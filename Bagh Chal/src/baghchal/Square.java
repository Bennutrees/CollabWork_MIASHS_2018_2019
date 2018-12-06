package baghchal;

import java.util.ArrayList;

public class Square {

	private Coordinates position;
	private boolean isAvailable;
	private boolean isBorder;
	private boolean isDiagonal;
	private boolean hasNorth;
	private boolean hasSouth;
	private boolean hasWest;
	private boolean hasEast;
	private boolean hasNorthWest;
	private boolean hasNorthEast;
	private boolean hasSouthWest;
	private boolean hasSouthEast;

	//Constructor
	public Square(int x,int y) {
		this.position = new Coordinates(x,y);
		this.isAvailable = true;
		this.isBorder = this.isBorder();
		this.isDiagonal = this.isDiagonal();
		initDirections();
	}

	private boolean isBorder() {
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();

		return x == 0 || y == 0 || x == 4 || y == 4;
	}

	private boolean isDiagonal() {
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();

		return (x + y) % 2 == 0;
	}

	private void initDirections() {
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();

		boolean isDiagonalCenter = this.isDiagonal && !this.isBorder;
		this.hasNorth = y != 0;
		this.hasSouth = y != 4;
		this.hasWest = x != 0;
		this.hasEast = x != 4;
		this.hasNorthWest = isDiagonalCenter || (this.isDiagonal && (x >= 2 && y >= 2));
		this.hasNorthEast = isDiagonalCenter || (this.isDiagonal && (x <= 2 && y >= 2));
		this.hasSouthWest = isDiagonalCenter || (this.isDiagonal && (x >= 2 && y <= 2));
		this.hasSouthEast = isDiagonalCenter || (this.isDiagonal && (x <= 2 && y <= 2));
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

	public boolean getHasNorth() {
		return hasNorth;
	}

	public boolean getHasSouth() {
		return hasSouth;
	}

	public boolean getHasWest() {
		return hasWest;
	}

	public boolean getHasEast() {
		return hasEast;
	}

	public boolean getHasNorthWest() {
		return hasNorthWest;
	}

	public boolean getHasNorthEast() {
		return hasNorthEast;
	}

	public boolean getHasSouthWest() {
		return hasSouthWest;
	}

	public boolean getHasSouthEast() {
		return hasSouthEast;
	}

	public void setAvailability(boolean newAvailability) {
		this.isAvailable = newAvailability;
	}


//	public boolean isNeighbour(Square neighbourSquare) {
//		int x1 = this.getPosition().getX();
//		int y1 = this.getPosition().getY();
//		int x2 = neighbourSquare.getPosition().getX();
//		int y2 = neighbourSquare.getPosition().getY();
//		boolean horizontallyAligned = Math.abs(x1-x2) == 1 && y1 == y2;
//		boolean verticallyAligned = Math.abs(y1-y2) == 1 && x1 == x2;
//		boolean diagonallyAligned = Math.abs(x1-x2) == 1 && Math.abs(y1-y2) == 1;
//
//		if (!(this.isDiagonal && neighbourSquare.isDiagonal)) {
//			return horizontallyAligned || verticallyAligned;
//		}
//		else {
//			return diagonallyAligned;
//		}
//	}

	private boolean hasMovePossibleToward(Direction direction) {
        if (direction == null) {
            return false;
        }
        switch (direction) {
	        case N:
	            return this.getHasNorth();
	        case E:
	        	return this.getHasEast();
	        case S:
	        	return this.getHasSouth();
	        case W:
	        	return this.getHasWest();
	        case NE:
	        	return this.getHasNorthEast();
	        case NW:
	        	return this.getHasNorthWest();
	        case SE:
	        	return this.getHasSouthEast();
	        case SW:
	        	return this.getHasSouthWest();
	        default:
	            return false;
        }
	}

   public ArrayList<Direction> getSquareAllowedDirections() {
    	Direction[] directions = Direction.values();
    	ArrayList<Direction> allowedDirections = new ArrayList<Direction>();

    	for(int i=0; i<directions.length; i++){
    		if (this.hasMovePossibleToward(directions[i]))
    			allowedDirections.add(directions[i]);
    	}
    	return allowedDirections;
    }
}