package tp.pr3.cityLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import tp.pr3.City;
import tp.pr3.Place;
import tp.pr3.Street;
import tp.pr3.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr3.items.CodeCard;
import tp.pr3.items.Fuel;
import tp.pr3.items.Garbage;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 3
 */

public class CityLoaderFromTxtFile {

	private BufferedReader bufferedReader;
	private ArrayList<Place> places;
	private ArrayList<Street> streets;

	/**
	 * 
	 */

	public CityLoaderFromTxtFile() {
		this.places = new ArrayList<Place>();
		this.streets = new ArrayList<Street>();

	}

	/**
	 * Verify if file contains BeginCity and EndCity and execute loadPlaces,
	 * Streets and Items
	 * 
	 * @param file
	 *            file with information about city.
	 * 
	 * @throws IOException
	 * @throws WrongCityFormatException
	 */

	public City loadCity(InputStream file) throws java.io.IOException {

		bufferedReader = new BufferedReader(new InputStreamReader(file));
		String fileLine = bufferedReader.readLine();

		if (fileLine == null || !fileLine.equals("BeginCity"))
			throw new WrongCityFormatException();
		try {
			loadPlaces();
		} catch (WrongCityFormatException e) {
			throw new WrongCityFormatException();
		}

		fileLine = bufferedReader.readLine();
		if (fileLine == null || !fileLine.equalsIgnoreCase("EndCity"))
			throw new WrongCityFormatException();
		Street[] streetArray = new Street[streets.size()];
		streets.toArray(streetArray);
		return new City(streetArray);
	}

	private void loadPlaces() throws IOException, WrongCityFormatException {
		String fileLine;
		String[] line;
		fileLine = bufferedReader.readLine();
		if (fileLine == null || !fileLine.equals("BeginPlaces"))
			throw new WrongCityFormatException();

		int num = 0;
		fileLine = bufferedReader.readLine();
		if (fileLine == null) {
			throw new WrongCityFormatException();
		}
		line = fileLine.split(" ");
		if (line.length == 0)
			throw new WrongCityFormatException();
		while (!line[0].equals("EndPlaces")) {
			loadPlace(line, num++);
			fileLine = bufferedReader.readLine();
			if (fileLine == null)
				throw new WrongCityFormatException();
			line = fileLine.split(" ");
			if (line.length == 0)
				throw new WrongCityFormatException();

		}
	}

	private void loadPlace(String[] line, int num)
			throws WrongCityFormatException {

		/*
		 * line[0] => place keyword line[1] => integer in increasing order
		 * line[2] => any string line[3] => description line[4] =>
		 * spaceship/noSpaceship, any other value is error
		 */

		if (!line[0].equals("place") || line.length != 5)
			throw new WrongCityFormatException();

		int posnum;
		try {
			posnum = Integer.parseInt(line[1]);
			if (num != posnum)
				throw new WrongCityFormatException();
		} catch (Exception e) {
			throw new WrongCityFormatException();
		}

		String description = line[3].replaceAll("_", " ");

		if (!line[4].equalsIgnoreCase("nospaceship")
				&& !line[4].equalsIgnoreCase("spaceship"))
			throw new WrongCityFormatException();
		boolean isSpaceShip = Boolean.parseBoolean(line[4]);
		places.add(new Place(line[2], isSpaceShip, description));
	}

	/**
	 * Show initial place
	 * 
	 * @return place initial Place
	 */

	public Place getInitialPlace() {
		return places.get(0);
	}
}