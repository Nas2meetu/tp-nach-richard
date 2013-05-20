package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          The superclass of every type of item. It contains the common
 *          information for all the items and it defines the interface that the
 *          items must match
 */

public abstract class Item {

	private String id;
	private String description;

	/**
	 * 
	 * Constructor of two parameters to define an item.
	 * 
	 * @param id
	 *            is a reference to identify an item.
	 * @param description
	 *            is description of item.
	 */

	public Item(String id, String description) {
		this.id = id;
		this.description = description;

	}

	/**
	 * Checks if the item can be used. Subclasses must override this method
	 * 
	 * @return true if the item can be used
	 */

	public abstract boolean canBeUsed();

	/**
	 * Try to use the item with a robot in a given place. It returns whether the
	 * action was completed. Subclasses must override this method
	 * 
	 * @param robot
	 *            The robot that uses the item
	 * @param navigation
	 *            The Place where the item is used
	 * @return true if the action was completed. Otherwise, it returns false
	 */

	public abstract boolean use(RobotEngine robot, NavigationModule navigation);

	/**
	 * 
	 * Return a public method (id) of a private attribute (Id).
	 * 
	 * @return id is a reference to identify an item.
	 */

	public String getId() {
		return this.id;
	}

	/**
	 * 
	 * Return information about an item: id (reference to identify an item) +
	 * description (description of item).
	 * 
	 */

	public String toString() {
		return this.id + ": " + this.description;
	}

	/**
	 * 
	 * Return a public method (description) of a private attribute
	 * (Description).
	 * 
	 * @return description is description of item.
	 */

	public String getDescription() {
		return description;
	}

}
