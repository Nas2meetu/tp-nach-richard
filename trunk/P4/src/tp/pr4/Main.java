package tp.pr4;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import tp.pr4.cityLoader.CityLoaderFromTxtFile;
import tp.pr4.gui.MainWindow;

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
	
	
	/*public static void main(String[] args) {
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
				final MainWindow gameWindow = new MainWindow(robot);
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
	}*/
	
	/*public static void main(String[] args) {
		
		//tp.pr4.Main [-h] [-i <type>] [-m <mapfile>]
		//-h,--help Shows this help message
		//-i,--interface <type> The type of interface: console or swing
		//-m,--map <mapfile> File with the description of the city
	
		Options options= new Options();
		Option helpOption= new Option("h", "help", false, "Show this help message");
		Option interfacesOption = new Option("i", "interface", true, "The type of interface: console or swing");
		interfacesOption.setArgName("type");
		Option mapsOption = new Option("m", "map", true, "File with the description of the city");
		mapsOption.setArgName("mapfile");
		
		options.addOption(helpOption);
		options.addOption(interfacesOption);
		options.addOption(mapsOption);
		
		
		
	}*/
	public static void main(String[] args) {

		CityLoaderFromTxtFile fileLoader = null;
		City city = null;
		CommandLineParser cmdParser = null;
		CommandLine cmdLine = null;

		// Options parser

		Options options = new Options();
		Option helpOption = new Option("h", "help", false,
				"Show this help message");
		Option interfacesOption = new Option("i", "interface", true,
				"The type of interface: console or swing");
		Option mapsOption = new Option("m", "map", true,
				"File with the description of the city");
		mapsOption.setArgName("mapfile");

		options.addOption(helpOption);
		options.addOption(interfacesOption);
		options.addOption(mapsOption);

		try {
			cmdParser = new BasicParser();
			cmdLine = cmdParser.parse(options, args);

			if (cmdLine.hasOption("h")) {
				new HelpFormatter().printHelp(Main.class.getCanonicalName(),
						options);
			}

			else {
				String map = cmdLine.getOptionValue("m");
				if (map != null) {
					try {
						InputStream file = new FileInputStream(map);
						fileLoader = new CityLoaderFromTxtFile();
						city = fileLoader.loadCity(file); //madrid.txt
					} catch (FileNotFoundException e) {
						System.err.println(args[0]);
						System.exit(2);
					} catch (IOException e) {
						System.err.println();
						System.exit(2);
					}
				} else {
					System.out.println("Introduce a map please.");
				}

				String interfaces = cmdLine.getOptionValue("i");
				if (interfaces != null) {

					if (interfaces.equalsIgnoreCase("swing")) {
						RobotEngine robot = new RobotEngine(city,
								fileLoader.getInitialPlace(), Direction.NORTH);
						final MainWindow gameWindow = new MainWindow(robot);
						robot.startEngine();
					} else if (interfaces.equalsIgnoreCase("console")) {
						RobotEngine robot = new RobotEngine(city,
								fileLoader.getInitialPlace(), Direction.NORTH);
						robot.startEngine();
					} else {
						System.out.println("Console or Swing?");
					}

				} else {
					System.out.println("Introduce an interface please.");
				}
			}

		} catch (org.apache.commons.cli.ParseException e) {
			System.out.println(e.getMessage());
		}
	}
}
