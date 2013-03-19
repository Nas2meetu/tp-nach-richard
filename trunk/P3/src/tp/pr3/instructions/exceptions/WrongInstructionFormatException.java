package tp.pr3.instructions.exceptions;

public class WrongInstructionFormatException extends Exception {
	
	/**
	 * List of exception about instruction format.
	 */
	
	private static final long serialVersionUID = 1L;


	public WrongInstructionFormatException(){
		super();
	}
	
	public WrongInstructionFormatException(String arg0){
		super(arg0);
	}
	
	public WrongInstructionFormatException(Throwable arg0){
		super(arg0);
	}
	
	public WrongInstructionFormatException(String arg0, Throwable arg1){
		super(arg0, arg1);
	}

}
