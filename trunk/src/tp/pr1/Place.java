package tp.pr1;

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

import static tp.pr1.Constants.LINE_SEPARATOR;

public class Place {
	
	/**
	 * Define place with his attributes .
	 * @param placeName
	 * @param placeDescription
	 * @param isSpaceShip
	 */
	

	private String placeName;
	private boolean isSpaceship;
	private String placeDescription;

	public Place(String placeName, boolean isSpaceship, String placeDescription) {
		this.placeName = placeName;
		this.isSpaceship = isSpaceship;
		this.placeDescription = placeDescription;
	}

	public boolean isSpaceship() {
		return isSpaceship;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  placeName + LINE_SEPARATOR + placeDescription;
	}
	
	

}
