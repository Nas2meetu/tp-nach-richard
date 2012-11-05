package tp.pr1;

public enum Direction { NORTH, EAST, SOUTH, WEST, UNKNOWN;
		

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


