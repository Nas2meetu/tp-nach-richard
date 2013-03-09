package tp.pr3;

import static tp.pr3.Constants.*;
import tp.pr3.instructions.*;
import tp.pr3.intructions.exceptions.*;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 3
 * 
 */

public class Interpreter {

	/*
	 * Define an array of valid instructions.
	 */

	private static Instruction[] allInstructions = new Instruction[] {

	new DropInstruction(), new HelpInstruction(), new MoveInstruction(),
			new OperateInstruction(), new PickInstruction(),
			new QuitInstruction(), new RadarInstruction(),
			new ScanInstruction(), new TurnInstruction()

	};

	public static Instruction generateInstruction(String line)
			throws WrongInstructionFormatException {

		for (Instruction aux : allInstructions) {
			try {
				return aux.parse(line);

			} catch (WrongInstructionFormatException wife) {

			}
		}
		throw new WrongInstructionFormatException();
	}

	/**
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
