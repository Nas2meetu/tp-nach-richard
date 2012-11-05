package tp.pr1;

public enum Direction { NORTH, EAST, SOUTH, WEST, UNKNOWN;
		

	/*public Direction opposite(){
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
		case UNKNOWN:
			oppossiteDirection = UNKNOWN;
		}
		return oppossiteDirection;
	}*/
	public Direction opposite() {
		Direction oppositeDirection = UNKNOWN;

		if (this == NORTH) {
			oppositeDirection = SOUTH;
		} else if (this == SOUTH) {
			oppositeDirection = NORTH;
		} else if (this == EAST) {
			oppositeDirection = WEST;
		} else if (this == WEST) {
			oppositeDirection = EAST;
		}

		return oppositeDirection;
	}

	/*public Direction turnLeft(){
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
	}*/
	
	
	public Direction turnLeft(){

		Direction direction = UNKNOWN;
		
		if (this == NORTH) {
			direction = WEST;
		} else if (this == SOUTH) {
			direction = EAST;
		} else if (this == EAST) {
			direction = NORTH;
		} else if (this == WEST) {
			direction = SOUTH;
		}

		return direction;
	}
	
	public Direction turnRight(){
		
		Direction direction = UNKNOWN;
		
		if (this == NORTH) {
			direction = EAST;
		} else if (this == SOUTH) {
			direction = WEST;
		} else if (this == EAST) {
			direction = SOUTH;
		} else if (this == WEST) {
			direction = NORTH;
		}

		return direction;
	}

}


