package tp.pr5;

import static tp.pr5.Constants.*;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          It represents a place in the city. Places are connected by streets
 *          according to the 4 compass directions, North, East, South and West.
 *          Every place has a name and a textual description about itself. This
 *          description is displayed when the robot arrives at the place. A
 *          place can represent the spaceship where the robot is safe. When the
 *          robot arrives at this place, the application is over.
 */

public class Place implements PlaceInfo {

	private String placeName;
	private boolean isSpaceship;
	private String placeDescription;
	private ItemContainer itemsInPlace;

	/**
	 * Constructor of three parameters with name and description place and if
	 * this place has a Robot Space Ship
	 * 
	 * @param placeName
	 *            is place name
	 * @param isSpaceship
	 *            is place where Spaceship is it
	 * @param placeDescription
	 *            is information about place
	 */

	public Place(String placeName, boolean isSpaceship, String placeDescription) {
		this.placeName = placeName;
		this.isSpaceship = isSpaceship;
		this.placeDescription = placeDescription;
		this.itemsInPlace = new ItemContainer();

	}

	/**
	 * Constructor without parameters
	 */

	public Place() {
		itemsInPlace = new ItemContainer();

	}

	/** 
	 * Return a public method (addItem) of a private attribute (item).
	 * 
	 * @param item
	 *            The item to be added
	 * 
	 * @return true if and only if the item can be added to the place, i.e., the
	 *         place does not contain an item with the same name
	 */

	public boolean addItem(Item item) {
		return itemsInPlace.addItem(item);
	}

	/** 
	 * Drop an item in this place. The operation can fail, returning false
	 * 
	 * @param it
	 *            The name of the item to be dropped.
	 * @return true if and only if the item is dropped in the place, i.e., an
	 *         item with the same identifier does not exists in the place
	 */

	public boolean dropItem(Item it) {
		return (it != null && itemsInPlace.addItem(it));

	}

	/**
	 * Return a public method (getItem) of a private attribute (existItem).
	 * 
	 * @param id
	 *            Identifier of an item
	 * @return true if and only if the place contains the item identified by id
	 */

	public boolean existItem(String id) {
		return itemsInPlace.getItem(id) != null;
	}

	/**
	 * Return a public method (placeName) of a private attribute (PlaceName).
	 * 
	 * @return placeName is name of place
	 */

	public String getName() {
		return placeName;
	}

	/**
	 * Return a public method (placeDescription) of a private attribute
	 * (Description).
	 * 
	 * @return placeDescription The place description
	 */

	public String getDescription() {
		return placeDescription;
	}

	/**
	 * Return a public method (getItem) of a private attribute (id).
	 * 
	 * @param id
	 *            is a reference to identify an item.
	 * 
	 * @return getItem(id) is the id of an item
	 */

	public Item getItem(String id) {
		return itemsInPlace.getItem(id);
	}

	/**
	 * Return a public method (isSpaceship) of a private attribute
	 * (isSpaceship).
	 * 
	 * @return isSpaceship true if robot is at spaceship in this place.
	 */

	public boolean isSpaceship() {
		return isSpaceship;
	}

	/**
	 * Return a public method (pickItem) of a private attribute (id).
	 * 
	 * @param id
	 *            is a reference to identify an item.
	 * 
	 * @return The item of identifier id if it exists in the place. Otherwise
	 *         the method returns null
	 */

	public Item pickItem(String id) {
		return itemsInPlace.pickItem(id);
	}

	/**
	 * Override toString to show place information.
	 */

	public String toString() {
		if (itemsInPlace.numberOfItems() == 0)
			return placeName + LINE_SEPARATOR + placeDescription
					+ LINE_SEPARATOR + PLACE_EMPTY + LINE_SEPARATOR
					+ itemsInPlace.toString();
		else
			return placeName + LINE_SEPARATOR + placeDescription
					+ LINE_SEPARATOR + SHOW_PLACE + LINE_SEPARATOR
					+ itemsInPlace.toString();
	}

}
