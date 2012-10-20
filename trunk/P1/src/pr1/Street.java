package pr1;

public class Street {
	
	private Place source;
	private Direction direction;
	private Place target;
	
	public Street(){
		
		this.source = source;
		this.direction = direction;
		this.target = target;
				
	}
	public boolean comeOutFrom(Place place, Direction whichDirection) {
		
		//if (whichDirection.equals(direction.oppossite()) && )
				
		
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
	

}
