package tp.pr3.cityLoader.cityLoaderExceptions;

import java.io.IOException;

public class WrongCityFormatException extends IOException {

	/**
	 * 
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
