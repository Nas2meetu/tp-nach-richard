package pr1;

public class Street {
	
	private Place source;
	private Direction direction;
	private Place target;
	private Place whereAmI;
	
	public Street(){
		
		this.source = source;
		this.direction = direction;
		this.target = target;
				
	}
	public boolean comeOutFrom(Place place, Direction whichDirection) {
		
			return true;	
		
	}
	public Place getSource() {
		return source;
	}
	public Direction getDirection() {
		return direction;
	}
	public Place getTarget() {
		return target;
	
	}
	
	public Place nextPlace(Place whereAmI) {
		return null;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	

}
