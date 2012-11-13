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


public class Street {
	
	/**
	 * 
	 */

	private Place sourcePlace;
	private Direction direction;
	private Place targetPlace;
	
	/**
	 * 
	 * @param sourcePlace is one side of street
	 * @param direction is default direction
	 * @param targetPlace is opposite side of street 
	 */

	public Street(Place sourcePlace, Direction direction, Place targetPlace) {
		this.sourcePlace = sourcePlace;
		this.direction = direction;
		this.targetPlace = targetPlace;

	}
	/**
	 * 
	 * @param place is place where is Robot
	 * @return nextPlace is next place where will goes Robot
	 */

	public Place nextPlace(Place place) {
		Place nextPlace = null;
		if (place.equals(sourcePlace)) {
			nextPlace = targetPlace;
		} else if (place.equals(targetPlace)) {
			nextPlace = sourcePlace;
		}

		return nextPlace;
	}
	
	/**
	 * 
	 * @param place is place where Robot is  --o esta o la del nextplace--
	 * @param fromDirection is direction from where robot comes
	 * @return ComeOutFrom is good place that Robot moves
	 */

	public boolean comeOutFrom(Place place, Direction fromDirection) {

		return ((comeOutFromSourcePlace(place, fromDirection)
				|| comeOutFromTargetPlace(place, fromDirection)));
		
	}
	
	/**
	 * 
	 * @param place is place where Robot is
	 * @param fromDirection is direction from where robot comes
	 * @return comeOutFromTargetPlace is Target place since Robot comes
	 */

	private boolean comeOutFromTargetPlace(Place place, Direction fromDirection) {
		return fromDirection.equals(direction.opposite()) && targetPlace.equals(place);
	}
	
	/**
	 * 
	 * @param place is place where Robot is
	 * @param fromDirection is direction from where robot comes
	 * @return comeOutFromSourcePlace is Source place since Robot comes
	 */
	private boolean comeOutFromSourcePlace(Place place, Direction fromDirection) {
		return place.equals(sourcePlace) && direction.equals(fromDirection);
	}

}
