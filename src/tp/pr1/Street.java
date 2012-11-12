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
	 * Generate street and 
	 * @return instruction
	 */

	private Place sourcePlace;
	private Direction direction;
	private Place targetPlace;

	public Street(Place sourcePlace, Direction direction, Place targetPlace) {
		this.sourcePlace = sourcePlace;
		this.direction = direction;
		this.targetPlace = targetPlace;

	}

	public Place nextPlace(Place place) {
		Place nextPlace = null;
		if (place.equals(sourcePlace)) {
			nextPlace = targetPlace;
		} else if (place.equals(targetPlace)) {
			nextPlace = sourcePlace;
		}

		return nextPlace;
	}

	public boolean comeOutFrom(Place place, Direction fromDirection) {

		if (comeOutFromSourcePlace(place, fromDirection)
				|| comeOutFromTargetPlace(place, fromDirection)) {
			return true;
		} else
			return false;

	}

	private boolean comeOutFromTargetPlace(Place place, Direction fromDirection) {
		return fromDirection.equals(direction.opposite()) && targetPlace.equals(place);
	}

	private boolean comeOutFromSourcePlace(Place place, Direction fromDirection) {
		return place.equals(sourcePlace) && direction.equals(fromDirection);
	}

}
