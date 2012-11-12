package tp.pr1;

/**
* 
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
* @param  
* 
* @return
*/

public enum Direction { NORTH, EAST, SOUTH, WEST, UNKNOWN;
		
/**
 * Define valid directions for Wall·E
 *
 */

	public Direction opposite() {
		 
		Direction oppositeDirection = UNKNOWN;
		
		 /** 
		 * Define opposite directions for Wall·E
		 * @return oppositeDirection
		 */

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
		
		 /** 
		 * Define turnLeft direction for Wall·E
		 * @return direction
		 */

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
		
		 /** 
		 * Define turnRight direction for Wall·E
		 * @return direction
		 */
		
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


