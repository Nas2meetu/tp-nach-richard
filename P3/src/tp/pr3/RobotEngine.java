package tp.pr3;

import java.util.Scanner;
import tp.pr3.instructions.Instruction;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;
import static tp.pr3.Constants.*;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 3
 * 
 */

public class RobotEngine {

	private int contFuel;
	private int contRecycledMaterial;
	private ItemContainer container;
	private NavigationModule navigation;
	private boolean endGame;
	private Instruction instruction;

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

	public void startEngine() {

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
	 */
	
	public void communicateRobot(Instruction c) {
		c.configureContext(this, navigation, container);
		try {
			c.execute();
		} catch (InstructionExecutionException e) {
			System.out.println(e.getMessage());
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
		}
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
}
