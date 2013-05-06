package tp.pr5;

public interface RobotEngineObserver {
	
	/**
	 * The robot engine informs that the communication is over.
	 * 
	 */
	public void communicationCompleted();
/**
 * The robot engine informs that the help has been requested
 * 
 * @param help
 */
public void	communicationHelp(String help);

/**
 * The robot engine informs that the robot has shut down (because it has arrived at the spaceship or it has run out of fuel)
 * 
 * @param atShip
 */
public void	engineOff(boolean atShip);


/**
 * The robot engine informs that it has raised an error
 * 
 * @param msg
 */
public void	raiseError(String msg);

/**
 * The robot engine informs that the robot wants to say something
 * 
 * @param message
 */
public void	robotSays(String message);

/**
 * The robot engine informs that the fuel and/or the amount of recycled material has changed
 * 
 * @param fuel
 * @param recycledMaterial
 */
public void	robotUpdate(int fuel, int recycledMaterial);


}
