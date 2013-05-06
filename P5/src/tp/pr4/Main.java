package tp.pr4;

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
import static tp.pr4.Constants.*;
import tp.pr4.cityLoader.CityLoaderFromTxtFile;
import tp.pr4.gui.MainWindow;
import tp.pr4.instructions.exceptions.InstructionExecutionException;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 */
public class Main {

	/**
	 * 
	 * Commons-Cli
	 * Load file with places, streets and items, create city and start
	 * game's interface and console
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
		Option helpOption = new Option("h", "help", false,
				SHOW_HELP_MESSAGE);
		Option interfacesOption = new Option("i", "interface", true,
				TYPE_INTERFACE);
		interfacesOption.setArgName("type");
		Option mapsOption = new Option("m", "map", true,
				FILE_DESCRIPTION_CITY);
		mapsOption.setArgName("mapfile");

		options.addOption(helpOption);
		options.addOption(interfacesOption);
		options.addOption(mapsOption);

		try {
			cmdParser = new BasicParser();
			cmdLine = cmdParser.parse(options, args);

			if (cmdLine.hasOption("h")) {
				System.out
						.println(EXECUTE_WITH_PAREMETERS);
				new HelpFormatter().printHelp(Main.class.getCanonicalName()
						+ EXECUTE_PAREMETERS, options);
				return;
			}

			if (cmdLine.hasOption("i")) {
				interfaces = cmdLine.getOptionValue("i");
				if (!interfaces.equalsIgnoreCase("swing")
						&& !interfaces.equalsIgnoreCase("console")) {
					System.err.println(WRONG_INTERFACE);
					System.exit(1);

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
						System.err
								.println(ERROR_READING_MAP);
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
					if (interfaces.equalsIgnoreCase("swing")) {
						RobotEngine robot = new RobotEngine(city,
								fileLoader.getInitialPlace(), Direction.NORTH);
						final MainWindow gameWindow = new MainWindow(robot);
						try {
							robot.startEngine();
						} catch (InstructionExecutionException e) {
							System.out.println(e.getMessage());
						}
					} else if (interfaces.equalsIgnoreCase("console")) {
						RobotEngine robot = new RobotEngine(city,
								fileLoader.getInitialPlace(), Direction.NORTH);
						try {
							robot.startEngine();
						} catch (InstructionExecutionException e) {
							System.out.println(e.getMessage());
						}
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
}
