package tp.pr3.intructions.exceptions;

public class InstructionExecutionException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public InstructionExecutionException(){
		super();
	}
	
	public InstructionExecutionException(String arg0){
		super(arg0);
	}
	
	
	public InstructionExecutionException(Throwable arg0){
		super(arg0);
	}
	
	
	public InstructionExecutionException(String arg0, Throwable arg1){
		super(arg0, arg1);
	}
	
	
}
