package tp.pr2;
import static tp.pr2.Constants.LINE_SEPARATOR;

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
	private Item[] itemsInPlace;

	
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
		int i=0;
		while (i<itemsInPlace.length) {
			if (itemsInPlace[i].getId().equals(id)){
				itemsInPlace[i]=itemsInPlace[i+1];
				i++;
			}	
		}
		itemsInPlace[itemsInPlace.length]=itemsInPlace[itemsInPlace.length-1];
		return getItem(id);
	}
		

	private Item getItem(String id) {
		return null;
	}

	/**
	 * Override toString to show place information
	 */
	
	public String toString() {
		return  placeName + LINE_SEPARATOR + placeDescription;
	}

	/* Add a item in place
	 * 
	public boolean addItem(Item item) {
		
		return (this.itemsInPlace[itemsInPlace.length+1]=item);

	
	}*/
	
	/**
	 * 
	 * @param item Add a item in a place if item is not duplicate
	 * @return item
	 */
	
	
	public boolean addItem(Item item) {
		
		int i=0;
		while (i<itemsInPlace.length) {
			if (itemsInPlace[i].getId().equals(item.getId())){
				return false;
			}else
				i++;
		}
		itemsInPlace[itemsInPlace.length+1]=item;
		return true;
	
	}


}
