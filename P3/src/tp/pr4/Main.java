package tp.pr4;

import static tp.pr4.Constants.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import tp.pr4.cityLoader.CityLoaderFromTxtFile;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 */
public class Main {

	/**
	 * 
	 * Load file with places, streets and items, create city and start game
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println(BAD_PARAMS);
			System.err.println(USAGE_MAPFILE + LINE_SEPARATOR);
			System.err.println(MAPFILE);
			System.exit(1);
		} else {
			try {
				InputStream file = new FileInputStream(args[0]);
				CityLoaderFromTxtFile fileLoader = new CityLoaderFromTxtFile();
				City city = fileLoader.loadCity(file);
				RobotEngine robot = new RobotEngine(city,
						fileLoader.getInitialPlace(), Direction.NORTH);
				robot.startEngine();
			} catch (FileNotFoundException e) {
				System.err
						.println(FILE_READ_ERROR + args[0] + FILE_READ_ERROR2);
				System.exit(2);
			} catch (IOException e) {
				System.err.println(FILE_FORMAT_ERROR);
				System.exit(2);
			}
		}
	}
}
