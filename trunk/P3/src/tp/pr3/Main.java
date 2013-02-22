package tp.pr3;

import static tp.pr3.Constants.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import tp.pr3.cityLoader.CityLoaderFromTxtFile;
import tp.pr3.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr3.items.CodeCard;
import tp.pr3.items.Fuel;
import tp.pr3.items.Garbage;

/**
* 
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 3
*/
public class Main {

   

    private static Street[] streets;
	private static Place[] places;
	

	/**
     *
     * Start the game
     *
     * @param args initial arguments for start game
     *
     */
   
             
            
       
    
    public static void main(String[] args) throws IOException{

		
    	FileInputStream file = null;	
    	City city = null;
    	RobotEngine robot = new RobotEngine(new City(streets), places[0], Direction.NORTH);
    			

    	try {
    		CityLoaderFromTxtFile mfile = new CityLoaderFromTxtFile();
    		File f = new File(args[0]); // "map.txt"
    		file = new FileInputStream(f);		
    		//city = mfile.loadCity(file);
    		
    		robot.startEngine();
    	
    		
    	} catch (FileNotFoundException e){
    		System.err.println("Error opening file");
    		
    	} catch (ArrayIndexOutOfBoundsException e){
    		
    			System.err.println("No map file specified." 
    			+ LINE_SEPARATOR +"Usage: tp.pr3.Main <mapFile>" + LINE_SEPARATOR );
    			System.exit(1);
    			
    			
    	//} catch (WrongCityFormatException e) {
    		
    		e.printStackTrace();
    	}		
    			
    		
    	} 
  }
   

