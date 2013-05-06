package tp.pr5;

import static tp.pr5.Constants.*;
import tp.pr5.instructions.*;
import tp.pr5.instructions.exceptions.*;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class Interpreter {

	/**
	 * 
	 * Define an array of valid instructions.
	 * 
	 */


	private static Instruction[] allInstructions = new Instruction[] {

	new DropInstruction(), new HelpInstruction(), new MoveInstruction(),
			new OperateInstruction(), new PickInstruction(),
			new QuitInstruction(), new RadarInstruction(),
			new ScanInstruction(), new TurnInstruction()

	};
	
	/**
	 * 
	 * Generate parse to instruction
	 * 
	 * @param line is a line receive from robotEngine (from keyboard)
	 * @return line is instruction
	 * @throws WrongInstructionFormatException
	 */
	
	public static Instruction generateInstruction(String line)
			throws WrongInstructionFormatException {

		for (Instruction aux : allInstructions) {
			try {
				return aux.parse(line);

			} catch (WrongInstructionFormatException wife) {
			}
		}
		throw new WrongInstructionFormatException(BAD_INSTRUCTION);
	}

	/**
	 * 
	 * Show information about permitted instructions
	 * 
	 * @return String override with help message
	 * 
	 */

	public static String interpreterHelp() {

		String messageHelp = HEAD_HELP;
		for (Instruction instruction : allInstructions) {
			messageHelp = messageHelp + instruction.getHelp() + LINE_SEPARATOR;

		}
		return messageHelp;
	}

}
