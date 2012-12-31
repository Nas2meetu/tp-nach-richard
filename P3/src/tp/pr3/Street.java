package tp.pr3;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
*/


public class Street {

	private Place sourcePlace;
	private Direction direction;
	private Place targetPlace;
	private boolean isOpen;
	private String code;
	
	
	
	/**
	 * 
	 * Constructor of three parameters to create Streets 
	 * @param sourcePlace is one side of street
	 * @param direction is default direction
	 * @param targetPlace is opposite side of street 
	 * 
	 */

	public Street(Place sourcePlace, Direction direction, Place targetPlace) {
		this.sourcePlace = sourcePlace;
		this.direction = direction;
		this.targetPlace = targetPlace;
		this.isOpen = true; 
		this.code = null;

	}
	
	public Street(Place sourcePlace, Direction direction, Place targetPlace, boolean Open, String code) {
		this.sourcePlace = sourcePlace;
		this.direction = direction;
		this.targetPlace = targetPlace;
		this.isOpen = Open;
		this.code = code;

	}


	public Place nextPlace(Place place) {
		Place nextPlace = null;
		if (place.equals(sourcePlace)) {
			nextPlace = targetPlace;
		} else if (place.equals(targetPlace)) {
			nextPlace = sourcePlace;
		}else 
			nextPlace=null;

		return nextPlace;
	}
	
	/**
	 * 
	 * @param place is place where Robot is  --o esta o la del nextplace--
	 * @param fromDirection is direction where Robot comes
	 * @return ComeOutFrom is correct place that Robot moves
	 */

	public boolean comeOutFrom(Place place, Direction fromDirection) {

		return ((comeOutFromSourcePlace(place, fromDirection)
				|| comeOutFromTargetPlace(place, fromDirection)));
		
	}
	
	/**
	 * 
	 * @param place is place where Robot is
	 * @param fromDirection is direction where Robot comes
	 * @return comeOutFromTargetPlace is Target place where's Robot comes
	 * 
	 */

	private boolean comeOutFromTargetPlace(Place place, Direction fromDirection) {
		return fromDirection.equals(direction.opposite()) && targetPlace.equals(place);
	}
	
	/**
	 * 
	 * @param place is place where Robot is
	 * @param fromDirection is direction where Robot comes
	 * @return comeOutFromSourcePlace is Source place where's Robot comes
	 * 
	 */
	private boolean comeOutFromSourcePlace(Place place, Direction fromDirection) {
		return place.equals(sourcePlace) && direction.equals(fromDirection);
	}

 

	public boolean isOpen() {
		return isOpen;
	}

	
	public boolean open(CodeCard card) {
		if (card.getCode().equals(code)){
			isOpen= true;
		}else
			isOpen = false;
		return isOpen;
	}
	
	public String getCode() {
		return code;
	}

	public boolean close(CodeCard card){
		
		if(isOpen && card.getCode().equals(code)){
			isOpen = false;
		}
		return !isOpen;
	}

}
