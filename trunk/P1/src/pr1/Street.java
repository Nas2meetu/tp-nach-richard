package pr1;

public class Street {
	
	private Place source;
	private Direction direction;
	private Place target;
	
	public Street(Place s, Direction d, Place t){
		this.source = s;
		this.direction = d;
		this.target = t;
				
	}
	public boolean comeOutFrom(Place place, Direction whichDirection) {
		
		if ((whichDirection.equals(direction) || whichDirection.equals(direction.oppossite())) 
			&& (this.source.equals(place) || this.target.equals(place))) {
			return true;
		}else
			return false;
		
						
	}
	

	public Place nextPlace(Place whereAmI) {
		
		Place nextPlace = null;
		
		if(this.source.equals(whereAmI)){
			nextPlace = target;			
		}else if (this.target.equals(whereAmI)){
			nextPlace = source; 
		}
		return nextPlace;  		
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}

}
