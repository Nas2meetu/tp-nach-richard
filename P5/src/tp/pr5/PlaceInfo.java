package tp.pr5;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          PlaceInfo defines a non-modifiable interface over a Place. It is
 *          employed by the classes that need to access the information
 *          contained in the place but that cannot modify the place itself.
 */

public interface PlaceInfo {

	/**
	 * Return the place description
	 * 
	 */
	public String getDescription();

	/**
	 * Return the place name
	 * 
	 */
	public String getName();

	/**
	 * Is this place the space ship?
	 * 
	 * true if the place represents a space ship
	 * 
	 */
	public boolean isSpaceship();

	public String toString();

}
