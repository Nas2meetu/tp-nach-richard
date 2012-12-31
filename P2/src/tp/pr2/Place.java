package tp.pr2;

import static tp.pr2.Constants.*;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
* 
*/


public class Place {
	

	private String placeName;
	private boolean isSpaceship;
	private String placeDescription;
	private ItemContainer itemsInPlace;
	

	
	/**
	 * Constructor of three parameters with name and description place and if this place has a Robot Space Ship
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
	
	public Item getItem(String id){
		return itemsInPlace.getItem(id);
	}
	

	public Place(){
		itemsInPlace = new ItemContainer();
	}
	
	/**
	 * 
	 * @return isSpaceship
	 * 
	 */

	public boolean isSpaceship() {
		return isSpaceship;
	}
	
	public Item pickItem(String id) {
			return itemsInPlace.pickItem(id);
	}
	
	public boolean addItem(Item item) {
		return itemsInPlace.addItem(item);
	}
	

	/**
	 * Override toString to show place information
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
