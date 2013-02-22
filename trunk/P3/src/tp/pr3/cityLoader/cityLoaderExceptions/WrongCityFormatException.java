package tp.pr3.cityLoader.cityLoaderExceptions;

public class WrongCityFormatException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongCityFormatException(){
		super();
	}
	
	public WrongCityFormatException(String msg){
		super(msg);
	}
	
	public WrongCityFormatException(Throwable arg){
		super(arg);		
	}
	
	public WrongCityFormatException(String msg, Throwable arg){
		super(msg, arg);
	}
	
	
	
	
}
