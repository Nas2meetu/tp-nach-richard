package tp.pr1;
import static tp.pr1.Constants.LINE_SEPARATOR;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
* @param  
*
* @return
*/


public class Place {
	
	/**
	 * 
	 */

	private String placeName;
	private boolean isSpaceship;
	private String placeDescription;
	
	/**
	 * 
	 * @param placeName
	 * @param isSpaceship
	 * @param placeDescription
	 */

	public Place(String placeName, boolean isSpaceship, String placeDescription) {
		this.placeName = placeName;
		this.isSpaceship = isSpaceship;
		this.placeDescription = placeDescription;
	}
	
	/**
	 * 
	 * @return
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
