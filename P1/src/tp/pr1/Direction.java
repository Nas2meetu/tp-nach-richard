package tp.pr1;

public enum Direction { NORTH, EAST, SOUTH, WEST, UNKNOWN;
		

	public Direction oppossite(){
		Direction oppossiteDirection = UNKNOWN;
		switch (this) {
		case NORTH:
			oppossiteDirection = SOUTH;
		case EAST:
			oppossiteDirection = WEST;
		case SOUTH:
			oppossiteDirection = NORTH;
		case WEST:
			oppossiteDirection = EAST;
		default:
			oppossiteDirection = UNKNOWN;
			break;
		
		}
		return oppossiteDirection;
	}

	public Direction turnLeft(){
		Direction turnLeftDirection = UNKNOWN;
		switch (this) {
		case NORTH:
			turnLeftDirection = WEST;
		case SOUTH:
			turnLeftDirection = EAST;
		case EAST:
			turnLeftDirection = NORTH;
		case WEST:
			turnLeftDirection = SOUTH;
		case UNKNOWN:
			turnLeftDirection = UNKNOWN;
		}
		return turnLeftDirection;
	}
	
	public Direction turnRight(){
		Direction turnRightDirection = UNKNOWN;
		switch (this) {
		case NORTH:
			turnRightDirection = EAST;
		case SOUTH:
			turnRightDirection = WEST;
		case EAST:
			turnRightDirection = SOUTH;
		case WEST:
			turnRightDirection = NORTH;
		case UNKNOWN:
			turnRightDirection = UNKNOWN;
		}
		return turnRightDirection;
	}

}


