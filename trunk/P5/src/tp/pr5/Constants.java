package tp.pr5;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class Constants {

	/**
	 * 
	 * Define messages for Wall·E , prompt, errors and line_separator
	 * 
	 */

	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	public static final String WALLE_SAYS = "WALL·E says: ";

	public static final String BAD_INSTRUCTION = WALLE_SAYS
			+ "I do not understand. Please repeat";

	public static final String BAD_PARAMS = "Bad params.";

	public static final String BEGIN_PLACES_NOT_FOUND = "BeginPlaces not found in file";

	public static final String BEGIN_STREETS_NOT_FOUND = "BeginStreets not found in file";

	public static final String BEGIN_ITEMS_NOT_FOUND = "BeginItems not found in file";

	public static final String CONTAINER = WALLE_SAYS
			+ "I am carrying the following items";

	public static final String CONTAINER_EMPTY = WALLE_SAYS
			+ "My inventory is empty";

	public static final String CONTAINER_ITEM = WALLE_SAYS
			+ "I am happy! Now I have ";

	public static final String ITEM_NOT_EXIST = "You do not have any ";

	public static final String CONTAINER_REPEAT_ITEM = WALLE_SAYS
			+ "I am stupid! I had already the object ";

	public static final int DEFAULT_TIMES = 1;

	public static final String END_GAME = WALLE_SAYS
			+ "I am at my spaceship. Bye bye";

	public static final String END_FUEL = WALLE_SAYS
			+ "I run out of fuel. I cannot move. Shutting down...";

	public static final String ERROR_READING_MAP = "Error reading the map file: noExiste.txt (No existe el fichero o el directorio)";

	public static final String EXECUTE_PAREMETERS = " [-h] [-i <type>] [-m <mapfile>]";

	public static final String EXECUTE_WITH_PAREMETERS = "Execute this assignment with these parameters:";

	public static final String FILE_DESCRIPTION_CITY = "File with the description of the city";

	public static final String FILE_READ_ERROR = "Error reading the map file: ";

	public static final String FILE_READ_ERROR2 = " (No existe el fichero o el directorio)";

	public static final String FILE_FORMAT_ERROR = "Format error";

	public static final String HEAD_HELP = "The valid instructions for WALL-E are:"
			+ LINE_SEPARATOR;

	public static final String IMAGES_DONT_LOAD = "Error to load images";

	public static final Integer INITIAL_POWER = 100;

	public static final Integer INITIAL_GARBAGE = 0;

	public static final String INTERFACE_NOT_SPECIFIED = "Interface not specified";

	public static final String IN_MY_INVENTORY = " in my inventory";

	public static final String ITEM_PROBLEMS = WALLE_SAYS
			+ "I have problems using the object ";

	public static final String ITEM_CANT_USED = WALLE_SAYS
			+ "What a pity! I have no more ";

	public static final String LOOKING_DIRECTION = "WALL·E is looking at direction ";

	public static final String MAPFILE = "<mapfile> : file with the description of the city.";

	public static final String MAP_NOT_SPECIFIED = "Map file not specified";

	public static final String MESSAGE_SCAN = "My items are:" + LINE_SEPARATOR;

	public static final String MOVE = WALLE_SAYS + "Moving in direction ";

	public static final String NO_STREET = WALLE_SAYS
			+ "There is no street in direction ";

	public static final String NO_ITEM_CHOOSE = "You forgot Select the item";

	public static final String NO_WRITTE_ITEM = "You should first write the name of an item";

	public static final String PLACE_EMPTY = "The place is empty. There are no objects to pick"
			+ LINE_SEPARATOR;

	public static final String DROP_ITEM = "Great! I have dropped ";

	public static final String PLACE_NO_ITEM = WALLE_SAYS
			+ "Ooops, this place has not the object ";

	public static final String PLACE_REPEAT_ITEM = WALLE_SAYS
			+ "I am stupid! The Place already has the object ";

	public static final String POWER = "      * My power is ";

	public static final String PROMPT = "WALL·E> ";

	public static final String QUIT = WALLE_SAYS
			+ "I have communication problems. Bye bye";

	public static final String RECYCLED_MATERIAL = "      * My reclycled material is ";

	public static final String STREET_CLOSE = WALLE_SAYS
			+ "Arrggg, there is a street but it is closed!";

	public static final String SHOW_HELP_MESSAGE = "Shows this help message";

	public static final String SHOW_PLACE = "The place contains these objects:";

	public static final String SCAN_NO_ITEM = WALLE_SAYS
			+ "I have not such object";

	public static final String TYPE_INTERFACE = "The type of interface: console or swing";

	public static final String USAGE_MAPFILE = "Usage: java tp.pr4.Main <mapfile>";

	public static final String WRONG_INTERFACE = "Wrong type of interface";

}
