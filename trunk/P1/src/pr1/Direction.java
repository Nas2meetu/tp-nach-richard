package pr1;

public enum Direction { NORTH, EAST, SOUTH, WEST, UNKNOWN;
	/*@Override
	public static Direction[] values(){
		for (Direction c : Direction.values())
			System.out.println(c);
	
}*/
	public Direction oppossite(){
		Direction oppossiteDirection = UNKNOWN;
		switch (this) {
		case NORTH:
			oppossiteDirection = SOUTH;
		case SOUTH:
			oppossiteDirection = NORTH;
		case EAST:
			oppossiteDirection = WEST;
		case WEST:
			oppossiteDirection = EAST;
		default:
			oppossiteDirection = UNKNOWN;
			break;
		
		}
		return oppossiteDirection;
	}

	
}
