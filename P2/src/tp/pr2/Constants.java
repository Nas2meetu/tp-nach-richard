package tp.pr2;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 2
*
*/

public class Constants {
	
/**
 * 
 * Define messages for Wall·E , prompt and line_separator
 * 
 */
	
	public static final int INITIAL_POWER = 50;
	
	public static final int INITIAL_GARBAGE = 0;
	
	public static final int DEFAULT_TIMES = 1;
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	public static final String WALLE_SAYS = "WALL·E says: ";
	
	public static final String RECICLED_MATERIAL = "   * My recycled material is: ";
	
	public static final String POWER = "    * My Power is ";
	
	public static final String POWER2 = "   * My Power is ";
	
	public static final String STREET_CLOSE = WALLE_SAYS 
				+ "Arrggg, there is a street but it is closed!";

	public static final String NO_STREET = WALLE_SAYS
			+ "There is no street in direction ";
	
	public static final String MESSAGE_SCAN = "My items are:" + LINE_SEPARATOR;

	public static final String QUIT = WALLE_SAYS
			+ "I have communication problems. Bye bye";
	
	public static final String CONTAINER = WALLE_SAYS 
				+ "I am carrying the following items";
	
	public static final String CONTAINER_EMPTY = WALLE_SAYS +
			"My inventory is empty";
	
	public static final String ITEM_PROBLEMS = WALLE_SAYS +
				"I have problems using the object ";
	
	public static final String SHOW_PLACE = "The place contains these objects: ";
	
	public static final String PLACE_EMPTY = "The place is empty. There are no objects to pick" + LINE_SEPARATOR;
	
	public static final String CONTAINER_ITEM = WALLE_SAYS + 
			"I am happy! Now I have  ";
	
	public static final String PLACE_NO_ITEM = WALLE_SAYS +
			"Ooops, this place has not the object ";
	
	public static final String SCAN_NO_ITEM = WALLE_SAYS +
					"I have not such object";
	
	public static final String ITEM_CANT_USED = WALLE_SAYS +
					"What a pity! I have no more ";
	
	public static final String CONTAINER_REPEAT_ITEM = WALLE_SAYS +
			"I am stupid! I already have the object ";

	public static final String HELP = "The valid instructions for WALL-E are:"
			+ LINE_SEPARATOR + "     MOVE"
			+ LINE_SEPARATOR + "     TURN <LEFT | RIGHT>"
			+ LINE_SEPARATOR + "     PICK <id>"
			+ LINE_SEPARATOR + "     SCAN [ <id> ]"
			+ LINE_SEPARATOR + "     OPERATE <id>"
			+ LINE_SEPARATOR + "     HELP" 
			+ LINE_SEPARATOR + "     QUIT"
			+ LINE_SEPARATOR;

	public static final String END_GAME = WALLE_SAYS + "I am at my space ship. Bye Bye";

	public static final String END_FUEL = WALLE_SAYS + "I run out of fuel. I cannot move. Shutting down...";
	
	public static final String PROMPT = "WALL·E > ";

	public static final String BAD_INSTRUCTION = WALLE_SAYS + "I do not understand. Please repeat";
	
	public static final String MOVE = WALLE_SAYS + "Moving in direction ";
	
	public static final String LOOKING_DIRECTION = "WALL·E is looking at direction ";
}