package tp.pr5;

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
