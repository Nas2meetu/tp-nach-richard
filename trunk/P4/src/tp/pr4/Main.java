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
				System.out
						.println("Execute this assignment with these parameters:");
				new HelpFormatter().printHelp(Main.class.getCanonicalName()
						+ " [-h] [-i <type>] [-m <mapfile>]", options);
				return;
			}

			if (cmdLine.hasOption("i")) {
				interfaces = cmdLine.getOptionValue("i");
				if (!interfaces.equalsIgnoreCase("swing")
						&& !interfaces.equalsIgnoreCase("console")) {
					System.err.println("Wrong type of interface");
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
								.println("Error reading the map file: noExiste.txt (No existe el fichero o el directorio)");
						System.exit(1);
					} catch (IOException e) {
						System.err.println("Format error: ");
						System.err.println(e.getMessage());
						System.exit(2);
					}
				} else {
					System.err.println("Map file not specified");
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
						System.err.println("Wrong type of interface");
						System.exit(1);
					}

				} else {
					System.err.println("Interface not specified");
					System.exit(1);
				}
			} else {
				System.err.println("Map file not specified");
				System.exit(1);
			}
		} catch (org.apache.commons.cli.ParseException e) {
			System.out.println(e.getMessage());
		}
	}
}
