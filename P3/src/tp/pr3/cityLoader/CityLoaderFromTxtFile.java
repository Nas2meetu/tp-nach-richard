package tp.pr3.cityLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;
import static tp.pr3.Constants.*;
import tp.pr3.City;
import tp.pr3.Place;
import tp.pr3.Street;
import tp.pr3.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr3.maploader.exceptions.WrongMapFormatException;


/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 3
 */

public class CityLoaderFromTxtFile {

	// private ArrayList<Place> places;

	private BufferedReader bufferedReader;
	private String fileLine;

	public CityLoaderFromTxtFile(InputStream file)throws IOException, WrongCityFormatException {
		City city = new City();
		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<Street> streets = new ArrayList<Street>();
		
		int num=0;
		
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(file));
			fileLine = bufferedReader.readLine();

			if (fileLine.equalsIgnoreCase("BeginCity")) {
				fileLine = bufferedReader.readLine();
				if (fileLine.equalsIgnoreCase("BeginPlaces")) {
					loadPlaces(places, num);
				}else{
					throw new WrongCityFormatException(BEGIN_PLACES_NOT_FOUND);
				}
				if (fileLine.equalsIgnoreCase("BeginStreets")) {
					loadStreets(places,streets, num);
				}else{
					throw new WrongCityFormatException(BEGIN_STREETS_NOT_FOUND);
				}
				if (fileLine.equalsIgnoreCase("BeginItems")) {
					loadItems(places, streets, num);
				}else{
					throw new WrongCityFormatException(BEGIN_ITEMS_NOT_FOUND);
				}
				
				
			}
		}catch (Exception e) {
			throw new WrongCityFormatException();
		} finally {
			bufferedReader.close();
		}
	}

	private void loadItems(ArrayList<Place> places, ArrayList<Street> streets,
			int num) {
		// TODO Auto-generated method stub
		
	}

	private void loadStreets(ArrayList<Place> places,
			ArrayList<Street> streets, int num) {
		// TODO Auto-generated method stub
		
	}

	private void loadPlaces(ArrayList<Place> places, int num) {
		// TODO Auto-generated method stub
		
	}

	public Place getInitialPlace() {
		return null;
	}

}