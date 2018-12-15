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
    
    public static Direction getOpositeDirection(Direction direction) {
    	switch(direction) {
    		case N:
    			return S;
    		case S:
    			return N;
    		case E:
    			return W;
    		case W:
    			return E;
    		case NE:
    			return SW;
    		case NW:
    			return SE;
    		case SW:
    			return NE;
    		case SE:
    			return NW;
    	}
		return null;
    }
}
