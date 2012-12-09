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

	public City() {
		this.cityMap = new Street[20];
	
	}
	
	public Street[] getCityMap() {
		return cityMap;
	}

	public int getCitySize() {
		int i;
		for (i = 0; i < cityMap.length && cityMap != null ; i++) {	
		}	
		return i;
	}

	public Street lookForStreet(Place source, Direction direction) {
		Street street = null;
		for (int i = 0; i < getCitySize(); i++) {
			if (cityMap[i] != null && cityMap[i].comeOutFrom(source, direction)){
				street = cityMap[i];
			}
		}return street;
	}

}
