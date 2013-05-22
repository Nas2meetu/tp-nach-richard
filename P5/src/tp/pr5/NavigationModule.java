package tp.pr5;

import static tp.pr5.Constants.*;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.Item;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          This class is in charge of the robot navigation features. It
 *          contains the city where the robot looks for garbage, the current
 *          place where the robot is, and the current direction of the robot. It
 *          contains methods to handle the different robot movements and to pick
 *          and drop items at the current place.
 */

public class NavigationModule extends Observable<NavigationObserver> {

	private Place actualPlace;
	private Direction lookingDirection;
	private City cityMap;

	/**
	 * Constructor of two parameter to create a NavigationModule
	 * 
	 * @param city
	 *            is the map where the robot lives
	 * 
	 * @param currentPlace
	 *            place where robot is
	 */

	public NavigationModule(City city, Place currentPlace) {
		super();
		this.cityMap = city;
		this.actualPlace = currentPlace;
		this.lookingDirection = Direction.NORTH;
	}

	/**
	 * Checks if the robot has arrived at a spaceship
	 * 
	 * @return isSpaceship true if an only if the current place is the spaceship
	 */

	public boolean atSpaceship() {
		return actualPlace.isSpaceship();
	}

	/**
	 * Drop an item in the current place place. It does not check whether the
	 * operation fails
	 * 
	 * @param item
	 *            The name of the item to be dropped.
	 */

	public void dropItemAtCurrentPlace(Item item) {
		actualPlace.dropItem(item);
		notifyPlaceHasChanged();

	}

	private void notifyPlaceHasChanged() {
		for (NavigationObserver navObserver : observers) {
			navObserver.placeHasChanged(actualPlace);
		}
	}

	/**
	 * Checks if there is an item with a given id in this place
	 * 
	 * @param id
	 *            Identifier of the item we are looking for
	 * 
	 * @return true if and only if an item with this id is in the current place
	 */

	public boolean findItemAtCurrentPlace(String id) {
		return actualPlace.existItem(id);
	}

	/**
	 * Returns the robot heading
	 * 
	 * @return lookingDirection The direction where the robot is facing to.
	 */

	public Direction getCurrentHeading() {
		return lookingDirection;
	}

	/**
	 * Returns the current place where the robot is (for testing purposes)
	 * 
	 * @return actualPlace The current place
	 */

	public Place getCurrentPlace() {
		return actualPlace;
	}

	/**
	 * Returns the street opposite the robot
	 * 
	 * @return actualPlace The street which the robot is facing, or null, if
	 *         there is not any street in this direction
	 */

	public Street getHeadingStreet() {
		return cityMap.lookForStreet(actualPlace, lookingDirection);

	}

	/**
	 * Initializes the current heading according to the parameter
	 * 
	 * @param heading
	 *            New direction for the robot
	 */

	public void initHeading(Direction heading) {
		this.lookingDirection = heading;
	}

	public void requestInitNavigationModule() {
		for (NavigationObserver navObserver : observers) {
			navObserver.initNavigationModule(actualPlace, lookingDirection);
		}
	}

	/**
	 * The method tries to move the robot following the current direction. If
	 * the movement is not possible because there is no street, or there is a
	 * street which is closed, then it throws an exception. Otherwise the
	 * current place is updated according to the movement
	 * 
	 * @throws InstructionExecutionException
	 *             An exception with a message about the encountered problem
	 */

	public void move() throws InstructionExecutionException {

		if (getHeadingStreet() == null) {
			throw new InstructionExecutionException(NO_STREET
					+ lookingDirection);
		}
		if (getHeadingStreet().isOpen()) {
			actualPlace = getHeadingStreet().nextPlace(actualPlace);
			notifyRobotArrivesAtPlace();
		} else
			throw new InstructionExecutionException(STREET_CLOSE);

	}

	private void notifyRobotArrivesAtPlace() {
		for (NavigationObserver navObserver : observers) {
			navObserver.robotArrivesAtPlace(lookingDirection, actualPlace);
		}
	}

	/**
	 * Tries to pick an item characterized by a given identifier from the
	 * current place. If the action was completed the item is removed from the
	 * current place.
	 * 
	 * Show if you can pick and item from current place.
	 * 
	 * @param it
	 *            is an item
	 * 
	 *            return The item of identifier id if it exists in the place.
	 *            Otherwise the method returns null
	 */

	public void pickItemFromCurrentPlace(Item it) {
		Item item = actualPlace.getItem(it.getId());
		if (item != null) {
			actualPlace.pickItem(it.getId());
			notifyPlaceHasChanged();
		}
	}

	/**
	 * Updates the current direction of the robot according to the rotation
	 * 
	 * @param rotation
	 *            left or right
	 */

	public void rotate(Rotation rotation) {

		if (rotation.equals(Rotation.LEFT)) {
			lookingDirection = lookingDirection.turnLeft();
		} else if (rotation.equals(Rotation.RIGHT)) {
			lookingDirection = lookingDirection.turnRight();
		}
		notifyHeadingChanged();
	}

	private void notifyHeadingChanged() {
		for (NavigationObserver navObserver : observers) {
			navObserver.headingChanged(lookingDirection);
		}

	}

	/**
	 * Provides the observers with the information (description + inventory) of
	 * the current place
	 */

	public void scanCurrentPlace() {
		notifyPlaceScanned();
	}

	private void notifyPlaceScanned() {
		for (NavigationObserver navObserver : observers) {
			navObserver.placeScanned(actualPlace);
		}
	}

	/**
	 * Return a public method (cityMap) of a private attribute CityMap.
	 * 
	 * @return cityMap is the map where the robot lives.
	 */

	public City getCityMap() {
		return cityMap;
	}

}
