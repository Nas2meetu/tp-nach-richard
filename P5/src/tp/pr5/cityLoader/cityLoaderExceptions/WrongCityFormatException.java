package tp.pr5.cityLoader.cityLoaderExceptions;

import java.io.IOException;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          Exception thrown by the map loader when the file does not adhere to
 *          the file format.
 */

public class WrongCityFormatException extends IOException {

	/**
	 * List of exception about format of city.
	 */

	private static final long serialVersionUID = 1L;

	public WrongCityFormatException() {
		super();
	}

	public WrongCityFormatException(String msg) {
		super(msg);
	}

	public WrongCityFormatException(Throwable arg) {
		super(arg);
	}

	public WrongCityFormatException(String msg, Throwable arg) {
		super(msg, arg);
	}

}
