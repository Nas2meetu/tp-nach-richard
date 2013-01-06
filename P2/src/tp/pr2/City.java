package tp.pr2;



/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
*/

public class City {

	private Street[] cityMap;

	public City(Street[] cityMap) {
		this.cityMap = cityMap;
	}

	/**
	 * 
	 * Define size of array map.
	 * 
	 */
	
	public City() {
		this.cityMap = new Street[20];
	
	}
	
	/**
	 * 
	 * Return a public method (cityMap) of a private attribute (CityMap).
	 * 
	 * @return cityMap is map of city.
	 */
	
	public Street[] getCityMap() {
		return cityMap;
	}
	
	/**
	 * 
	 * Return a public method (i) of a private attribute (CitySize).
	 * 
	 * @return cityMap.length is a size of map.
	 */
	
	public int getCitySize() {
		int i;
		for (i = 0; i < cityMap.length && cityMap != null ; i++) {	
		}	
		return i;
	}

	
	/**
	 * 
	 * Constructor of two parameters to define lookForStreet.
	 * 
	 * 
	 * @param source
	 * @param direction
	 * 
	 * 
	 * @return street
	 */
	
	public Street lookForStreet(Place source, Direction direction) {
		Street street = null;
		for (int i = 0; i < getCitySize(); i++) {
			if (cityMap[i] != null && cityMap[i].comeOutFrom(source, direction)){
				street = cityMap[i];
			}
		}return street;
	}

}
