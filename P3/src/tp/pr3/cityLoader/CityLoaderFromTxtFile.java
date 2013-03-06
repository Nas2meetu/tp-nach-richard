package tp.pr3.cityLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import com.sun.java.util.jar.pack.Instruction.Switch;

import static tp.pr3.Constants.*;
import sun.security.provider.JavaKeyStore.CaseExactJKS;
import tp.pr3.City;
import tp.pr3.Direction;
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
	 * Load Items of text file
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

	/*
	 * Load Places of text file
	 */
	
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
		int posStreet = Integer.parseInt(pos);
		if (num != posStreet)
			throw new WrongCityFormatException();
		if (!words.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();
			
			/* mirar si existe ese place en array places*/
			
		String pos2 = st.nextToken();

		int posSource = Integer.parseInt(pos2);
		if (places.size()<posSource)
			throw new WrongCityFormatException();
		
		Place sourcePlace = places.get(posSource);		
		String direction = st.nextToken();
		int i;
		while(Direction.values().length<i){
			Direction.values().equals(direction);
		}
			
			
		if (!words.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();	
		String pos3 = st.nextToken();
		
		int posTarget = Integer.parseInt(pos3);
		if (places.size()<posTarget)
			throw new WrongCityFormatException();
		Place targetPlace= places.get(posTarget);
		String isOpen = st.nextToken();
		boolean Open = Boolean.parseBoolean(isOpen);
		
		if (isOpen.equalsIgnoreCase ("CLOSE")) {
			Open = false;
			String code = st.nextToken();
			streets.add(new Street(sourcePlace, direction, targetPlace, Open, code))
		}
		else {
			Open = true;
			String code = null;
			streets.add(new Street(sourcePlace, direction, targetPlace, Open, code));
		}

	}

	public Place getInitialPlace() {
		return places.get(0);
	}

}