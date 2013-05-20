package tp.pr5;

import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.ItemContainer;
import static tp.pr5.Constants.*;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 */

public class RobotEngine extends Observable<RobotEngineObserver> {

	private int contFuel;
	private int contRecycledMaterial;
	private ItemContainer container;
	private NavigationModule navigation;
	

	/**
	 * 
	 * Constructor of three parameters to create a new Robot Engine
	 * 
	 * @param initialPlace
	 *            is the initial place of the robot
	 * @param direction
	 *            is the default direction
	 * @param city
	 *            is the map where the robot lives
	 * 
	 **/

	public RobotEngine(City city, Place initialPlace, Direction direction) {
		super();
		this.container = new ItemContainer();
		this.contFuel = INITIAL_POWER;
		this.contRecycledMaterial = INITIAL_GARBAGE;
		this.navigation = new NavigationModule(city, initialPlace);
		this.navigation.initHeading(direction);
	}

	/**
	 * Registers an EngineObserver to the model
	 * 
	 * @param observer
	 *            The observer that wants to be registered
	 */
	public void addEngineObserver(RobotEngineObserver observer) {
		this.addObserver(observer);

	}

	/**
	 * Adds an amount of fuel to the robot (it can be negative)
	 * 
	 * @param newFuel
	 *            is one unit of energy.
	 */

	public void addFuel(int newFuel) {
		this.contFuel += newFuel;
		if (contFuel <= 0) {
			contFuel = 0;
			requestEnd();
		}
		notifyRobotUpdate();
	}

	/**
	 * Registers an ItemContainerObserver to the model
	 * 
	 * @param ContainerObserver
	 *            The observer that wants to be registered
	 */
	public void addItemContainerObserver(InventoryObserver observer) {
		container.addObserver(observer);
	}

	/**
	 * Register a NavigationObserver to the model
	 * 
	 * @param robotObserver
	 *            The observer that wants to be registered
	 */
	public void addNavigationObserver(NavigationObserver observer) {
		navigation.addObserver(observer);
	}

	/**
	 * Increases the amount of recycled material of the robot
	 * 
	 * @param newMaterial
	 *            is one unit of recycled material.
	 */

	public void addRecycledMaterial(int newMaterial) {
		this.contRecycledMaterial += newMaterial;
		notifyRobotUpdate();
	}

	/**
	 * It executes an instruction. The instruction must be configured with the
	 * context before executing it. It controls the end of the simulation. If
	 * the execution of the instruction throws an exception, then the
	 * corresponding message is printed
	 * 
	 * @param c
	 *            The instruction to be executed
	 * @throws InstructionExecutionException
	 */

	public void communicateRobot(Instruction c)
	 {
		c.configureContext(this, navigation, container);
		try {
			c.execute();
			if (navigation.atSpaceship())
				this.requestEnd();
		} catch (InstructionExecutionException e) {
			this.requestError(e.getMessage());
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isOver() {
		return (noFuel() || navigation.atSpaceship());
	}

	/**
	 * 
	 * If robot has not got fuel puts contFuel count to zero
	 * 
	 * @return contFuel robot's count fuel
	 */

	public boolean noFuel() {
		return this.contFuel <= 0;
	}

	/**
	 * Requests the engine to inform that an error has been raisedRequests the
	 * engine to inform that an error has been raised
	 * 
	 * @param msg
	 */
	public void requestError(String msg) {
		for (RobotEngineObserver robotObserver : observers) {
			robotObserver.raiseError(msg);
		}
	}

	/**
	 * 
	 * Prints the information about all possible instructions
	 * 
	 */

	public void requestHelp() {
		for (RobotEngineObserver robotObserver : observers) {
			robotObserver.communicationHelp(Interpreter.interpreterHelp());
		}

	}

	public void requestQuit() {
		notifyCommunicationCompleted();
	}

	private void notifyCommunicationCompleted() {
		for (RobotEngineObserver robotObserver : observers) {
			robotObserver.communicationCompleted();
		}
	}

	/**
	 * 
	 * Requests the end of the simulation
	 * 
	 */

	public void requestEnd() {
		notifyEngineOff();
	}

	private void notifyEngineOff() {
		for (RobotEngineObserver robotObserver : observers)
			robotObserver.engineOff(navigation.atSpaceship());
	}

	/**
	 * Requests the engine to inform the observers that the simulation starts
	 * 
	 */
	public void requestStart() {
		navigation.requestInitNavigationModule();
		notifyRobotUpdate();
	}

	public void notifyRobotUpdate() {
		for (RobotEngineObserver robotObserver : observers) {
			robotObserver.robotUpdate(contFuel, contRecycledMaterial);
		}
	}

	/**
	 * Request the engine to say something
	 * 
	 * @param message
	 */
	public void saySomething(String message) {
		for (RobotEngineObserver robotObserver : observers) {
			robotObserver.robotSays(message);
		}

	}

	/**
	 * 
	 * Returns the current fuel level of the robot.
	 * 
	 * @return contFuel The current fuel level of the robot
	 * 
	 */

	public int getFuel() {
		return contFuel;
	}

	/**
	 * 
	 * Returns the current weight of recycled material that the robot carries
	 * 
	 * @return contRecycledMaterial The current recycled material of the robot
	 * 
	 */

	public int getRecycledMaterial() {
		return contRecycledMaterial;
	}

	/**
	 * 
	 * Return a public method (actualPlace and lookingDirection) of a private
	 * attribute (HeadingStreet).
	 * 
	 * @return lookForStreet is the actual place and direction where Robot is
	 *         looking at.
	 * 
	 */

	public Street getHeadingStreet() {
		return navigation.getCityMap().lookForStreet(
				navigation.getCurrentPlace(), navigation.getCurrentHeading());
	}

	/**
	 * 
	 * Return a public method (container) of a private attribute (getContaine).
	 * 
	 * @return container is a container of items
	 */

	public ItemContainer getContainer() {
		return container;
	}

}
