package tp.pr5.instructions;

import java.util.StringTokenizer;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;
import static tp.pr5.Constants.*;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class QuitInstruction implements Instruction {

	private RobotEngine robot;

	/**
	 * Read a string with an action, compare if this action is correct and
	 * generate QuitInstruction, else throw an exception.
	 */

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("QUIT")) || (words.equals("SALIR"))) {
			if (!st.hasMoreTokens())
				return new QuitInstruction();
			else
				throw new WrongInstructionFormatException(BAD_INSTRUCTION);
		} else
			throw new WrongInstructionFormatException(BAD_INSTRUCTION);
	}

	/**
	 * Show information about QUIT instruction syntax.
	 */

	@Override
	public String getHelp() {
		return "QUIT | SALIR";
	}

	/**
	 * 
	 * Set the execution context. The method receives the entire engine 
	 * (engine, navigation and the robot container) even though the actual implementation
	 *  of execute() may not require it.
	 * 
	 * engine 
	 * 		The robot engine
     * navigation 
     * 		The information about the game, i.e., the places, current direction and 
     * 		current heading to navigate
     * robotContainer  
     * 		The inventory of the robot 
	 * 
	 */

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		robot = engine;

	}

	/**
	 * Execute QUIT instruction
	 */

	@Override
	public void execute() throws InstructionExecutionException {
		robot.requestQuit();
		System.exit(0);

	}

}
