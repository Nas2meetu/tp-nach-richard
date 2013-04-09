package tp.pr4;

import static tp.pr4.Constants.*;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
				final MainWindow gameWindow = new MainWindow(robot);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						gameWindow.pack(); // compacta la ventana 
						gameWindow.setLocationRelativeTo(null);
						gameWindow.setVisible(true);
					}
				});
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
}
