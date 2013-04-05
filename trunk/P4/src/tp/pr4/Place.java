package tp.pr4;

import static tp.pr4.Constants.*;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class Place {

	private String placeName;
	private boolean isSpaceship;
	private String placeDescription;
	private ItemContainer itemsInPlace;
	

	public String getPlaceName() {
		return placeName;
	}

	public String getDescription(){
		return placeDescription;
	}
	
	/**
	 * 
	 * Constructor of three parameters with name and description place and if
	 * this place has a Robot Space Ship
	 * 
	 * @param placeName is place name
	 * @param isSpaceship is place where Spaceship is it
	 * @param placeDescription is information about place
	 * 
	 */

	public Place(String placeName, boolean isSpaceship, String placeDescription) {
		this.placeName = placeName;
		this.isSpaceship = isSpaceship;
		this.placeDescription = placeDescription;
		this.itemsInPlace = new ItemContainer();

	}

	/**
	 * Constructor without parameters
	 * 
	 */
	
	public Place() {
		itemsInPlace = new ItemContainer();
		
	}
	
	/**
	 * 
	 * @param it
	 * @return
	 */

	public boolean dropItem(Item it) {
		
		if (itemsInPlace.containsItem(it.getId()))
			System.out.println(CONTAINER_NO_ITEM + it.getId());
		else if(itemsInPlace.addItem(it)){
		    System.out.println(PLACE_ITEM + it.getId());
		    return true;
		}else
			 System.out.println(PLACE_REPEAT_ITEM + it.getId());
		return false;	       

	}

	/**
	 * 
	 * Return a public method (getItem) of a private attribute (id).
	 * 
	 * @param id is a reference to identify an item.
	 * 
	 * @return getItem
	 * 
	 */

	public Item getItem(String id) {
		return itemsInPlace.getItem(id);
	}

	/**
	 * 
	 * Return a public method (isSpaceship) of a private attribute (isSpaceship).
	 * 
	 * @return isSpaceship if robot is at spaceship in this place.
	 * 
	 */

	public boolean isSpaceship() {
		return isSpaceship;
	}

	/**
	 * 
	 * Return a public method (pickItem) of a private attribute (id).
	 * 
	 * @param id is a reference to identify an item.
	 * 
	 * @return pickItem
	 * 
	 */

	public Item pickItem(String id) {
		return itemsInPlace.pickItem(id);
	}

	/**
	 * 
	 * Return a public method (addItem) of a private attribute (item).
	 * 
	 * @param item is an item
	 * 
	 * @return addItem 
	 * 
	 */

	public boolean addItem(Item item) {
		return itemsInPlace.addItem(item);
	}

	/**
	 * 
	 * Override toString to show place information.
	 * 
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

	/**
	 * 
	 * Return a public method (getItem) of a private attribute (existItem).
	 * 
	 * @param id
	 * @return
	 */
	
	public boolean existItem(String id) {
		return itemsInPlace.getItem(id) != null; // mirar
	}

}
