package tp.pr3.cityLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import tp.pr3.City;
import tp.pr3.Direction;

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
	private String fileLine;

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
		fileLine = bufferedReader.readLine();

		if (fileLine == null || !fileLine.equalsIgnoreCase("BeginCity"))
			throw new WrongCityFormatException();

		loadPlaces(places);
		loadStreets(streets);
		loadItems();

		fileLine = bufferedReader.readLine();
		if (fileLine == null || !fileLine.equalsIgnoreCase("EndCity"))
			throw new WrongCityFormatException();
		Street[] streetArray = new Street[streets.size()];
		streets.toArray(streetArray);
		return new City(streetArray);

	}

	/**
	 * Verify if text line content BeginPlaces and EndPlaces identifiers and
	 * execute loadPlace
	 * 
	 * @param places
	 * @throws IOException
	 */

	private void loadPlaces(ArrayList<Place> places) throws IOException {

		fileLine = bufferedReader.readLine();
		if (fileLine == null || !fileLine.equalsIgnoreCase("BeginPlaces"))
			throw new WrongCityFormatException();

		int num = 0;
		fileLine = bufferedReader.readLine();
		if (fileLine == null)
			throw new WrongCityFormatException();
		// StringTokenizer token2 = new StringTokenizer(fileLine, " ");
		// String words2 = token2.nextToken();
		while (!fileLine.equalsIgnoreCase("EndPlaces")) {
			loadPlace(num++);
			fileLine = bufferedReader.readLine();
			if (fileLine == null) {
				throw new WrongCityFormatException();
			}

		}
	}

	/**
	 * Load Places from fileLine
	 * 
	 * @param num
	 *            number of places.
	 * @throws IOException
	 */

	private void loadPlace(int num) throws IOException {
		StringTokenizer token = new StringTokenizer(fileLine);
		String word = token.nextToken();
		if (!token.hasMoreElements() || !word.equalsIgnoreCase("Place"))
			throw new WrongCityFormatException();

		String pos = token.nextToken();
		ifMoreToken(token);

		int posnum = Integer.parseInt(pos);
		if (num != posnum)
			throw new WrongCityFormatException();

		String name = token.nextToken();
		ifMoreToken(token);

		String description = token.nextToken();
		ifMoreToken(token); 
		description = description.replaceAll("_", " ");

		String spaceShip = token.nextToken();
		if (!spaceShip.equalsIgnoreCase("spaceShip")
				&& !spaceShip.equalsIgnoreCase("noSpaceShip"))
			throw new WrongCityFormatException();
		boolean isSpaceShip = Boolean.parseBoolean(spaceShip);
		places.add(new Place(name, isSpaceShip, description));
		
	}
	/**
	 * Verify if has got more elements, else throw exception
	 * 
	 * @param token
	 * @throws WrongCityFormatException
	 */
	private void ifMoreToken(StringTokenizer token)
			throws WrongCityFormatException {
		if (!token.hasMoreTokens())
			throw new WrongCityFormatException();
	}

	/**
	 * Verify if text line content BeginStreets and EndStreets identifiers and
	 * execute loadStreet
	 * 
	 * @param streets
	 * @throws IOException
	 */

	private void loadStreets(ArrayList<Street> streets) throws IOException {

		fileLine = bufferedReader.readLine();

		if (fileLine == null || !fileLine.equalsIgnoreCase("BeginStreets"))
			throw new WrongCityFormatException();

		int num = 0;
		fileLine = bufferedReader.readLine();
		if (fileLine == null)
			throw new WrongCityFormatException();
		StringTokenizer token2 = new StringTokenizer(fileLine, " ");
		String words2 = token2.nextToken();
		while (!words2.equalsIgnoreCase("EndStreets")) {
			loadStreet(num++);
			fileLine = bufferedReader.readLine();
			if (fileLine == null)
				throw new WrongCityFormatException();
			StringTokenizer token3 = new StringTokenizer(fileLine, " ");
			if (!token3.hasMoreTokens())
				throw new WrongCityFormatException();
			words2 = token3.nextToken();
		}
	}

	/**
	 * Load Streets from text file
	 * 
	 * @param fileLine
	 *            line of file to parse.
	 * 
	 * @param num
	 *            number of streets.
	 * 
	 * @throws WrongCityFormatException
	 */

	private void loadStreet(int num) throws WrongCityFormatException {
		StringTokenizer token = new StringTokenizer(fileLine, " ");

		/* look for word "street" */

		String words = token.nextToken();
		ifMoreToken(token);
		if (!token.hasMoreElements() || !words.equalsIgnoreCase("STREET"))
			throw new WrongCityFormatException();

		/* look for word "place" */

		String pos = token.nextToken();
		ifMoreToken(token);
		int posStreet = Integer.parseInt(pos);
		if (num != posStreet)
			throw new WrongCityFormatException();

		String wordsPlace = token.nextToken();
		ifMoreToken(token);
		if (!wordsPlace.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();

		/* load sourcePlace */

		String pos2 = token.nextToken();
		ifMoreToken(token);
		int posSource = Integer.parseInt(pos2);

		if (places.size() < posSource)
			throw new WrongCityFormatException();

		Place sourcePlace = places.get(posSource);

		/* load direction */

		String dir = token.nextToken();
		ifMoreToken(token);
		Direction direction = Direction.valueOf(dir.toUpperCase());

		/* look for word "place" */
		String words2 = token.nextToken();
		ifMoreToken(token);

		if (!words2.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();

		/* load target place */

		String pos3 = token.nextToken();
		ifMoreToken(token);

		int posTarget = Integer.parseInt(pos3);

		if (places.size() < posTarget)
			throw new WrongCityFormatException();

		Place targetPlace = places.get(posTarget);

		/* load or not boolean Open/Close */

		String isOpen = token.nextToken();

		boolean Open = Boolean.parseBoolean(isOpen);

		if (isOpen.equalsIgnoreCase("closed")) { /* load code to open street */
			Open = false;
			String code = token.nextToken();
			streets.add(new Street(sourcePlace, direction, targetPlace, Open,
					code));
		} else {
			Open = true;
			String code = null;
			streets.add(new Street(sourcePlace, direction, targetPlace, Open,
					code));
		}

	}

	/**
	 * Verified if text line content BeginItems and EndItems identifiers and
	 * execute loadItem
	 * 
	 * @param items
	 * @throws IOException
	 */

	private void loadItems() throws IOException {

		String fileLine = bufferedReader.readLine();
		if (fileLine == null || !fileLine.equalsIgnoreCase("BeginItems"))
			throw new WrongCityFormatException();

		int num = 0;
		fileLine = bufferedReader.readLine();
		while (fileLine == null || !fileLine.equalsIgnoreCase("EndItems")) {
			loadItem(fileLine, num++);
			fileLine = bufferedReader.readLine();
			if (fileLine == null)
				throw new WrongCityFormatException();
		}
		// throw new WrongCityFormatException();

	}

	/**
	 * Load Items from text file
	 * 
	 * @param fileLine
	 *            line of file to parse.
	 * @param num
	 *            number of items.
	 * 
	 * @throws WrongCityFormatException
	 */

	private void loadItem(String fileLine, int num)
			throws WrongCityFormatException {

		StringTokenizer token = new StringTokenizer(fileLine, " ");
		String item = token.nextToken().toUpperCase();
		ifMoreToken(token);
		switch (item) {
		case "FUEL": {
			loadFuelItem(token, num++);
			break;
		}
		case "CODECARD": {
			loadCodeCardItem(token, num++);
			break;
		}
		case "GARBAGE": {
			loadGarbageItem(token, num++);
			break;
		}
		default:
			throw new WrongCityFormatException();

		}
	}

	/**
	 * Load a garbage of type Item
	 * 
	 * @param token
	 *            sentence with garbage information
	 * @param num
	 *            count of number of garbage
	 * @throws WrongCityFormatException
	 */

	private void loadGarbageItem(StringTokenizer token, int num)
			throws WrongCityFormatException {
		String pos = token.nextToken();
		ifMoreToken(token);
		int posnum = Integer.parseInt(pos);
		if (num != posnum)
			throw new WrongCityFormatException();
		String id = token.nextToken();
		ifMoreToken(token);
		String description = token.nextToken();
		ifMoreToken(token);
		description = description.replaceAll("_", " ");
		String rclMat = token.nextToken();
		ifMoreToken(token);
		int recycledMaterial = Integer.parseInt(rclMat);
		String words = token.nextToken();
		ifMoreToken(token);
		if (!words.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();
		String posPlaces = token.nextToken();
		int posplaces = Integer.parseInt(posPlaces);

		if (!places.get(posplaces).addItem(
				new Garbage(id, description, recycledMaterial)))
			throw new WrongCityFormatException();
	}

	/**
	 * Load a codecard of type Item
	 * 
	 * @param token
	 *            sentence with codecard information
	 * @param num
	 *            count of number of codecard
	 * @throws WrongCityFormatException
	 */

	private void loadCodeCardItem(StringTokenizer token, int num)
			throws WrongCityFormatException {
		String pos = token.nextToken();
		ifMoreToken(token);
		int posnum = Integer.parseInt(pos);
		if (num != posnum)
			throw new WrongCityFormatException();
		String id = token.nextToken();
		ifMoreToken(token);
		String description = token.nextToken();
		ifMoreToken(token);
		description = description.replaceAll("_", " ");
		String code = token.nextToken();
		ifMoreToken(token);
		String words = token.nextToken();
		ifMoreToken(token);
		if (!words.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();
		String posPlaces = token.nextToken();
		int posPlace = Integer.parseInt(posPlaces);
		if (!places.get(posPlace).addItem(new CodeCard(id, description, code)))
			throw new WrongCityFormatException();
	}

	/**
	 * Load a fuel of type Item
	 * 
	 * @param token
	 *            sentence with fuel information
	 * @param num
	 *            count of number of fuel
	 * @throws WrongCityFormatException
	 */

	private void loadFuelItem(StringTokenizer token, int num)
			throws WrongCityFormatException {
		String pos = token.nextToken();
		ifMoreToken(token);
		int posnum = Integer.parseInt(pos);
		if (num != posnum)
			throw new WrongCityFormatException();
		String id = token.nextToken();
		ifMoreToken(token);
		String description = token.nextToken();
		ifMoreToken(token);
		description = description.replaceAll("_", " ");
		String fuel = token.nextToken();
		ifMoreToken(token);
		int power = Integer.parseInt(fuel);
		String duration = token.nextToken();
		ifMoreToken(token);
		int times = Integer.parseInt(duration);
		String words = token.nextToken();
		ifMoreToken(token);
		if (!words.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();
		String posPlaces = token.nextToken();
		int posPlace = Integer.parseInt(posPlaces);
		if (num != posPlace)
			throw new WrongCityFormatException();
		if (!places.get(posPlace).addItem(
				new Fuel(id, description, power, times)))
			throw new WrongCityFormatException();
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
