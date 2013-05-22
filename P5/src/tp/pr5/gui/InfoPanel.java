package tp.pr5.gui;

import java.util.List;

import static tp.pr5.Constants.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

/**
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          Panel at the bottom of the window that displays messages about the
 *          events that occur during the simulation. This panel implements all
 *          the observer interfaces in order to be notified about all event
 *          ocurred
 */

public class InfoPanel extends JPanel implements RobotEngineObserver,
		NavigationObserver, InventoryObserver {

	private static final long serialVersionUID = 1L;

	private JLabel lbInfo = new JLabel("Start Game");

	public InfoPanel() {
		add(lbInfo);
	}

	/**
	 * The robot engine informs that the communication is over.
	 */

	@Override
	public void communicationCompleted() {
		lbInfo.setText(QUIT);

	}

	/**
	 * The robot engine informs that the help has been requested
	 * 
	 * @param help
	 *            A string with information help
	 */
	@Override
	public void communicationHelp(String help) {
		// not use

	}

	/**
	 * The robot engine informs that the robot has shut down (because it has
	 * arrived at the spaceship or it has run out of fuel)
	 * 
	 * @param atShip
	 *            true if the robot shuts down because it has arrived at the
	 *            spaceship or false if it has run out of fuel
	 */

	@Override
	public void engineOff(boolean atShip) {
		// Not used

	}

	/**
	 * Notifies that the robot heading has changed
	 * 
	 * @param newHeading
	 *            New robot heading
	 */

	@Override
	public void headingChanged(Direction newHeading) {
		lbInfo.setText(LOOKING_DIRECTION + newHeading);

	}

	/**
	 * Notifies that the navigation module has been initialized
	 * 
	 * @param initialPlace
	 *            The place where the robot starts the simulation.
	 * @param heading
	 *            The initial robot heading.
	 */

	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		// Not used

	}

	/**
	 * Notifies that the container has changed
	 * 
	 * @Param inventory New inventory
	 */

	@Override
	public void inventoryChange(List<Item> inventory) {
		lbInfo.setText(INVENTORY_UPDATED);

	}

	/**
	 * Notifies that the user requests a SCAN instruction over the inventory.
	 * 
	 * @param collectionDescription
	 *            Information about the inventory
	 */

	@Override
	public void inventoryScanned(String inventoryDescription) {
		// Not used

	}

	/**
	 * Notifies that an item is empty and it will be removed from the container.
	 * An invocation to the inventoryChange method will follow.
	 * 
	 * @param itemName
	 *            Name of the empty item
	 */

	@Override
	public void itemEmpty(String itemName) {
		lbInfo.setText(ITEM_CANT_USED + itemName + IN_MY_INVENTORY);
	}

	/**
	 * Notifies that the user wants to scan an item allocated in the inventory
	 * 
	 * @param description
	 *            Item description
	 */

	@Override
	public void itemScanned(String description) {
		// Not used

	}

	/**
	 * Notifies that the place where the robot stays has changed (because the
	 * robot picked or dropped an item)
	 * 
	 * @param placeDescription
	 *            Information with the current place
	 */

	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		// Not used

	}

	/**
	 * Notifies that the user requested a RADAR instruction
	 * 
	 * @param placeDescription
	 *            Information with the current place
	 */
	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		// not use

	}

	/**
	 * The robot engine informs that it has raised an error
	 * 
	 * @param msg
	 *            Error message.
	 */

	@Override
	public void raiseError(String msg) {
		lbInfo.setText(msg);

	}

	/**
	 * Notifies that the robot has arrived at a place
	 * 
	 * @param heading
	 *            The robot movement direction
	 * @param place
	 *            The place where the robot arrives
	 */

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		lbInfo.setText(MOVE + heading);

	}

	/**
	 * The robot engine informs that the robot wants to say something
	 * 
	 * @param message
	 *            The robot message
	 * 
	 */

	@Override
	public void robotSays(String message) {
		lbInfo.setText(message);

	}

	/**
	 * The robot engine informs that the fuel and/or the amount of recycled
	 * material has changed
	 * 
	 * @param fuel
	 *            Current amount of fuel
	 * @param recycledMaterial
	 *            Current amount of recycled material
	 */
	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		lbInfo.setText(ROBOT_UPDATE + fuel + "," + recycledMaterial + ")");

	}

}
