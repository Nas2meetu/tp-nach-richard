package tp.pr5;

import static tp.pr5.Constants.*;
import javax.swing.JOptionPane;
import tp.pr5.gui.NavigationPanel;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.Item;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class NavigationModule extends Observable<NavigationObserver> {

	private Place actualPlace;
	private Direction lookingDirection;
	private City cityMap;
	private RobotEngine robot;
	private NavigationPanel navPanel;

	/**
	 * 
	 * Constructor of two parameter to create a NavigationModule
	 * 
	 * @param city
	 *            is the map where the robot lives
	 * 
	 * @param currentPlace
	 *            place where robot is
	 * 
	 */

	public NavigationModule(City city, Place currentPlace) {
		super();
		this.cityMap = city;
		this.actualPlace = currentPlace;
		this.lookingDirection = Direction.NORTH;
	}

	/**
	 * Constructor of three parameter to create a NavigationModule
	 * 
	 * @param city
	 *            is the map where the robot lives
	 * @param robot
	 * @param currentPlace
	 *            place where robot is
	 * 
	 */

	public NavigationModule(City city, Place currentPlace, RobotEngine robot) {

		this(city, currentPlace);
		this.robot = robot;

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
	 * Drop an item in the current place place.
	 * 
	 * @param it
	 *            The name of the item to be dropped.
	 * @throws InstructionExecutionException
	 * 
	 */

	public void dropItemAtCurrentPlace(Item item) {
		actualPlace.dropItem(item);
		if (navPanel != null) {
			JOptionPane.showMessageDialog(navPanel, PLACE_ITEM + item.getId());
			robot.getContainer().updateInventory();

		}
	}

	/**
	 * 
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
	 * 
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
	 *            - New direction for the robot
	 * 
	 */

	public void initHeading(Direction heading) {
		this.lookingDirection = heading;
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
		} else if (getHeadingStreet().isOpen()) {
			actualPlace = getHeadingStreet().nextPlace(actualPlace);
			if (actualPlace.isSpaceship() && (navPanel != null))
				robot.isOver();
			if (navPanel != null)
				navPanel.showActualPlaceLog(actualPlace);
		} else
			throw new InstructionExecutionException(STREET_CLOSE);
		if (navPanel != null)
			navPanel.updateCity(actualPlace, lookingDirection);

	}

	/**
	 * Show if you can pick and item from current place.
	 * 
	 * @param it
	 *            is an item
	 * 
	 */

	public void pickItemFromCurrentPlace(Item it) {
		Item item = actualPlace.getItem(it.getId());
		if (item != null) {
			actualPlace.pickItem(it.getId());
			if (navPanel != null) {
				navPanel.showActualPlaceLog(actualPlace);
				JOptionPane.showMessageDialog(navPanel,
						CONTAINER_ITEM + it.getId());
			}
		}

	}

	/**
	 * Updates the current direction of the robot according to the rotation
	 * 
	 * @param rotation
	 *            - left or right
	 * 
	 */

	public void rotate(Rotation rotation) {

		if (rotation.equals(Rotation.LEFT)) {
			lookingDirection = lookingDirection.turnLeft();
		} else if (rotation.equals(Rotation.RIGHT)) {
			lookingDirection = lookingDirection.turnRight();
		}
		if (navPanel != null)
			navPanel.updateIcon(lookingDirection);
		//navigationObserver.headingChanged(lookingDirection);
	}

	/**
	 * Provides the observers with the information (description + inventory) of
	 * the current place
	 * 
	 */
	public void scanCurrentPlace() {
		for (NavigationObserver navObserver : observers) {
			navObserver.placeScanned(actualPlace);
		}
	}

	/**
	 * 
	 * Return a public method (cityMap) of a private attribute CityMap.
	 * 
	 * @return cityMap is the map where the robot lives.
	 */

	public City getCityMap() {
		return cityMap;
	}

	/**
	 * Introduce update messages into log panel
	 */

	public void updatePlace() {
		if (navPanel != null)
			navPanel.updateLog();
	}

	/**
	 * 
	 * Introduce initial place message into log panel
	 * 
	 * @param navPanel
	 *            contain navigation panel
	 * 
	 */

	public void setNavigationPanel(NavigationPanel navPanel) {
		this.navPanel = navPanel;
		this.navPanel.setInitialPlace(actualPlace);
	}

}
