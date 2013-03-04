package tp.pr3.cityLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;
import static tp.pr3.Constants.*;
import tp.pr3.City;
import tp.pr3.NavigationModule;
import tp.pr3.Place;
import tp.pr3.RobotEngine;
import tp.pr3.Street;
import tp.pr3.cityLoader.cityLoaderExceptions.WrongCityFormatException;
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
	private ArrayList<Item> items;

	public CityLoaderFromTxtFile() {
		this.places = new ArrayList<Place>();
		this.streets = new ArrayList<Street>();
		this.items = new ArrayList<Item>();
	}

	public CityLoaderFromTxtFile(InputStream file) throws IOException,
			WrongCityFormatException {
		City city = new City();

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(file));

			String fileLine = bufferedReader.readLine();
			if (!fileLine.equalsIgnoreCase("BeginCity"))
				throw new WrongCityFormatException();

			loadPlaces(places);
			loadStreets(streets);
			loadItems(items);

			fileLine = bufferedReader.readLine();
			if (!fileLine.equalsIgnoreCase("EndCity"))
				throw new WrongCityFormatException();

			/*
			 * { fileLine = bufferedReader.readLine(); if
			 * (fileLine.equalsIgnoreCase("BeginPlaces")) { loadPlaces(places,
			 * num); }else{ throw new
			 * WrongCityFormatException(BEGIN_PLACES_NOT_FOUND); } if
			 * (fileLine.equalsIgnoreCase("BeginStreets")) {
			 * loadStreets(places,streets, num); }else{ throw new
			 * WrongCityFormatException(BEGIN_STREETS_NOT_FOUND); } if
			 * (fileLine.equalsIgnoreCase("BeginItems")) { loadItems(places,
			 * streets, num); }else{ throw new
			 * WrongCityFormatException(BEGIN_ITEMS_NOT_FOUND); }
			 * 
			 * 
			 * }
			 */
		} finally {
			bufferedReader.close();
		}
	}

	/*
	 * 
	 */

	private void loadItems(ArrayList<Item> items) throws IOException {
		
		String fileLine = bufferedReader.readLine();
		if (!fileLine.equalsIgnoreCase("BeginItems"))
			throw new WrongCityFormatException();

		int num = 0;
		while (!fileLine.equalsIgnoreCase("EndItems")) {
			fileLine = bufferedReader.readLine();
			loadItem(fileLine, num++);

		}
		throw new WrongCityFormatException();

	}


	private void loadPlaces(ArrayList<Place> places) throws IOException {

		String fileLine = bufferedReader.readLine();
		if (!fileLine.equalsIgnoreCase("BeginPlaces"))
			throw new WrongCityFormatException();

		int num = 0;
		while (!fileLine.equalsIgnoreCase("EndPlaces")) {
			fileLine = bufferedReader.readLine();
			loadPlace(fileLine, num++);

		}
		throw new WrongCityFormatException();

	}

	private void loadStreets(ArrayList<Street> streets) throws IOException {

		String fileLine = bufferedReader.readLine();
		if (!fileLine.equalsIgnoreCase("BeginStreets"))
			throw new WrongCityFormatException();

		int num = 0;
		while (!fileLine.equalsIgnoreCase("EndStreets"))
			fileLine = bufferedReader.readLine();
		loadStreet(fileLine, num++);

	}
	
	private void loadItem(String fileLine, int num) 
			throws WrongCityFormatException {
	/*	StringTokenizer st = new StringTokenizer(fileLine, " ");
		String Item = st.nextToken();
		String pos = st.nextToken();
		int posnum = Integer.parseInt(pos);
		if (num != posnum)
			throw new WrongCityFormatException();
		String name = st.nextToken();
		String description = st.nextToken();
		items.add(new Item
		// TODO Auto-generated method stub
	*/	
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
				isSpaceShip = spaceShip.equalsIgnoreCase("noSpaceShip");
				places.add(new Place(name, isSpaceShip, description));
			}
		}

	}

	private void loadStreet(String fileLine, int num)
			throws WrongCityFormatException {
		StringTokenizer st = new StringTokenizer(fileLine, " ");
		String words = st.nextToken();
		if (!words.equalsIgnoreCase("STREET"))
			throw new WrongCityFormatException();
		String pos = st.nextToken();
		int posnum = Integer.parseInt(pos);
		if (num != posnum)
			throw new WrongCityFormatException();
		if (!words.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();
		Place sourcePlace = st.nextToken();		
		String direction = st.nextToken();
		if (!words.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();
		Place targetPlace= st.nextToken();
		String isOpen = st.nextToken();
		boolean Open = Boolean.parseBoolean(isOpen);
		Open = isOpen.equalsIgnoreCase("open");
		String code = st.nextToken();
		streets.add(new Street(sourcePlace, direction, targetPlace, Open, code));

		// TODO Auto-generated method stub

	}

	public Place getInitialPlace() {
		return places.get(0);
	}

}