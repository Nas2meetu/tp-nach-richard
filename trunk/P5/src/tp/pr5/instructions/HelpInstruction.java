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
 *          Shows the game help with all the instructions that the robot can
 *          execute. This instruction works if the user writes HELP or AYUDA
 */

public class HelpInstruction implements Instruction {

	private RobotEngine robot;

	/**
	 * Read a string with an action, compare if this action is correct and
	 * generate DropInstruction, else throw an exception.
	 * 
	 * @param cad
	 *            Text String to parse
	 * 
	 *            return Instruction Reference to an instance of
	 *            HelpInstruction.
	 * 
	 * @throws WrongInstructionFormatException
	 *             When the String is not HELP
	 */

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("HELP")) || (words.equals("AYUDA"))) {
			if (!st.hasMoreTokens())
				return new HelpInstruction();
			else
				throw new WrongInstructionFormatException();
		} else
			throw new WrongInstructionFormatException();
	}

	/**
	 * Show information about HELP instruction syntax.
	 * 
	 * return the instruction syntax HELP
	 */

	@Override
	public String getHelp() {
		return "HELP | AYUDA";
	}

	/**
	 * Set the execution context. The method receives the entire engine (engine,
	 * navigation and the robot container) even though the actual implementation
	 * of execute() may not require it.
	 * 
	 * engine The robot engine. navigation The information about the game, i.e.,
	 * the places, current direction and current heading to navigate.
	 * robotContainer The inventory of the robot
	 * 
	 */

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.robot = engine;

	}

	/**
	 * Execute HELP instruction
	 * 
	 * @throws InstructionExecutionException
	 *             if there exist any execution error.
	 * 
	 */

	@Override
	public void execute() throws InstructionExecutionException {
		robot.requestHelp();
	}

}
