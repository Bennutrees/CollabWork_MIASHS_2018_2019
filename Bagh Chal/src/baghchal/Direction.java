package baghchal;

public enum Direction {
	
	NW(-1, -1), N(0, -1), NE(1, -1), E(1, 0), SE(1, 1), S(0, 1), SW(-1, 1), W(-1, 0);

    public final int dx, dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction getDirection(int dx, int dy) {
        for (Direction direction : values()) {
            if (direction.dx == dx && direction.dy == dy) {
                return direction;
            }
        }
        return null;
    }

    public static Direction[] getSquarePossibleDirections(Square square) {
    	if(square.getIsDiagonal()) {
    		return values();
    	}
    	Direction[] dir = new Direction[4];
    	dir[0] = N;
    	dir[1] = S;
    	dir[2] = E;
    	dir[3] = W;
    	return dir;
    }
}
