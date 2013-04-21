package tp.pr4;

import java.util.Scanner;
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
	private ItemContainer container;
	private NavigationModule navigation;
	private boolean endGame;
	private Instruction instruction;
	private MainWindow mainWindow;
	private RobotPanel robotPanel;
	private NavigationPanel navPanel;


	/**
	 * 
	 * Constructor of three parameters to create a new Robot Engine
	 * 
	 * @param initialPlace is the initial place of the robot
	 * @param direction is the default direction
	 * @param city is the map where the robot lives
	 * 
	 **/

	public RobotEngine(City city, Place initialPlace, Direction direction) {

		this.container = new ItemContainer();
		this.contFuel = INITIAL_POWER;
		this.contRecycledMaterial = INITIAL_GARBAGE;
		this.navigation = new NavigationModule(city, initialPlace);
		this.navigation.initHeading(direction);
		this.robotPanel= new RobotPanel();

	}

	/**
	 * 
	 * Is the Start of game, show initial information, finish information and if
	 * player win or lost game.
	 * 
	 */

	public ItemContainer getContainer() {
		return container;
	}

	public void startEngine() throws InstructionExecutionException {

		Scanner reader = new Scanner(System.in);
		printRobotState();
		endGame = false;

		while (!this.noFuel() && !endGame) {
			System.out.print(PROMPT);
			String input = reader.nextLine();

			try {
				instruction = Interpreter.generateInstruction(input);
				this.communicateRobot(instruction);
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
	 * Requests the game to quit
	 *
	 */
	
	public void requestQuit() {
		System.out.println(QUIT);
		System.exit(0);
	}
	
	/**
	 * 
	 * @param c
	 * @throws InstructionExecutionException 
	 */
	
	public void communicateRobot(Instruction c) throws InstructionExecutionException {
		c.configureContext(this, navigation, container);
		try {
			c.execute();
		} catch (InstructionExecutionException e) {
			throw new InstructionExecutionException(e.getMessage());
		}
	}

	/**
	 * 
	 * Execute EXIT action to finish game.
	 * 
	 * @throws
	 * 
	 */

	/*
	 * public void requestQuit() { quit = true; System.out.println(QUIT);
	 * 
	 * }
	 */

	public void printRobotState() {

		System.out.println(navigation.getCurrentPlace().toString()
				+ LOOKING_DIRECTION + navigation.getCurrentHeading()
				+ LINE_SEPARATOR + POWER2 + this.contFuel + LINE_SEPARATOR
				+ RECYCLED_MATERIAL + contRecycledMaterial + LINE_SEPARATOR);
	}

	public boolean noFuel() {
		return this.contFuel <= 0;
	}

	/**
	 * 
	 * Robot increase his fuel counter.
	 * 
	 * @param newFuel
	 *            is one unit of energy.
	 */

	public void addFuel(int newFuel) {
		this.contFuel += newFuel;
		if (contFuel < 0) {
			contFuel = 0;
			System.out.println(LOOKING_DIRECTION + navigation.getCurrentHeading()
				+ LINE_SEPARATOR + POWER2 + this.contFuel + LINE_SEPARATOR
				+ RECYCLED_MATERIAL + contRecycledMaterial + LINE_SEPARATOR);
		}
		robotPanel.setFuel(contFuel);
	}

	/**
	 * 
	 * Robot increase his recycled material counter.
	 * 
	 * @param newMaterial
	 *            is one unit of recycled material.
	 */

	public void addRecycledMaterial(int newMaterial) {
		this.contRecycledMaterial += newMaterial;

	}

	/**
	 * 
	 * Return a public method (contFuel) of a private attribute (Fuel).
	 * 
	 * @return contFuel is a counter of energy.
	 * 
	 */

	public int getFuel() {
		return contFuel;
	}

	/**
	 * 
	 * Return a public method (contRecyledMaterial) of a private attribute
	 * (RecycledMaterial).
	 * 
	 * @return ContRecycledMaterial is counter of recycled material.
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

	public void requestHelp() {
		System.out.println(Interpreter.interpreterHelp());

	}
	
	
	
	public void setNavigationPanel(NavigationPanel navPanel){
		navigation.setNavigationPanel(navPanel);
	}

	public void setGUIWindow(MainWindow mainWindow){ 
		this.mainWindow= mainWindow;
	}

	public void setRobotPanel(RobotPanel robotPanel){ 
		this.robotPanel = robotPanel;
		this.container.setRobotPanel(robotPanel);
	}
}
