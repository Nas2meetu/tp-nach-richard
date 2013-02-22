package tp.pr3;

import java.util.Scanner;

import tp.pr3.instructions.Instruction;
import tp.pr3.intructions.exceptions.InstructionExecutionException;
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

	/**
	 * 
	 * Creates a new Robot Engine
	 * 
	 * @param initialPlace
	 *            is the initial place of the robot
	 * @param direction
	 *            is the default direction
	 * @param cityMap
	 *            is the map where the robot lives
	 * 
	 **/

	public RobotEngine(City city, Place initialPlace, Direction direction) {

		this.container = new ItemContainer();
		this.contFuel = INITIAL_POWER;
		this.contRecycledMaterial = INITIAL_GARBAGE;
		this.navigation = new NavigationModule(city, initialPlace);
	}

	/**
	 * 
	 * Is the Start game, show initial information and finish information and if
	 * Player win or lost game.
	 * 
	 */

	public ItemContainer getContainer() {
		return container;
	}

	public void communicateRobot(Instruction c) {
		c.configureContext(this, navigation, container);
		try {
			c.execute();
		} catch (InstructionExecutionException e) {
		}
	}

	/**
	 * 
	 * Execute EXIT action to finish game.
	 * 
	 * @throws
	 * 
	 */

	public void requestQuit() {

		System.out.println(QUIT);
		System.exit(0);

	}

	public void printRobotState() {

		System.out.println(navigation.getCurrentPlace().toString()
				+ LINE_SEPARATOR + POWER2 + contFuel + LINE_SEPARATOR
				+ RECICLED_MATERIAL + contRecycledMaterial + LINE_SEPARATOR
				+ LOOKING_DIRECTION + navigation.getCurrentHeading());
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

	public void startEngine() {

		/*Scanner read = new Scanner(System.in);
		
		
		while (!isEndGame(instruction) && contFuel > 0) {
			System.out.print(PROMPT);
			String word = read.nextLine().toLowerCase();
					

			if ((word == null) || word.isValid()) {
				processInstruction(instruction);
			} else
				System.out.println(BAD_INSTRUCTION + LINE_SEPARATOR);
		}
		read.close();
		if (contFuel <= 0)
			System.out.println(END_FUEL);
		else
			System.out.println(END_GAME);
	*/}

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
