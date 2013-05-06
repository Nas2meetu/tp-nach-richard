package tp.pr5.items;

public interface InventoryObserver {

	/**
	 * Notifies that the container has changed
	 * 
	 * @param inventory
	 */
	public void	inventoryChange(java.util.List<Item> inventory);
	
	/**
	 * Notifies that the user requests a SCAN instruction over the inventory.
	 *  
	 * @param inventoryDescription
	 */
	public void	inventoryScanned(java.lang.String inventoryDescription);
	
	/**
	 * Notifies that an item is empty and it will be removed from the container.
	 * 
	 * @param itemName
	 */
	public void	itemEmpty(java.lang.String itemName);
	
	/**
	 * Notifies that the user wants to scan an item allocated in the inventory
	 * 
	 * @param description
	 */
	public void	itemScanned(java.lang.String description);
	
}
