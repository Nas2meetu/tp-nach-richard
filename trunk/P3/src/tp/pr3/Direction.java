package tp.pr3;

/**
* 
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 3
* 
*/


public enum Direction { NORTH, EAST, SOUTH, WEST, UNKNOWN;

	/**
	 * Define opposite directions for Wall·E
	 * @return oppositeDirection is opposite direction where robot is looking at
	 * 
	 */
		

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


	/**
	 * 
     * Define turnLeft direction for Wall·E
     * @return direction is direction that robot is looking at if it turn left
     * 
     */
		
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
	
	 /**
	 * 
     * Define turnRight direction for Wall·E
     * @return direction is direction that robot is looking at if it turn right
     * 
     */
	
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


