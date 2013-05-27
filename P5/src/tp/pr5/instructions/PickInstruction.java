package tp.pr5.instructions;

import static tp.pr5.Constants.*;

import java.util.StringTokenizer;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 */

public class PickInstruction implements Instruction {

	private NavigationModule navigation;
	private String id;
	private ItemContainer robotContainer;
	private RobotEngine robot;

	public PickInstruction(String token2) {
		this.id = token2;

	}

	public PickInstruction() {

	}

	/**
	 * Read a string with an action, compare if this action is correct and
	 * generate PickInstruction, else throw an exception.
	 * 
	 * @param cad
	 *            text String to parse
	 * 
	 *            return Instruction Reference to an instance of PickInstruction
	 * 
	 * @throws WrongInstructionFormatException
	 *             then the String is not PICK|COGER <id>
	 */

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("PICK")) || (words.equals("COGER"))) {
			if (st.hasMoreTokens()) {
				String token2 = st.nextToken();
				if (!st.hasMoreTokens())
					return new PickInstruction(token2);
				else
					throw new WrongInstructionFormatException();
			} else
				throw new WrongInstructionFormatException();
		} else
			throw new WrongInstructionFormatException();
	}

	/**
	 * Show information about PICK instruction syntax.
	 * 
	 * Returns a description of the Instruction syntax. The string does not end
	 * with the line separator. It is up to the caller adding it before
	 * printing.
	 * 
	 * return the command syntax PICK|COGER <id>
	 */

	@Override
	public String getHelp() {
		return "PICK | COGER <id>";
	}

	/**
	 * Set the execution context. The method receives the entire engine (engine,
	 * navigation and the robot container) even though the actual implementation
	 * of execute() may not require it.
	 * 
	 * engine The robot engine navigation The information about the game, i.e.,
	 * the places, current direction and current heading to navigate
	 * robotContainer The inventory of the robot
	 * 
	 * @param engine
	 *            The robot engine
	 * @param navigation
	 *            The information about the game, i.e., the places, current
	 *            direction and current heading to navigate
	 * @param robotContainer
	 *            The inventory of the robot
	 */

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.robot = engine;
		this.navigation = navigation;
		this.robotContainer = robotContainer;

	}

	/**
	 * Execute PICK instruction Verify if item isn't null and if place has got
	 * this item.
	 * 
	 * The robot adds an item to its inventory from the current place, if it
	 * exists
	 * 
	 * @throws instructionExecutionException
	 *             When the place does not contain an item with this name or
	 *             when there is another item with the same id in the robot
	 *             inventory.
	 * @throws InstructionExecutionException
	 *             if there exist any execution error.
	 */

	@Override
	public void execute() throws InstructionExecutionException {
		Item item = navigation.getCurrentPlace().getItem(id);
		if (item == null)
			throw new InstructionExecutionException(PLACE_NO_ITEM + id);
		else if (robotContainer.addItem(item)) {
			navigation.pickItemFromCurrentPlace(item);
			robot.saySomething(CONTAINER_ITEM + id + LINE_SEPARATOR);
		} else
			throw new InstructionExecutionException(CONTAINER_REPEAT_ITEM + id);

	}

}