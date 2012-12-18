package tp.pr2;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
*/

public class Constants {
	
/**
 * Define messages for Wall·E , prompt and line_separator
 */
	public static final int INITIAL_POWER = 50;
	
	public static final int INITIAL_GARBAGE = 0;
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	public static final String WALLE_SAYS = "WALL·E says: ";
	
	public static final String STREET_CLOSE = WALLE_SAYS 
				+ "Arrggg, there is a street but it is closed!";

	public static final String NO_STREET = WALLE_SAYS
			+ "There is no street in direction ";

	public static final String QUIT = WALLE_SAYS
			+ "I have communication problems. Bye bye";
	
	public static final String CONTAINER = WALLE_SAYS +
	"I am carrying the following items "+ LINE_SEPARATOR;
	
	public static final String CONTAINER_EMPTY = WALLE_SAYS +
			"My inventory is empty";

	public static final String HELP = "The valid instructions for WALL·E are:"
			+ LINE_SEPARATOR
			+ "  MOVE"
			+ LINE_SEPARATOR
			+ "  TURN <LEFT | RIGHT>"
			+ LINE_SEPARATOR + "  HELP" + LINE_SEPARATOR + "  QUIT";

	public static final String END_GAME = WALLE_SAYS + "I am at my spaceship. Shutting down... Bye bye";

	public static final String PROMPT = "WALL·E > ";

	public static final String BAD_INSTRUCTION = WALLE_SAYS + "I do not understand. Please repeat";
	
	public static final String MOVE = WALLE_SAYS + "Moving in direction ";
	
	public static final String TURN = "WALL·E is looking at direction ";
}