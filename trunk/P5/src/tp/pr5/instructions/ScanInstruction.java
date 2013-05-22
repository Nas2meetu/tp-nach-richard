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
 *          The execution of this instruction shows the information of the
 *          inventory of the robot or the complete description about the item
 *          with identifier id contained in the inventory This Instruction works
 *          if the player writes SCAN or ESCANEAR (id is not mandatory)
 */

public class ScanInstruction implements Instruction {

	private String id;
	private ItemContainer robotContainer;
	private RobotEngine robot;

	public ScanInstruction(String token2) {
		this.id = token2;
	}

	public ScanInstruction() {

	}

	/**
	 * Read a string with an action, compare if this action is correct and
	 * generate ScanInstruction, else throw an exception.
	 * 
	 * @param cad
	 *            text String to parse
	 * 
	 *            return Instruction Reference to an instance of ScanInstruction
	 * 
	 * @throws WrongInstructionFormatException
	 *             When the String is not SCAN | ESCANEAR [id]
	 */

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("SCAN")) || (words.equals("ESCANEAR"))) {
			if (st.hasMoreTokens()) {
				String token2 = st.nextToken();
				if (!st.hasMoreTokens())
					return new ScanInstruction(token2);
				else
					throw new WrongInstructionFormatException();
			} else
				return new ScanInstruction();
		} else
			throw new WrongInstructionFormatException();

	}

	/**
	 * Show information about SCAN instruction syntax.
	 * 
	 * Returns a description of the Instruction syntax. The string does not end
	 * with the line separator. It is up to the caller adding it before
	 * printing.
	 * 
	 * Return The Instruction's syntax.
	 */

	@Override
	public String getHelp() {
		return "SCAN | ESCANEAR <id>";
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
		this.robotContainer = robotContainer;

	}

	/**
	 * Execute SCAN instruction.
	 * 
	 * Executes the Instruction It must be implemented in every non abstract
	 * subclass.
	 * 
	 * @throws InstructionExecutionException
	 *             if there exist any execution error.
	 */

	@Override
	public void execute() throws InstructionExecutionException {
		if (id == null) {
			if (robotContainer.numberOfItems() == 0) {
				robot.saySomething(CONTAINER_EMPTY);
			} else {
				robot.saySomething(CONTAINER);
				robot.saySomething(robotContainer.toString() + LINE_SEPARATOR);
			}
		} else {
			Item item = robotContainer.getItem(id);
			if (item != null)
				System.out.println(WALLE_SAYS + item.toString());
			else
				throw new InstructionExecutionException(SCAN_NO_ITEM + id);
		}
	}
}
