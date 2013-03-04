package tp.pr3.instructions;

import java.util.StringTokenizer;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.intructions.exceptions.InstructionExecutionException;
import tp.pr3.intructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;
import static tp.pr3.Constants.*;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 3
 * 
 */

public class QuitInstruction implements Instruction {

	private RobotEngine robot;

	/*
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
	
	/*
	 * Show information about QUIT instruction syntax.
	 */

	@Override
	public String getHelp() {
		return "QUIT | SALIR";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		robot = engine;

	}

	@Override
	public void execute() throws InstructionExecutionException {

		robot.requestQuit();

	}

}
