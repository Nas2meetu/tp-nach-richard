package tp.pr3.instructions;

import java.util.StringTokenizer;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
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

public class HelpInstruction implements Instruction {

	private RobotEngine robot;
	
	/**
	 * Read a string with an action, compare if this action is correct 
	 * and generate DropInstruction, else throw an exception.
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
	 */
	
	@Override
	public String getHelp() {
		return "HELP | AYUDA";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.robot = engine;

	}
	
	/**
	 * Execute HELP instruction
	 */
	
	@Override
	public void execute() throws InstructionExecutionException {
		robot.requestHelp();
	}

}
