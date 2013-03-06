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
	private ArrayList<Item> items;
	private City cityMap;
	
	/**
	 * 
	 */
	
	public CityLoaderFromTxtFile() {
		this.places = new ArrayList<Place>();
		this.streets = new ArrayList<Street>();
		/*this.items = new ArrayList<Item>();*/
	}
	
	/**
	 * 
	 * @param file file with information about city.
	 * @throws IOException
	 * @throws WrongCityFormatException
	 */
	
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
	
	/**
	 * Verified if text line content BeginItems and EndItems identifiers
	 * and execute loadItem 
	 * 
	 * @param items
	 * @throws IOException
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

	/**
	 * Verified if text line content BeginPlaces and EndPlaces identifiers
	 * and execute loadPlace
	 *  
	 * @param places
	 * @throws IOException
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

	/**
	 * Verified if text line content BeginStreets and EndStreets identifiers
	 * and execute loadStreet
	 * 
	 * @param streets
	 * @throws IOException
	 */
	
	private void loadStreets(ArrayList<Street> streets) throws IOException {

		String fileLine = bufferedReader.readLine();
		if (!fileLine.equalsIgnoreCase("BeginStreets"))
			throw new WrongCityFormatException();

		int num = 0;
		while (!fileLine.equalsIgnoreCase("EndStreets"))
			fileLine = bufferedReader.readLine();
		loadStreet(fileLine, num++);

	}
	
	/**
	 * Load Items from text file
	 * 
	 * @param fileLine line of file to parse.
	 * @param num number of items.
	 * @throws WrongCityFormatException
	 */

	private void loadItem(String fileLine, int num)
			throws WrongCityFormatException {
		 StringTokenizer st = new StringTokenizer(fileLine, " ");
		 String item = st.nextToken();
		 if (item.equalsIgnoreCase("FUEL")){
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
				/*	
				int posplaces = Integer.parseInt(posPlaces);
				places.add(new CodeCard(id, description, code));
				if (places.get(posplaces).equals(null))
					throw new WrongCityFormatException();*/
		 }	
		 else if (item.equalsIgnoreCase("CODECARD")){
				String id = st.nextToken();
				String description = st.nextToken();
				description = description.replaceAll("_", " ");
				String code = st.nextToken();
				String words = st.nextToken();
				if (!words.equalsIgnoreCase("PLACE"))
					throw new WrongCityFormatException();
				String posPlaces = st.nextToken();
				/*	
				int posplaces = Integer.parseInt(posPlaces);
				places.add(new CodeCard(id, description, code));
				if (places.get(posplaces).equals(null))
					throw new WrongCityFormatException();*/
		 } 
		 else if (item.equalsIgnoreCase("GARBAGE")){
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
				
				if (places.get(posplaces).equals(null))
					throw new WrongCityFormatException();
				else 
					places.add(posplace,new Garbage(id, description, recycledMaterial));
		 }	 
		 else
			 throw new WrongCityFormatException();

	}
	
	/**
	 * Load Places from text file
	 * 
	 * @param fileLine line of file to parse.
	 * @param num number of places.
	 * @throws WrongCityFormatException
	 */
	
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
	
	/**
	 * Load Places from text file
	 * 
	 * @param fileLine line of file to parse.
	 * @param num number of streets.
	 * @throws WrongCityFormatException
	 */

	private void loadStreet(String fileLine, int num)
			throws WrongCityFormatException {
		StringTokenizer st = new StringTokenizer(fileLine, " ");
		
		/* look for word "street" */
		
		String words = st.nextToken();
		if (!words.equalsIgnoreCase("STREET"))
			throw new WrongCityFormatException();
		
		/*  look for word "place" */
		
		String pos = st.nextToken();
		int posStreet = Integer.parseInt(pos);
		if (num != posStreet)
			throw new WrongCityFormatException();
		
		if (!words.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();		
		
		/* load sourcePlace */
		
		String pos2 = st.nextToken();
		int posSource = Integer.parseInt(pos2);

		if (places.size()<posSource)
			throw new WrongCityFormatException();
		
		Place sourcePlace = places.get(posSource);		
		
		/* load direction */
		
		generateDirection(st);
		
		private void generateDirection(StringTokenizer st) {
			switch (st.nextToken()) {
			
			case ("NORTH"): 
				Direction direction = Direction.NORTH;
				break; 
			case ("SOUTH"): 
				direction = Direction.SOUTH;
				break; 
			case ("EAST"): 
				direction = Direction.EAST;
				break; 
			case ("WEST"): 
				direction = Direction.WEST;
				break;
			default:
				break;
			
			}
			
			return direction;
		}
		
		/* look for word "place" */
	    String words2 = st.nextToken();
		if (!words2.equalsIgnoreCase("PLACE"))
			throw new WrongCityFormatException();	
		
		/* load target place */
		
		String pos3 = st.nextToken();
		int posTarget = Integer.parseInt(pos3);
		if (places.size()<posTarget)
			throw new WrongCityFormatException();
		Place targetPlace= places.get(posTarget);
		
		/* load or not boolean Open/Close */
		
		String isOpen = st.nextToken();
		boolean Open = Boolean.parseBoolean(isOpen);
		
		if (isOpen.equalsIgnoreCase ("CLOSE")) { /* load code to open street */
			Open = false;
			String code = st.nextToken();
			streets.add(new Street(sourcePlace, direction, targetPlace, Open, code));
		}
		else { 
			Open = true;
			String code = null;
			streets.add(new Street(sourcePlace, direction, targetPlace, Open, code));
		}

	}

	/**
	 * 
	 * @return
	 */

	public Place getInitialPlace() {
		return places.get(0);
	}

}