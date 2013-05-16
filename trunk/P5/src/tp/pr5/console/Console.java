package tp.pr5.console;

import java.util.List;
import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;
import static tp.pr5.Constants.*;

public class Console extends Object implements NavigationObserver,
		RobotEngineObserver, InventoryObserver {

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
	 */
	@Override
	public void communicationHelp(String help) {
		System.out.println(help);
	}

	/**
	 * The robot engine informs that the robot has shut down (because it has
	 * arrived at the spaceship or it has run out of fuel)
	 */
	@Override
	public void engineOff(boolean atShip) {
		if (atShip)
			System.out.println(END_GAME);
		else
			System.out.println(END_FUEL);

	}

	/**
	 * Notifies that the robot heading has changed
	 * 
	 */
	@Override
	public void headingChanged(Direction newHeading) {
		System.out.println(LOOKING_DIRECTION + newHeading);
	}

	/**
	 * Notifies that the navigation module has been initialized
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
	 */
	@Override
	public void inventoryChange(List<Item> inventory) {
		System.out.println(inventory.toString());
	}

	/**
	 * Notifies that the user requests a SCAN instruction over the inventory.
	 * 
	 */
	@Override
	public void inventoryScanned(String inventoryDescription) {
		System.out.println(CONTAINER + inventoryDescription + LINE_SEPARATOR);
	}

	/**
	 * Notifies that an item is empty and it will be removed from the container.
	 * 
	 */
	@Override
	public void itemEmpty(String itemName) {
		System.out.println(ITEM_NOT_EXIST + itemName);

	}

	/**
	 * Notifies that the user wants to scan an item allocated in the inventory
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
		System.out.println(placeDescription.getName()
				+ placeDescription.getDescription());
	}

	/**
	 * Notifies that the user requested a RADAR instruction
	 * 
	 */
	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		System.out.println(placeDescription.getDescription());
	}

	/**
	 * The robot engine informs that it has raised an error
	 * 
	 */
	@Override
	public void raiseError(String msg) {
		System.out.println(msg);
	}

	/**
	 * Notifies that the robot has arrived at a place
	 * 
	 */
	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		System.out.println(LOOKING_DIRECTION + heading + LINE_SEPARATOR
				+ place.getName() + LINE_SEPARATOR + place.getDescription());
	}

	/**
	 * The robot engine informs that the robot wants to say something
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
	 */
	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		System.out.println(POWER + fuel + LINE_SEPARATOR + RECYCLED_MATERIAL
				+ recycledMaterial);

	}

}
