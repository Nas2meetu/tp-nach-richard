package tp.pr5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.cli.*;
import static tp.pr5.Constants.*;
import tp.pr5.cityLoader.CityLoaderFromTxtFile;
import tp.pr5.console.Console;
import tp.pr5.console.ConsoleController;
import tp.pr5.gui.GUIController;
import tp.pr5.gui.MainWindow;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          Application entry-point. The application admits a parameter -m |
 *          --map with the name of the map file to be used, a parameter -i |
 *          --interface with the type of interface (console, swing or both) and
 *          a parameter -h | --help to show a help message about how to use this
 *          application.
 * 
 *          If no arg is specified (or more than one file is given), it prints
 *          an error message (in System.err) and the application finishes with
 *          an error code (-1).
 * 
 *          If the map file cannot be read (or it does not exist), the
 *          application ends with a different error code (-2).
 * 
 *          If the interface arg is not correct (console, swing or both) the
 *          application prints a message and the application finishes with an
 *          error code (-3). Otherwise, the simulation starts and eventually the
 *          application will end normally (return code 0).
 */

public class Main {

	/**
	 * Commons-Cli Load file with places, streets and , create city and start
	 * game's interface, console or both.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		CityLoaderFromTxtFile fileLoader = null;
		City city = null;
		CommandLineParser cmdParser = null;
		CommandLine cmdLine = null;
		String interfaces = null;

		// Options parser

		Options options = new Options();
		Option helpOption = new Option("h", "help", false, SHOW_HELP_MESSAGE);
		Option interfacesOption = new Option("i", "interface", true,
				TYPE_INTERFACE);
		interfacesOption.setArgName("type");
		Option mapsOption = new Option("m", "map", true, FILE_DESCRIPTION_CITY);
		mapsOption.setArgName("mapfile");

		options.addOption(helpOption);
		options.addOption(interfacesOption);
		options.addOption(mapsOption);

		try {
			cmdParser = new BasicParser();
			cmdLine = cmdParser.parse(options, args);

			if (cmdLine.hasOption("h")) {
				System.out.println(EXECUTE_WITH_PAREMETERS);
				new HelpFormatter().printHelp(Main.class.getCanonicalName()
						+ EXECUTE_PAREMETERS, options);
				return;
			}

			if (cmdLine.hasOption("i")) {
				interfaces = cmdLine.getOptionValue("i");
				if (!interfaces.equalsIgnoreCase("swing")
						&& !interfaces.equalsIgnoreCase("console")
						&& !interfaces.equalsIgnoreCase("both")) {
					System.err.println(WRONG_INTERFACE);
					System.exit(3);

				}
			}

			// Load map

			if (cmdLine.hasOption("m")) {
				String map = cmdLine.getOptionValue("m");
				if (map != null) {
					try {
						InputStream file = new FileInputStream(map);
						fileLoader = new CityLoaderFromTxtFile();
						city = fileLoader.loadCity(file);
					} catch (FileNotFoundException e) {
						System.err.println(ERROR_READING_MAP);
						System.exit(2);
					} catch (IOException e) {
						System.err.println("Format error: ");
						System.err.println(e.getMessage());
						System.exit(2);
					}
				} else {
					System.err.println(MAP_NOT_SPECIFIED);
					System.exit(1);
				}

				// Load interface

				interfaces = cmdLine.getOptionValue("i");
				if (interfaces != null) {
					RobotEngine robot = new RobotEngine(city,
							fileLoader.getInitialPlace(), Direction.NORTH);

					// Load only swing interface

					if (interfaces.equalsIgnoreCase("swing")) {
						startGuiInterface(robot);
						// Load console only interface

					} else if (interfaces.equalsIgnoreCase("console")) {
						startConsoleInterface(robot);
						// Load swing and console interface (but interface only
						// read mode)

					} else if (interfaces.equalsIgnoreCase("both")) {
						startBothInterfaces(robot);

					} else {
						System.err.println(WRONG_INTERFACE);
						System.exit(1);
					}

				} else {
					System.err.println(INTERFACE_NOT_SPECIFIED);
					System.exit(1);
				}
			} else {
				System.err.println(MAP_NOT_SPECIFIED);
				System.exit(1);
			}
		} catch (org.apache.commons.cli.ParseException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void startGuiInterface(RobotEngine robot) {
		GUIController gc = new GUIController(robot);
		final MainWindow mainWindow = new MainWindow(gc);
		gc.startGuiController();
	}

	private static void startConsoleInterface(RobotEngine robot) {
		ConsoleController cc = new ConsoleController(robot);
		Console c = new Console();
		robot.addEngineObserver(c);
		robot.addNavigationObserver(c);
		robot.addItemContainerObserver(c);
		cc.startController();
	}

	private static void startBothInterfaces(RobotEngine robot) {
		GUIController gc = new GUIController(robot);
		Console c = new Console();
		robot.addEngineObserver(c);
		robot.addNavigationObserver(c);
		robot.addItemContainerObserver(c);
		final MainWindow mainWindow = new MainWindow(gc);
		gc.startGuiController();
	}
}
