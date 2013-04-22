package tp.pr4;

import static tp.pr4.Constants.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import tp.pr4.gui.NavigationPanel;
import tp.pr4.instructions.exceptions.*;
import tp.pr4.items.Item;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class NavigationModule {

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
	 * @param currentPlace
	 *            place where robot is
	 */

	public NavigationModule(City city, Place currentPlace) {

		this.cityMap = city;
		this.actualPlace = currentPlace;
		this.lookingDirection = Direction.NORTH;

	}

	/**
	 * 
	 * Show direction of robot is looking at initial place.
	 * 
	 * @param heading
	 *            where robot is looking.
	 */

	public void initHeading(Direction heading) {
		this.lookingDirection = heading;
	}

	/**
	 * 
	 * Return a public method (isSpaceship) of a private attribute atSpaceship.
	 * 
	 * @return isSpaceship if Spaceship is in actual place or not
	 */

	public boolean atSpaceship() {
		return actualPlace.isSpaceship();
	}

	/**
	 * 
	 * Show if robot can turn or not depends of direction.
	 * 
	 * @param rotation
	 *            is rotation of Robot left or right
	 */

	public void rotate(Rotation rotation) {

		if (rotation.equals(Rotation.LEFT)) {
			lookingDirection = lookingDirection.turnLeft();
			if (navPanel != null)
				navPanel.updateIcon(lookingDirection);

		} else if (rotation.equals(Rotation.RIGHT)) {
			lookingDirection = lookingDirection.turnRight();
			if (navPanel != null)
				navPanel.updateIcon(lookingDirection);

		} else
			JOptionPane.showMessageDialog(navPanel,
					"WALL·E says: I can't turn to UNKNOW direction");

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
	 * 
	 * Return a public method (lookingDirection) of a private attribute
	 * CurrentPlace.
	 * 
	 * @return lookingDirection is direction that robot is looking.
	 */

	public Direction getCurrentHeading() {
		return lookingDirection;
	}

	/**
	 * 
	 * Return a public method (actualPlace) of a private attribute CurrentPlace.
	 * 
	 * @return actualPlace place where robot is.
	 */

	public Place getCurrentPlace() {
		return actualPlace;
	}

	/**
	 * 
	 * Return a public method (actualPlace and lookingDirection) of a private
	 * attribute HeadingStreet.
	 * 
	 * @return actualPlace place where robot is lookingDirection is direction
	 *         that robot is looking
	 */

	public Street getHeadingStreet() {
		return cityMap.lookForStreet(actualPlace, lookingDirection);

	}

	/**
	 * Show id of an item.
	 * 
	 * @param it
	 *            is an item
	 */

	public void dropItemAtCurrentPlace(Item it) {

		robot.getContainer().pickItem(it.getId());

	}

	/**
	 * 
	 * Return a public method (actualPlace.existItem(id)) of a private attribute
	 * id of ItemAtCurrentPlace.
	 * 
	 * @param id
	 *            is a reference to identify an item.
	 * @return actualPlace.existItem(id) if this item exist at this place or
	 *         not.
	 */

	public boolean findItemAtCurrentPlace(String id) {
		return actualPlace.existItem(id);
	}

	/**
	 * 
	 * @throws InstructionExecutionException
	 */

	public void move() throws InstructionExecutionException {

		if (getHeadingStreet() == null) {
			throw new InstructionExecutionException(NO_STREET
					+ lookingDirection);
		} else if (getHeadingStreet().isOpen()) {
			actualPlace = getHeadingStreet().nextPlace(actualPlace);
			if (navPanel != null)
				navPanel.showCurrentPlaceLog(actualPlace);
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
	 */

	public void pickItemAtCurrentPlace(Item it) {
		Item item = actualPlace.getItem(it.getId());
		if (item != null) {
			actualPlace.pickItem(it.getId());
			if (navPanel != null)
				navPanel.showCurrentPlaceLog(actualPlace);
		}

	}

	public void updatePlace() {
		if (navPanel != null)
			navPanel.updateLog();
	}

	public void setNavigationPanel(NavigationPanel navPanel) {
		this.navPanel = navPanel;
		this.navPanel.setInitialPlace(actualPlace);
	}

}
