package tp.pr5;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          Interface of the observers that want to be notified about the events
 *          related to the navigation module. Classes that implement this
 *          interface wiil be informed when the robot changes its heading, when
 *          it arrives at a place, when the place is modified (because the robot
 *          picked or dropped an item) or when the user requests to use the
 *          radar.
 */

public interface NavigationObserver {

	/**
	 * Notifies that the robot heading has changed
	 * 
	 * @param newHeading
	 */
	public void headingChanged(Direction newHeading);

	/**
	 * Notifies that the navigation module has been initialized
	 * 
	 * @param initialPlace
	 * @param heading
	 */
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading);

	/**
	 * Notifies that the place where the robot stays has changed (because the
	 * robot picked or dropped an item)
	 * 
	 * @param placeDescription
	 */
	public void placeHasChanged(PlaceInfo placeDescription);

	/**
	 * Notifies that the user requested a RADAR instruction
	 * 
	 * @param placeDescription
	 */
	public void placeScanned(PlaceInfo placeDescription);

	/**
	 * Notifies that the robot has arrived at a place
	 * 
	 * @param heading
	 * @param place
	 */
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place);

}
