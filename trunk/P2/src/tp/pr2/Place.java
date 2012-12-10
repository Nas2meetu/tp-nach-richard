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
	private ItemContainer itemsInPlace;
	//private Item[] itemsInPlace;
	//private int cont;

	
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
		if (itemsInPlace != null){
			return itemsInPlace.pickItem(id);
		}
		else
			return null;
	}
	public boolean addItem(Item item) {
		if (itemsInPlace != null){
			itemsInPlace.addItem(item);		
			return true;
		}else
			return false;
	}

		

	/**
	 * Override toString to show place information
	 */
	
	public String toString() {
		return  placeName + LINE_SEPARATOR + placeDescription;
	}

}
