package tp.pr5.cityLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import tp.pr5.City;
import tp.pr5.Direction;
import tp.pr5.Place;
import tp.pr5.Street;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.items.CodeCard;
import tp.pr5.items.Fuel;
import tp.pr5.items.Garbage;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          City loader from a txt file
 */

public class CityLoaderFromTxtFile {

	private BufferedReader bufferedReader;
	private ArrayList<Place> places;
	private ArrayList<Street> streets;

	/**
	 * Constructor of two arrays to create city with places, streets and items.
	 */

	public CityLoaderFromTxtFile() {
		this.places = new ArrayList<Place>();
		this.streets = new ArrayList<Street>();

	}

	/**
	 * Verify if file contains word "BeginCity" and "EndCity" if exists execute
	 * loadPlaces, loadStreets and loadItems.
	 * 
	 * @param file
	 *            is a file with information about city.
	 * @return City map of city.
	 * 
	 * @throws java.io.IOException
	 */

	public City loadCity(InputStream file) throws java.io.IOException {
		places.clear();
		streets.clear();

		bufferedReader = new BufferedReader(new InputStreamReader(file));
		String fileLine = bufferedReader.readLine();

		/* find word "BeginCity" in line of file */

		if (fileLine == null || !fileLine.equals("BeginCity"))
			throw new WrongCityFormatException();
		try {
			loadPlaces();
			loadStreets();
			loadItems();
		} catch (WrongCityFormatException e) {
			throw new WrongCityFormatException(e);
		}

		/* find word "EndCity" in line of file */

		fileLine = bufferedReader.readLine();
		if (fileLine == null || !fileLine.equalsIgnoreCase("EndCity"))
			throw new WrongCityFormatException();
		Street[] streetArray = new Street[streets.size()];
		streets.toArray(streetArray);
		return new City(streetArray);
	}

	/**
	 * Verify if file has got places.
	 * 
	 * @throws IOException
	 * 
	 * @throws WrongCityFormatException
	 */

	private void loadPlaces() throws IOException, WrongCityFormatException {
		String fileLine;
		String[] line;
		fileLine = bufferedReader.readLine();

		/* find word "BeginPlaces" in line of file */

		if (fileLine == null || !fileLine.equals("BeginPlaces"))
			throw new WrongCityFormatException();

		int num = 0;
		fileLine = bufferedReader.readLine();
		if (fileLine == null) {
			throw new WrongCityFormatException();
		}

		/* delete initial and final " " of line */
		// line = fileLine.split("\"");
		if (fileLine.contains("\"")) {
			line = fileLine.split("\"");
			String desc = line[1];
			desc = desc.replaceAll(" ", "_");
			fileLine = line[0] + desc + line[2];
		}
		line = fileLine.split(" ");
		if (line.length == 0)
			throw new WrongCityFormatException();

		/* find word "EndPlacess" in line of file */

		while (!line[0].equals("EndPlaces")) {
			loadPlace(line, num++);
			fileLine = bufferedReader.readLine();
			if (fileLine == null)
				throw new WrongCityFormatException();
			if (fileLine.contains("\"")) {
				line = fileLine.split("\"");
				String desc = line[1];
				desc = desc.replaceAll(" ", "_");
				fileLine = line[0] + desc + line[2];
			}
			line = fileLine.split(" ");
			if (line.length == 0)
				throw new WrongCityFormatException();

		}
	}

	/**
	 * Load places to city.
	 * 
	 * @param line
	 *            is a line of text from file
	 * @param num
	 *            size of array of places.
	 * @throws WrongCityFormatException
	 */

	private void loadPlace(String[] line, int num)
			throws WrongCityFormatException {

		/*
		 * line[0] => place keyword line[1] => integer in increasing order
		 * line[2] => any string line[3] => description line[4] =>
		 * spaceship/noSpaceship, any other value is error
		 */

		/* verify if word "place" exist in line */

		if (!line[0].equals("place") || line.length != 5)
			throw new WrongCityFormatException();

		int posnum;
		try {
			posnum = Integer.parseInt(line[1]);
		} catch (Exception e) {
			throw new WrongCityFormatException(e);
		}
		if (num != posnum)
			throw new WrongCityFormatException();

		/* replace "_" by " " */

		String description = line[3].replaceAll("_", " ");

		/*
		 * verify if word "nospaceship" or "spaceship" exist in line and add
		 * place to array of places
		 */

		if (!line[4].equalsIgnoreCase("nospaceship")
				&& !line[4].equalsIgnoreCase("spaceship"))
			throw new WrongCityFormatException();
		if (line[4].equalsIgnoreCase("spaceship"))
			places.add(new Place(line[2], true, description));
		else
			places.add(new Place(line[2], false, description));

	}

	/**
	 * Verify if file has got streets.
	 * 
	 * @throws IOException
	 * @throws WrongCityFormatException
	 */

	private void loadStreets() throws IOException, WrongCityFormatException {
		String fileLine;
		String[] line;
		fileLine = bufferedReader.readLine();
		if (fileLine == null || !fileLine.equals("BeginStreets"))
			throw new WrongCityFormatException();

		int num = 0;
		fileLine = bufferedReader.readLine();
		if (fileLine == null) {
			throw new WrongCityFormatException();
		}
		line = fileLine.split(" ");
		if (line.length == 0)
			throw new WrongCityFormatException();
		while (!line[0].equals("EndStreets")) {
			loadStreet(line, num++);
			fileLine = bufferedReader.readLine();
			if (fileLine == null)
				throw new WrongCityFormatException();
			line = fileLine.split(" ");
			if (line.length == 0)
				throw new WrongCityFormatException();
		}
	}

	/** 
	 * Load streets to city.
	 * 
	 * @param line
	 *            is a line of text from file
	 * @param num
	 *            size of array of streets.
	 * @throws WrongCityFormatException
	 */

	private void loadStreet(String[] line, int num)
			throws WrongCityFormatException {

		int posnum, posSource, posTarget;
		Place sourcePlace;
		Place targetPlace;

		/**
		 * street 0 place 0 south place 1 open street 1 place 1 east place 2
		 * open street 2 place 2 north place 3 closed onetwothreefourfive
		 */
		if (!line[0].equals("street") || (line.length != 8 && line.length != 9))
			throw new WrongCityFormatException();

		try {
			posnum = Integer.parseInt(line[1]);
		} catch (Exception e) {
			throw new WrongCityFormatException(e);
		}
		if (num != posnum)
			throw new WrongCityFormatException();

		if (!line[2].equals("place"))
			throw new WrongCityFormatException();

		try {
			posSource = Integer.parseInt(line[3]);

		} catch (Exception e) {
			throw new WrongCityFormatException(e);
		}

		if (places.size() < posSource)
			throw new WrongCityFormatException();
		sourcePlace = places.get(posSource);
		Direction direction = Direction.valueOf(line[4].toUpperCase());

		if (!line[5].equals("place"))
			throw new WrongCityFormatException();

		try {
			posTarget = Integer.parseInt(line[6]);
		} catch (Exception e) {
			throw new WrongCityFormatException(e);
		}
		if (places.size() < posTarget)
			throw new WrongCityFormatException();
		targetPlace = places.get(posTarget);

		if (line[7].equalsIgnoreCase("closed") && line.length == 9)
			streets.add(new Street(sourcePlace, direction, targetPlace, false,
					line[8]));
		else if (line[7].equalsIgnoreCase("open") && line.length == 8)
			streets.add(new Street(sourcePlace, direction, targetPlace));
		else
			throw new WrongCityFormatException();
	}

	/**
	 * Verify if file has got items.
	 * 
	 * @throws IOException
	 * 
	 * @throws WrongCityFormatException
	 */

	private void loadItems() throws IOException, WrongCityFormatException {
		/**
		 * fuel 0 Petrol from_olds_heatings 10 3 place 0 fuel 1 Battery
		 * to_get_cracking -50 1 place 0 codecard 2 Card The_key_is_too_easy
		 * onetwothreefourfive place 1 garbage 3 Newspapers News_on_sport 30
		 * place 2
		 * 
		 */

		String fileLine;
		String[] line;

		fileLine = bufferedReader.readLine();
		if (fileLine == null || !fileLine.equals("BeginItems"))
			throw new WrongCityFormatException();

		int num = 0;
		fileLine = bufferedReader.readLine();
		if (fileLine == null) {
			throw new WrongCityFormatException();
		}
		if (fileLine.contains("\"")) {
			line = fileLine.split("\"");
			String desc = line[1];
			desc = desc.replaceAll(" ", "_");
			fileLine = line[0] + desc + line[2];
		}
		line = fileLine.split(" ");
		if (line.length == 0)
			throw new WrongCityFormatException();

		while (!line[0].equals("EndItems")) {
			loadItem(line, num++);
			fileLine = bufferedReader.readLine();
			if (fileLine == null)
				throw new WrongCityFormatException();
			if (fileLine.contains("\"")) {
				line = fileLine.split("\"");
				String desc = line[1];
				desc = desc.replaceAll(" ", "_");
				fileLine = line[0] + desc + line[2];
			}
			line = fileLine.split(" ");
			if (line.length == 0)
				throw new WrongCityFormatException();
		}
	}

	/** 
	 * Verify type of items has got line and process this item.
	 * 
	 * @param line
	 *            is a line of text from file
	 * @param num
	 *            size of array of items
	 * @throws WrongCityFormatException
	 */

	private void loadItem(String[] line, int num)
			throws WrongCityFormatException {
		if (line[0].equals("fuel")) {
			loadFuelItem(line, num++);
		} else if (line[0].equals("codecard")) {
			loadCodeCardItem(line, num++);
		} else if (line[0].equals("garbage")) {
			loadGarbageItem(line, num++);
		} else
			throw new WrongCityFormatException();

	}

	/**
	 * Load fuel items to map
	 * 
	 * @param line
	 *            is a line of text from file
	 * @param num
	 *            size of array of items
	 * @throws WrongCityFormatException
	 */

	private void loadFuelItem(String[] line, int num)
			throws WrongCityFormatException {

		int posnum, posPlace, power, times;

		if (!line[0].equals("fuel") || line.length != 8)
			throw new WrongCityFormatException();
		try {
			posnum = Integer.parseInt(line[1]);

		} catch (NumberFormatException e) {
			throw new WrongCityFormatException(e);
		}
		if (num != posnum)
			throw new WrongCityFormatException();

		String description = line[3].replaceAll("_", " ");

		try {
			power = Integer.parseInt(line[4]);
		} catch (NumberFormatException e) {
			throw new WrongCityFormatException(e);
		}

		try {
			times = Integer.parseInt(line[5]);
		} catch (NumberFormatException e) {
			throw new WrongCityFormatException(e);
		}

		if (!line[6].equalsIgnoreCase("place"))
			throw new WrongCityFormatException();

		try {
			posPlace = Integer.parseInt(line[7]);
		} catch (NumberFormatException e) {
			throw new WrongCityFormatException(e);
		}

		if (posPlace >= places.size())
			throw new WrongCityFormatException();
		if (!places.get(posPlace).addItem(
				new Fuel(line[2], description, power, times)))
			throw new WrongCityFormatException();

	}

	/**
	 * 
	 * Load garbage items to place.
	 * 
	 * @param line
	 *            is a line of text from file
	 * @param num
	 *            size of array of items
	 * @throws WrongCityFormatException
	 */

	private void loadGarbageItem(String[] line, int num)
			throws WrongCityFormatException {

		int posnum, posPlace, garbage;

		if (!line[0].equals("garbage") || line.length != 7)
			throw new WrongCityFormatException();

		try {
			posnum = Integer.parseInt(line[1]);

		} catch (NumberFormatException e) {
			throw new WrongCityFormatException(e);
		}
		if (num != posnum)
			throw new WrongCityFormatException();

		String description = line[3].replaceAll("_", " ");

		try {
			garbage = Integer.parseInt(line[4]);
		} catch (NumberFormatException e) {
			throw new WrongCityFormatException(e);
		}

		if (!line[5].equalsIgnoreCase("place"))
			throw new WrongCityFormatException();

		try {
			posPlace = Integer.parseInt(line[6]);
		} catch (NumberFormatException e) {
			throw new WrongCityFormatException(e);
		}

		if (posPlace >= places.size())
			throw new WrongCityFormatException();

		if (!places.get(posPlace).addItem(
				new Garbage(line[2], description, garbage)))
			throw new WrongCityFormatException();

	}

	/**
	 * 
	 * Load codeCard items to place.
	 * 
	 * @param line
	 *            is a line of text from file
	 * @param num
	 *            size of array of items
	 * @throws WrongCityFormatException
	 */

	private void loadCodeCardItem(String[] line, int num)
			throws WrongCityFormatException {
		int posnum, posPlace;
		if (!line[0].equals("codecard") || line.length != 7)
			throw new WrongCityFormatException();

		try {
			posnum = Integer.parseInt(line[1]);

		} catch (NumberFormatException e) {
			throw new WrongCityFormatException(e);
		}
		if (num != posnum)
			throw new WrongCityFormatException();

		String description = line[3].replaceAll("_", " ");

		if (!line[5].equalsIgnoreCase("place"))
			throw new WrongCityFormatException();

		try {
			posPlace = Integer.parseInt(line[6]);
		} catch (NumberFormatException e) {
			throw new WrongCityFormatException(e);
		}
		if (posPlace >= places.size())
			throw new WrongCityFormatException();

		if (!places.get(posPlace).addItem(
				new CodeCard(line[2], description, line[4])))
			throw new WrongCityFormatException();

	}

	/**
	 * Returns the place where the robot will start the simulation
	 * 
	 * @return The initial place
	 */

	public Place getInitialPlace() {
		return places.get(0);
	}
}