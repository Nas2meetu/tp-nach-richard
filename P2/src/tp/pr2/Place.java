package tp.pr2;

import static tp.pr2.Constants.*;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 2
* 
*/


public class Place {
	
	private String placeName;
	private boolean isSpaceship;
	private String placeDescription;
	private ItemContainer itemsInPlace;
	

	
	/**
	 * 
	 * Constructor of three parameters with name and description place and if this place has a Robot Space Ship
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
	public Place(){
		itemsInPlace = new ItemContainer();
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
	
	public Item getItem(String id){
		return itemsInPlace.getItem(id);
	}
	
	
	/**
	 * 
	 * @return isSpaceship if spaceship is in this place.
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
	 * @param item
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
			return  placeName + LINE_SEPARATOR + placeDescription+ LINE_SEPARATOR
					 + PLACE_EMPTY + LINE_SEPARATOR 
					 + itemsInPlace.toString();
		else
			return  placeName + LINE_SEPARATOR + placeDescription+ LINE_SEPARATOR
				 + SHOW_PLACE + LINE_SEPARATOR 
				 + itemsInPlace.toString();
	}

}
