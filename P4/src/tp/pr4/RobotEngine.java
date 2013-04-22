package tp.pr4;

import java.util.Scanner;

import javax.swing.JOptionPane;

import tp.pr4.gui.MainWindow;
import tp.pr4.gui.NavigationPanel;
import tp.pr4.gui.RobotPanel;
import tp.pr4.instructions.Instruction;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;
import static tp.pr4.Constants.*;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class RobotEngine {

	private int contFuel;
	private int contRecycledMaterial;
	private boolean endGame;
	private ItemContainer container;
	private NavigationModule navigation;
	private Instruction instruction;
	private MainWindow mainWindow;
	private RobotPanel robotPanel;
	private NavigationPanel navPanel;

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

		this.container = new ItemContainer();
		this.contFuel = INITIAL_POWER;
		this.contRecycledMaterial = INITIAL_GARBAGE;
		this.navigation = new NavigationModule(city, initialPlace);
		this.navigation.initHeading(direction);
		this.robotPanel = new RobotPanel();

	}


	/**
	 * 
	 * Is the Start of game, show initial information, finish information and if
	 * player win or lost game.
	 * 
	 */
	
	public void startEngine() throws InstructionExecutionException {

		Scanner reader = new Scanner(System.in);
		printRobotState();
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
					System.out.print(END_GAME + LINE_SEPARATOR);
					endGame = true;
				}
			} catch (WrongInstructionFormatException e) {
				System.out.println(e.getMessage());
			}
		}
		reader.close();

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
	 * It executes an instruction. The instruction must be configured with the context before executing it.
	 * It controls the end of the simulation. If the execution of the instruction throws an exception,
	 *  then the corresponding message is printed
	 * 
	 * @param c The instruction to be executed
	 * @throws InstructionExecutionException
	 */

	public void communicateRobot(Instruction c)
			throws InstructionExecutionException {
		c.configureContext(this, navigation, container);
		try {
			c.execute();
			if (navigation.atSpaceship()) {
				JOptionPane.showMessageDialog(navPanel, END_GAME);
				System.exit(0);
			}
		} catch (InstructionExecutionException e) {
			throw new InstructionExecutionException(e.getMessage());
		}
	}
	
	/**
	 * Prints the state of the robot
	 */

	public void printRobotState() {

		System.out.println(navigation.getCurrentPlace().toString()
				+ LOOKING_DIRECTION + navigation.getCurrentHeading()
				+ LINE_SEPARATOR + POWER2 + this.contFuel + LINE_SEPARATOR
				+ RECYCLED_MATERIAL + contRecycledMaterial + LINE_SEPARATOR);
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
	 * 
	 * Adds an amount of fuel to the robot (it can be negative)
	 * 
	 * @param newFuel is one unit of energy.
	 *            
	 */

	public void addFuel(int newFuel) {
		this.contFuel += newFuel;
		if (contFuel < 0) {
			contFuel = 0;
			System.out.println(LOOKING_DIRECTION
					+ navigation.getCurrentHeading() + LINE_SEPARATOR + POWER2
					+ this.contFuel + LINE_SEPARATOR + RECYCLED_MATERIAL
					+ contRecycledMaterial + LINE_SEPARATOR);
		}
		if (robotPanel != null)
			robotPanel.setFuel(contFuel);
	}

	/**
	 * 
	 * Increases the amount of recycled material of the robot
	 * 
	 * @param newMaterial is one unit of recycled material.
	 *            
	 */

	public void addRecycledMaterial(int newMaterial) {
		this.contRecycledMaterial += newMaterial;
		if (robotPanel != null)
			robotPanel.setGarbage(newMaterial);
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
	 * Sets a panel to the navigation module in order to show its information in a GUI
	 * 
	 * @param navPanel The panel where the navigation module will show its information
	 */
	
	public void setNavigationPanel(NavigationPanel navPanel) {
		navigation.setNavigationPanel(navPanel);
	}

	/**
	 * 
	 * Sets the main window of the GUI in order to inform about some robot events
	 * 
	 * @param mainWindow a GUI Window
	 */
	
	public void setGUIWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	/**
	 * 
	 * Sets a panel in order to show the robot information and the container in a GUI
	 * 
	 * @param robotPanel The panel where the robot and the container will show its information
	 */
	
	public void setRobotPanel(RobotPanel robotPanel) {
		this.robotPanel = robotPanel;
		this.container.setRobotPanel(robotPanel);
	}
}
