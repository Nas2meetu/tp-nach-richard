package tp.pr5.console;

import java.util.List;
import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;
import static tp.pr5.Constants.*;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          The view that displays the application on the System.out. It
 *          implements all the observer interfaces in order to be notified about
 *          every event that occur when the robot is running.
 * 
 */

public class Console implements NavigationObserver, RobotEngineObserver,
		InventoryObserver {

	/**
	 * The robot engine informs that the communication is over.
	 * 
	 */
	@Override
	public void communicationCompleted() {
		System.out.println(QUIT);
		System.exit(0);
	}

	/**
	 * The robot engine informs that the help has been requested
	 * 
	 * help - A string with information help
	 * 
	 */

	@Override
	public void communicationHelp(String help) {
		System.out.println(help);
	}

	/**
	 * The robot engine informs that the robot has shut down (because it has
	 * arrived at the spaceship or it has run out of fuel)
	 * 
	 * atShip - true if the robot shuts down because it has arrived at the
	 * spaceship or false if it has run out of fuel
	 * 
	 */
	@Override
	public void engineOff(boolean atShip) {
		if (atShip)
			System.out.println(END_SPACESHIP);
		else
			System.out.println(END_FUEL);

	}

	/**
	 * Notifies that the robot heading has changed
	 * 
	 * newHeading - New robot heading
	 * 
	 */

	@Override
	public void headingChanged(Direction newHeading) {
		System.out.println(LOOKING_DIRECTION + newHeading);
	}

	/**
	 * Notifies that the navigation module has been initialized
	 * 
	 * initialPlace - The place where the robot starts the simulation. heading -
	 * The initial robot heading
	 * 
	 */

	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		System.out.println(initialPlace.toString() + LINE_SEPARATOR
				+ LOOKING_DIRECTION + heading);
	}

	/**
	 * Notifies that the container has changed
	 * 
	 * inventory - New inventory
	 * 
	 */
	@Override
	public void inventoryChange(List<Item> inventory) {
		// Not use
	}

	/**
	 * Notifies that the user requests a SCAN instruction over the inventory.
	 * 
	 * inventoryDescription - Information about the inventory
	 */
	@Override
	public void inventoryScanned(String inventoryDescription) {
		System.out.println(CONTAINER + inventoryDescription);
	}

	/**
	 * Notifies that an item is empty and it will be removed from the container.
	 * 
	 * itemName - Name of the empty item
	 * 
	 */

	@Override
	public void itemEmpty(String itemName) {
		System.out.println(ITEM_CANT_USED + itemName + IN_MY_INVENTORY);

	}

	/**
	 * Notifies that the user wants to scan an item allocated in the inventory
	 * 
	 * description - Item description
	 * 
	 */
	@Override
	public void itemScanned(String description) {
		System.out.println(WALLE_SAYS + description);
	}

	/**
	 * Notifies that the place where the robot stays has changed (because the
	 * robot picked or dropped an item)
	 * 
	 */
	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		// Not use
	}

	/**
	 * Notifies that the user requested a RADAR instruction
	 * 
	 * placeDescription - Information with the current place
	 * 
	 */

	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		System.out.println(placeDescription.toString());
	}

	/**
	 * The robot engine informs that it has raised an error
	 * 
	 * msg - Error message
	 */

	@Override
	public void raiseError(String msg) {
		System.out.println(msg);
	}

	/**
	 * Notifies that the robot has arrived at a place
	 * 
	 * heading - The robot movement direction. place - The place where the robot
	 * arrives
	 * 
	 */
	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		System.out.println(MOVE + heading + LINE_SEPARATOR + place.toString());
	}

	/**
	 * The robot engine informs that the robot wants to say something
	 * 
	 * message - The robot message
	 * 
	 */

	@Override
	public void robotSays(String message) {
		System.out.print(message);
	}

	/**
	 * The robot engine informs that the fuel and/or the amount of recycled
	 * material has changed
	 * 
	 * fuel - Current amount of fuel recycled. Material - Current amount of
	 * recycled material.
	 * 
	 */
	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		System.out.println(POWER + fuel + LINE_SEPARATOR + RECYCLED_MATERIAL
				+ recycledMaterial);

	}

}
