package tp.pr3.cityLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static tp.pr3.Constants.*;
import tp.pr3.City;
import tp.pr3.Direction;
import tp.pr3.NavigationModule;
import tp.pr3.Place;
import tp.pr3.RobotEngine;
import tp.pr3.Street;
import tp.pr3.cityLoader.cityLoaderExceptions.WrongCityFormatException;

import tp.pr3.items.CodeCard;
import tp.pr3.items.Fuel;
import tp.pr3.items.Garbage;
import tp.pr3.items.Item;

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
	private Street[] cityMap;

	/**
	 * 
	 */

	public CityLoaderFromTxtFile() {
		this.places = new ArrayList<Place>();
		this.streets = new ArrayList<Street>();
		this.cityMap = new Street[0];
	}

	/**
	 * 
	 * @param file
	 *            file with information about city.
	 * @throws IOException
	 * @throws WrongCityFormatException
	 */

	public City loadCity(InputStream file) throws java.io.IOException {

		bufferedReader = new BufferedReader(new InputStreamReader(file));
		String fileLine = bufferedReader.readLine();
		if (!fileLine.equalsIgnoreCase("BeginCity"))
			throw new WrongCityFormatException();

		loadPlaces(places);
		loadStreets(streets);
		loadItems();

		fileLine = bufferedReader.readLine();
		if (!fileLine.equalsIgnoreCase("EndCity"))
			throw new WrongCityFormatException();
		Street[] streetArray= new Street[streets.size()];
		streets.toArray(streetArray);
		return new City(streetArray);

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
		if (!fileLine.equalsIgnoreCase("BeginItems"))
			throw new WrongCityFormatException();

		int num = 0;
		fileLine = bufferedReader.readLine();
		while (!fileLine.equalsIgnoreCase("EndItems")) {
			loadItem(fileLine, num++);
			fileLine = bufferedReader.readLine();
		}
		// throw new WrongCityFormatException();

	}

	/**
	 * Verified if text line content BeginPlaces and EndPlaces identifiers and
	 * execute loadPlace
	 * 
	 * @param places
	 * @throws IOException
	 */

	private void loadPlaces(ArrayList<Place> places) throws IOException {

		String fileLine = bufferedReader.readLine();
		if (!fileLine.equalsIgnoreCase("BeginPlaces"))
			throw new WrongCityFormatException();

		int num = 0;
		fileLine = bufferedReader.readLine();
		while (!fileLine.equalsIgnoreCase("EndPlaces")) {
			loadPlace(fileLine, num++);
			fileLine = bufferedReader.readLine();
		}

	}

	private void loadPlace(String fileLine, int num)
			throws WrongCityFormatException {
		StringTokenizer st = new StringTokenizer(fileLine, " ");
		String words = st.nextToken();
		if (!words.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();
		else {
			String pos = st.nextToken();
			int posnum = Integer.parseInt(pos);
			if (num != posnum)
				throw new WrongCityFormatException();
			else {
				String name = st.nextToken();
				String description = st.nextToken();
				description = description.replaceAll("_", " ");
				String spaceShip = st.nextToken();
				boolean isSpaceShip = Boolean.parseBoolean(spaceShip);
				isSpaceShip = spaceShip.equalsIgnoreCase("spaceShip");
				places.add(new Place(name, isSpaceShip, description));
			}
		}

	}

	/**
	 * Verified if text line content BeginStreets and EndStreets identifiers and
	 * execute loadStreet
	 * 
	 * @param streets
	 * @throws IOException
	 */

	private void loadStreets(ArrayList<Street> streets) throws IOException {

		String fileLine = bufferedReader.readLine();
		if (!fileLine.equalsIgnoreCase("BeginStreets"))
			throw new WrongCityFormatException();

		int num = 0;
		fileLine = bufferedReader.readLine();
		while (!fileLine.equalsIgnoreCase("EndStreets")) {
			loadStreet(fileLine, num++);
			fileLine = bufferedReader.readLine();
		}
	}

	/**
	 * Load Items from text file
	 * 
	 * @param fileLine
	 *            line of file to parse.
	 * @param num
	 *            number of items.
	 * @throws WrongCityFormatException
	 */

	private void loadItem(String fileLine, int num)
			throws WrongCityFormatException {

		StringTokenizer st = new StringTokenizer(fileLine, " ");
		String item = st.nextToken();
		switch (item) {
		case "FUEL": {
			loadFuelItem(st);
			break;
		}
		case "CODECARD": {
			loadCodeCardItem(st);
			break;
		}
		case "GARBAGE": {
			loadGarbageItem(st);
			break;
		}
		default:
			throw new WrongCityFormatException();

		}
	}

	private void loadGarbageItem(StringTokenizer st)
			throws WrongCityFormatException {
		String id = st.nextToken();
		String description = st.nextToken();
		description = description.replaceAll("_", " ");
		String rclMat = st.nextToken();
		int recycledMaterial = Integer.parseInt(rclMat);
		String words = st.nextToken();
		if (!words.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();
		String posPlaces = st.nextToken();
		int posplaces = Integer.parseInt(posPlaces);

		if (!places.get(posplaces).addItem(
				new Garbage(id, description, recycledMaterial)))
			throw new WrongCityFormatException();
	}

	private void loadCodeCardItem(StringTokenizer st)
			throws WrongCityFormatException {
		String id = st.nextToken();
		String description = st.nextToken();
		description = description.replaceAll("_", " ");
		String code = st.nextToken();
		String words = st.nextToken();
		if (!words.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();
		String posPlaces = st.nextToken();
		int posPlace = Integer.parseInt(posPlaces);
		if (!places.get(posPlace).addItem(new CodeCard(id, description, code)))
			throw new WrongCityFormatException();
	}

	private void loadFuelItem(StringTokenizer st)
			throws WrongCityFormatException {
		String id = st.nextToken();
		String description = st.nextToken();
		description = description.replaceAll("_", " ");
		String fuel = st.nextToken();
		int power = Integer.parseInt(fuel);
		String duration = st.nextToken();
		int times = Integer.parseInt(duration);
		String words = st.nextToken();
		if (!words.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();
		String posPlaces = st.nextToken();
		int posPlace = Integer.parseInt(posPlaces);
		if (!places.get(posPlace).addItem(
				new Fuel(id, description, power, times)))
			throw new WrongCityFormatException();
	}

	/**
	 * Load Places from text file
	 * 
	 * @param fileLine
	 *            line of file to parse.
	 * @param num
	 *            number of places.
	 * @throws WrongCityFormatException
	 */

	/**
	 * Load Places from text file
	 * 
	 * @param fileLine
	 *            line of file to parse.
	 * @param num
	 *            number of streets.
	 * @throws WrongCityFormatException
	 */

	private void loadStreet(String fileLine, int num)
			throws WrongCityFormatException {
		StringTokenizer st = new StringTokenizer(fileLine, " ");

		/* look for word "street" */

		String words = st.nextToken();
		if (!words.equalsIgnoreCase("STREET"))
			throw new WrongCityFormatException();

		/* look for word "place" */

		String pos = st.nextToken();
		int posStreet = Integer.parseInt(pos);
		if (num != posStreet)
			throw new WrongCityFormatException();

		String wordsPlace = st.nextToken();
		if (!wordsPlace.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();

		/* load sourcePlace */

		String pos2 = st.nextToken();
		int posSource = Integer.parseInt(pos2);

		if (places.size() < posSource)
			throw new WrongCityFormatException();

		Place sourcePlace = places.get(posSource);

		/* load direction */

		Direction direction = generateDirection(st);

		/* look for word "place" */
		String words2 = st.nextToken();
		if (!words2.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();

		/* load target place */

		String pos3 = st.nextToken();
		int posTarget = Integer.parseInt(pos3);
		if (places.size() < posTarget)
			throw new WrongCityFormatException();
		Place targetPlace = places.get(posTarget);

		/* load or not boolean Open/Close */

		String isOpen = st.nextToken();
		boolean Open = Boolean.parseBoolean(isOpen);

		if (isOpen.equalsIgnoreCase("CLOSE")) { /* load code to open street */
			Open = false;
			String code = st.nextToken();
			streets.add(new Street(sourcePlace, direction, targetPlace, Open,
					code));
		} else {
			Open = true;
			String code = null;
			streets.add(new Street(sourcePlace, direction, targetPlace, Open,
					code));
		}

	}

	private Direction generateDirection(StringTokenizer st)
			throws WrongCityFormatException {
		String dir = st.nextToken();
		Direction direction;
		if (dir.equalsIgnoreCase("NORTH"))
			direction = Direction.NORTH;
		else if (dir.equalsIgnoreCase("SOUTH"))
			direction = Direction.SOUTH;
		else if (dir.equalsIgnoreCase("EAST"))
			direction = Direction.EAST;
		else if (dir.equalsIgnoreCase("WEST"))
			direction = Direction.WEST;
		else
			throw new WrongCityFormatException();
		return direction;
	}

	/**
	 * 
	 * @return
	 */

	public Place getInitialPlace() {
		return places.get(0);
	}

}