package tp.pr3;

import static tp.pr3.Constants.*;
import java.io.FileInputStream;
import java.io.IOException;
import tp.pr3.cityLoader.CityLoaderFromTxtFile;


/**
* 
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 3
*/
public class Main {



	/**
     *
     * Start the game
     *
     * @param args initial arguments for start game
     *
     */
   
             
            
       
    
    public static void main(String[] args) throws IOException{
    	City cityMap = null;
		if (args == null) {
			System.err.println ("Bad params."+ LINE_SEPARATOR+"Usage: java tp.pr3.Main <mapfile>"+LINE_SEPARATOR+
					LINE_SEPARATOR+"<mapfile> : file with the description of the city.");
			System.exit(1);
		}	
		else{
		CityLoaderFromTxtFile cityLoader = new CityLoaderFromTxtFile();
		FileInputStream file;
		try {
			file = new FileInputStream(args[0]);
			//cityMap = cityLoader.loadCity(file);				
		} catch (IOException e1) {
			System.err.println("Error reading the map file: "+args[0]+" (No existe el fichero o el directorio)");
			System.exit(2);
		}
		RobotEngine robot = new RobotEngine(cityMap, cityLoader.getInitialPlace(), Direction.NORTH);
		robot.startEngine();

		}
		
    } 
  }
   

