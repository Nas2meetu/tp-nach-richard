package tp.pr5.items;

import java.util.List;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          Interface of the observers that want to be notified about the events
 *          ocurred in the robot inventory. The container will notify its
 *          observer every change in the container (when the robot picks or
 *          drops items) and when an item is removed from the container because
 *          it is empty. The container will also notify when the user requests
 *          to scan an item or the whole container
 */

public interface InventoryObserver {

	/**
	 * Notifies that the container has changed
	 * 
	 * @param inventory
	 *            New inventory
	 */
	public void inventoryChange(List<Item> inventory);

	/**
	 * Notifies that the user requests a SCAN instruction over the inventory.
	 * 
	 * @param inventoryDescription
	 *            Information about the inventory
	 */
	public void inventoryScanned(String inventoryDescription);

	/**
	 * Notifies that an item is empty and it will be removed from the container.
	 * 
	 * @param itemName
	 *            Name of the empty item
	 */
	public void itemEmpty(String itemName);

	/**
	 * Notifies that the user wants to scan an item allocated in the inventory
	 * 
	 * @param description
	 *            Item description
	 */
	public void itemScanned(String description);

}
