package tp.pr5.instructions;

import java.util.StringTokenizer;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 */

public class MoveInstruction implements Instruction {

	private NavigationModule navigation;
	private RobotEngine robot;

	/**
	 * Read a string with an action, compare if this action is correct and
	 * generate MoveInstruction, else throw an exception.
	 * 
	 * @param cad
	 *            text String to parse return Instruction Reference to an
	 *            instance of MoveInstruction
	 * @throws WrongInstructionFormatException
	 *             When the String is not MOVE
	 * 
	 */

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("MOVE")) || (words.equals("MOVER"))) {
			if (!st.hasMoreTokens())
				return new MoveInstruction();
			else
				throw new WrongInstructionFormatException();
		} else
			throw new WrongInstructionFormatException();
	}

	/**
	 * Show information about MOVE instruction syntax.
	 * 
	 * Returns a description of the Instruction syntax. The string does not end
	 * with the line separator. It is up to the caller adding it before
	 * printing.
	 * 
	 * return the command syntax MOVE|MOVER
	 */

	@Override
	public String getHelp() {
		return "MOVE | MOVER";
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
		this.navigation = navigation;
		this.robot = engine;
	}

	/**
	 * Execute MOVE instruction
	 * 
	 * Moves from the current place to the next place on the current Direction.
	 * An opened street must exist between both places to be moved
	 * 
	 * @throws InstructionExecutionException
	 *             When the robot cannot go to other place (there is a wall, a
	 *             closed street...)
	 */

	@Override
	public void execute() throws InstructionExecutionException {
		try {
			navigation.move();
			robot.addFuel(-5);
		} catch (InstructionExecutionException e) {
			throw new InstructionExecutionException(e.getMessage());
		}

	}

}
