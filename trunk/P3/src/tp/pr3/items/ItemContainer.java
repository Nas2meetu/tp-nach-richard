package tp.pr3.items;

import static tp.pr3.Constants.*;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 3
 * 
 */

public class ItemContainer {

	private int numberOfItems;
	private Item[] container;

	/**
	 * Constructor of two parameters to define robot´s Container
	 */
	public ItemContainer() {
		container = new Item[100];
		numberOfItems = 0;
	}

	/**
	 * Return a public method (container) of a private attribute (container).
	 * 
	 * @return all items in Robot´s container
	 * 
	 */

	public Item[] getContainer() {
		return container;
	}

	/**
	 * Return a public method (numberOfItems) of a private attribute
	 * (numberOfItems).
	 * 
	 * @return the number of Items who´s robot has
	 * 
	 */

	public int numberOfItems() {
		return numberOfItems;
	}

	public boolean containsItem(String id) {
		boolean contains = false;
		int i = 0;
		while (i < numberOfItems() && !contains) {
			contains = container[i].getId().equalsIgnoreCase(id);
			i++;
		}
		return contains;

	}
	
	public String getId(Item it){
		return it.getId() ;
	}

	/**
	 * This method return an item if item´s id and container´s id are equals
	 * 
	 * @param id
	 *            is a reference to identify an item.
	 * 
	 * @return item if item´s id and container´s id are the same
	 */
	public Item getItem(String id) {

		int i = 0;
		while (i < numberOfItems) {
			if (container[i].getId().equalsIgnoreCase(id)) {
				return container[i];
			}
			i++;
		}
		return null;
	}

	/**
	 * 
	 * Use posItem to know the position of item's id. If position is -1, return
	 * an empty item. Make an instance Item and put into container's item in
	 * position (pos) Use after moveItemLeft to shift move position (pos) to
	 * left and decrease cont one unit, finally return item.
	 * 
	 * @param id
	 *            is a reference to identify an item.
	 * 
	 * @return picked item has been pick or not.
	 */

	public Item pickItem(String id) {
		Item picked;
		int pos = posItem(id);
		if (pos == -1)
			picked = null;
		else {
			Item item = container[pos];
			getItem(item.getId());
			this.moveItemLeft(pos);
			picked = item;
		}
		return picked;
	}

	/**
	 * 
	 * Recibe and item's id, and find position to insert this item. Use i
	 * parameter to move around container's array, from i position to end of
	 * container's array. Compare all item container with id was received from
	 * method. If comparation is small than 0 return i (position to insert item)
	 * else increase i counter. If counter i find the end of array position of
	 * insert item is i.
	 * 
	 * @param id
	 *            is a reference to identify an item.
	 * 
	 * @return i is a position to insert item.
	 */

	private int whereInsert(String id) {
		int i = 0;
		while (i < numberOfItems) {
			if (container[i].getId().compareToIgnoreCase(id) > 0) {
				return i;
			} else
				i++;
		}
		return i;
	}

	/**
	 * 
	 * PosItem receive an item's id, move around item's container and compare
	 * item's id with container's id, if equal return i. Else return -1
	 * 
	 * @param id
	 *            is a reference to identify an item.
	 * 
	 * @return -1
	 */

	private int posItem(String id) {
		int i = 0;
		while (i < numberOfItems) {
			if (container[i].getId().equalsIgnoreCase(id)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	/**
	 * moveItemLeft moves Item's position one to left from initial position
	 * (pos).
	 * 
	 * @param pos
	 *            initial position.
	 * 
	 */

	private void moveItemLeft(int pos) {
		for (int j = pos; j < numberOfItems; j++) {
			container[j] = container[j + 1];
		}
		numberOfItems--;
	}

	/**
	 * moveItemLeft moves Item's position one to right from initial position
	 * (i).
	 * 
	 * @param i
	 *            initial position.
	 */

	private void moveItemRight(int pos) {

		for (int j = numberOfItems; j >= pos; j--) {
			container[j + 1] = container[j];
		}
		numberOfItems++;
	}

	/**
	 * 
	 * addItem insert an item in itemContainer's container.
	 * 
	 * @param item
	 * 
	 * @return added
	 */

	public boolean addItem(Item item) {
		boolean added;
		int pos = posItem(item.getId());
		if (pos != -1)
			added = false;
		else {
			pos = whereInsert(item.getId());
			this.moveItemRight(pos);
			container[pos] = item;
			added = true;
		}
		return added;

	}

	/**
	 * 
	 * Show content of ItemContainer of place.
	 * 
	 */

	public String toString() {

		String showItems = "";
		if (numberOfItems() != 0)
			for (int i = 0; i < numberOfItems(); i++) {
				showItems += "   " + container[i].getId() + LINE_SEPARATOR;
			}
		return showItems;
	}

	/**
	 * 
	 * Show items of container.
	 * 
	 * @return showItems items of container of robot´s container.
	 */

	public String showItems() {

		String showItems = "";
		if (numberOfItems() == 0) {
			return CONTAINER_EMPTY;
		} else {
			for (int i = 0; i < numberOfItems(); i++) {
				showItems += "   " + container[i].getId() + LINE_SEPARATOR;
			}
			System.out.println(CONTAINER);
			return showItems;
		}
	}

}
