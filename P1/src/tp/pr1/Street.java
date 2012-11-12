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
	 * @param sourcePlace
	 * @param direction
	 * @param targetPlace
	 */

	public Street(Place sourcePlace, Direction direction, Place targetPlace) {
		this.sourcePlace = sourcePlace;
		this.direction = direction;
		this.targetPlace = targetPlace;

	}
	/**
	 * 
	 * @param place
	 * @return
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
	 * @param place
	 * @param fromDirection
	 * @return
	 */

	public boolean comeOutFrom(Place place, Direction fromDirection) {

		return ((comeOutFromSourcePlace(place, fromDirection)
				|| comeOutFromTargetPlace(place, fromDirection)));
		
	}

	private boolean comeOutFromTargetPlace(Place place, Direction fromDirection) {
		return fromDirection.equals(direction.opposite()) && targetPlace.equals(place);
	}

	private boolean comeOutFromSourcePlace(Place place, Direction fromDirection) {
		return place.equals(sourcePlace) && direction.equals(fromDirection);
	}

}
