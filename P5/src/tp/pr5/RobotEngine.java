package tp.pr5;

import java.util.Iterator;
import java.util.Scanner;

import tp.pr5.console.Console;
import tp.pr5.gui.MainWindow;
import tp.pr5.gui.NavigationPanel;
import tp.pr5.gui.RobotPanel;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.ItemContainer;
import static tp.pr5.Constants.*;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class RobotEngine extends Observable<RobotEngineObserver> {

	private int contFuel;
	private int contRecycledMaterial;
	private ItemContainer container;
	private NavigationModule navigation;
	private RobotPanel robotPanel;
	private MainWindow mainWindow;
	private boolean ship;
	private Console robotObserver;
	private boolean endGame;
	private Instruction instruction;

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
		this.navigation = new NavigationModule(city, initialPlace, this);
		this.navigation.initHeading(direction);
		this.ship = false;

	}

	/**
	 * Registers an EngineObserver to the model
	 * 
	 * @param observer
	 *            The observer that wants to be registered
	 */
	public void addEngineObserver(RobotEngineObserver observer) {

	}

	/**
	 * Adds an amount of fuel to the robot (it can be negative)
	 * 
	 * @param newFuel
	 *            is one unit of energy.
	 */

	public void addFuel(int newFuel) {
		this.contFuel += newFuel;
		for (RobotEngineObserver robotObserver : observers) {
			robotObserver.robotUpdate(contFuel, contRecycledMaterial);
		}
		if (contFuel <= 0) {
			contFuel = 0;
			robotObserver.engineOff(false);
		}
		if (robotPanel != null)
			robotPanel.setFuel(contFuel);
	}

	/**
	 * Registers an ItemContainerObserver to the model
	 * 
	 * @param ContainerObserver
	 *            The observer that wants to be registered
	 */
	public void addItemContainerObserver(InventoryObserver ContainerObserver) {

	}

	/**
	 * Register a NavigationObserver to the model
	 * 
	 * @param robotObserver
	 *            The observer that wants to be registered
	 */
	public void addNavigationObserver(NavigationObserver robotObserver) {

	}

	/**
	 * Increases the amount of recycled material of the robot
	 * 
	 * @param newMaterial
	 *            is one unit of recycled material.
	 */

	public void addRecycledMaterial(int newMaterial) {
		this.contRecycledMaterial += newMaterial;
		for (RobotEngineObserver robotObserver : observers) {
			robotObserver.robotUpdate(contFuel, contRecycledMaterial);
		}
		if (robotPanel != null)
			robotPanel.setGarbage(newMaterial);
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
			throws InstructionExecutionException {
		c.configureContext(this, navigation, container);
		try {
			c.execute();
			robotObserver.robotUpdate(fuel, recycledMaterial);
			robotObserver.communicationCompleted();
		} catch (InstructionExecutionException e) {
			throw new InstructionExecutionException(e.getMessage());
		}
	}

	/**
	 * 
	 * Show different message in console mode if robot is at space ship or
	 * hasn't got fuel
	 * 
	 * @param ship
	 */

	private void engineOff(boolean ship) {
		for (RobotEngineObserver robotObserver : this.observers)
			robotObserver.engineOff(ship);
	}

	/**
	 * 
	 * @return
	 */
	public void isOver() {
		this.engineOff(ship);
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

	}

	/**
	 * 
	 * Prints the information about all possible instructions
	 * 
	 */

	public void requestHelp() {
		System.out.println(Interpreter.interpreterHelp());

	}

	/**
	 * 
	 * Requests the end of the simulation
	 * 
	 */

	public void requestQuit() {
		System.out.println(QUIT);
		System.exit(0);
	}

	/**
	 * Requests the engine to inform the observers that the simulation starts
	 * 
	 */
	public void requestStart() {

	}

	/**
	 * Request the engine to say something
	 * 
	 * @param message
	 */
	public void saySomething(String message) {

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

	/**
	 * 
	 * Sets a panel to the navigation module in order to show its information in
	 * a GUI
	 * 
	 * @param navPanel
	 *            The panel where the navigation module will show its
	 *            information
	 */

	public void setNavigationPanel(NavigationPanel navPanel) {
		navigation.setNavigationPanel(navPanel);
	}

	/**
	 * 
	 * Sets a panel in order to show the robot information and the container in
	 * a GUI
	 * 
	 * @param robotPanel
	 *            The panel where the robot and the container will show its
	 *            information
	 */

	public void setRobotPanel(RobotPanel robotPanel) {
		this.robotPanel = robotPanel;
		this.container.setRobotPanel(robotPanel);
	}

	public void setGUi(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public void requestComunicationEnd() {
		for (RobotEngineObserver robEngineObserver : observers) {
			robEngineObserver.communicationCompleted();
		}
	}

	/**
	 * 
	 * Is the Start of game, show initial information, finish information and if
	 * player win or lost game.
	 * 
	 */

	public void startEngine() throws InstructionExecutionException {

		Scanner reader = new Scanner(System.in);
		endGame = false;

		while (!this.noFuel() && !endGame) {
			System.out.print(PROMPT);
			String input = reader.nextLine();
			try {
				instruction = Interpreter.generateInstruction(input);
				try {
					this.communicateRobot(instruction);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				if (navigation.atSpaceship()) {
					this.engineOff(true);
					endGame = true;
				}
			} catch (WrongInstructionFormatException e) {
				System.out.println(e.getMessage());
			}
		}
		reader.close();

	}

}
