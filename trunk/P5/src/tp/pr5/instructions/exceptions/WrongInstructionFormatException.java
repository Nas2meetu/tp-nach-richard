package tp.pr5.instructions.exceptions;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          Exception thrown when a string cannot be parsed to create an
 *          instruction. The exception has a user-friendly message with an
 *          explanation about the error. This class has many different
 *          constructors, one for every constructor of the base class.
 */

public class WrongInstructionFormatException extends Exception {

	/**
	 * List of exception about instruction format.
	 */

	private static final long serialVersionUID = 1L;

	public WrongInstructionFormatException() {
		super();
	}

	public WrongInstructionFormatException(String arg0) {
		super(arg0);
	}

	public WrongInstructionFormatException(Throwable arg0) {
		super(arg0);
	}

	public WrongInstructionFormatException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
