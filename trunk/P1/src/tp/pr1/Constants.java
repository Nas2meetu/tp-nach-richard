package tp.pr1;

public class Constants {

	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	public static final String WALLE_SAYS = "WALL·E says: ";

	public static final String MESSAGE_NO_STREET = WALLE_SAYS
			+ "There is no street in direction ";

	public static final String MESSAGE_QUIT = WALLE_SAYS
			+ "I have communications problems. Bye bye";

	public static final String MESSAGE_HELP = "The valid instructions for WALL·E are:"
			+ LINE_SEPARATOR
			+ "MOVE"
			+ LINE_SEPARATOR
			+ "TURN <LEFT | RIGHT>"
			+ LINE_SEPARATOR + "HELP" + LINE_SEPARATOR + "QUIT";

	public static final String END_GAME = "GAME OVER" + LINE_SEPARATOR
			+ "Thank you for playing, goodbye.";

	public static final String PROMPT = "WALL·E> ";

	public static final String MESSAGE_BAD_INSTRUCTION = WALLE_SAYS + "I do not understand. Please repeat";
	
	public static final String MESSAGE_MOVE = WALLE_SAYS + "Moving in direction ";
	
	public static final String MESSAGE_TURN = "WALL·E is looking at direction ";
}