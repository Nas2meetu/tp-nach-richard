package tp.pr3;

import static tp.pr3.Constants.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import tp.pr3.cityLoader.CityLoaderFromTxtFile;


/**
* 
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 3
*/
public class Main {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Bad params.");
			System.err.println("Usage: java tp.pr3.Main <mapfile>" + LINE_SEPARATOR);
			System.err.println("<mapfile> : file with the description of the city.");
			System.exit(1);
		} else {
			try {
				InputStream file = new FileInputStream(args[0]);
				CityLoaderFromTxtFile fileLoader = new CityLoaderFromTxtFile();
				City city = fileLoader.loadCity(file);
				RobotEngine robot = new RobotEngine(city, fileLoader.getInitialPlace(), Direction.NORTH);
				robot.startEngine();
			} catch (FileNotFoundException e) {
				System.err.println(FILE_READ_ERROR);
				System.exit(2);
			} catch (IOException e) {
				System.err.println(FILE_FORMAT_ERROR);
				System.exit(2);
			}
		}
	}
}
