package tp.pr4.instructions;

import java.util.StringTokenizer;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
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

public class QuitInstruction implements Instruction {

	private RobotEngine robot;

	/**
	 * Read a string with an action, compare if this action is correct 
	 * and generate QuitInstruction, else throw an exception.
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
	 * Method receives complete engine and use part of configureContext 
	 * depends of the instruction needs.
	 * 
	 * engine robot engine
     * navigation information about map (actualPlace, currentHeading, rotation...)
     * robotContainer inventory of robot 
	 * 
	 */
	
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		robot = engine;

	}

	/**
	 * Execute QUIT intruction
	 */
	
	@Override
	public void execute() throws InstructionExecutionException {

		robot.requestQuit();

	}

}
