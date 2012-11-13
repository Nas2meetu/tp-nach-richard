package tp.pr1;
import static tp.pr1.Constants.LINE_SEPARATOR;

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
	
	/**
	 * 
	 * @param placeName is place name
	 * @param isSpaceship is place which where Spaceship stays
	 * @param placeDescription is information about place
	 */

	public Place(String placeName, boolean isSpaceship, String placeDescription) {
		this.placeName = placeName;
		this.isSpaceship = isSpaceship;
		this.placeDescription = placeDescription;
	}
	
	/**
	 * 
	 * @return isSpaceship
	 */

	public boolean isSpaceship() {
		return isSpaceship;
	}

	
	/**
	 * 
	 */
	
	public String toString() {
		return  placeName + LINE_SEPARATOR + placeDescription;
	}
	
	

}
