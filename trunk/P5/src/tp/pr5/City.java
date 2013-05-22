package tp.pr5;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          This class represents the city where the robot is wandering. It
 *          contains information about the streets and the places in the city
 */

public class City {

	private Street[] cityMap;

	/**
	 * Constructor of cityMap. Creates a city using an array of streets.
	 * 
	 * @param cityMap
	 *            map of city.
	 */

	public City(Street[] cityMap) {
		this.cityMap = cityMap;
	}

	/**
	 * Define size of array map.
	 */

	public City() {
		this.cityMap = new Street[20];

	}

	/**
	 * Return a public method (cityMap) of a private attribute (CityMap).
	 * 
	 * @return cityMap is map of city.
	 */

	public Street[] getCityMap() {
		return cityMap;
	}

	/**
	 * Return size of a array map
	 * 
	 * @return i is a size of map.
	 */

	public int getCitySize() {
		int i;
		for (i = 0; i < cityMap.length && cityMap != null; i++) {
		}
		return i;
	}

	/**
	 * Looks for the street that starts from the given place in the given
	 * direction.
	 * 
	 * @param source
	 *            is one side of street. The place where to look for the street
	 * 
	 * @param direction
	 *            is direction that robot is looking at. The direction to look
	 *            for the street.
	 * @return street a street of map.
	 */

	public Street lookForStreet(Place source, Direction direction) {
		Street street = null;
		for (int i = 0; i < getCitySize(); i++) {
			if (cityMap[i] != null && cityMap[i].comeOutFrom(source, direction)) {
				street = cityMap[i];
			}
		}
		return street;
	}

}
