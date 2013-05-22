package tp.pr5;

import static tp.pr5.Constants.*;
import tp.pr5.instructions.*;
import tp.pr5.instructions.exceptions.*;

/**
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          The interpreter is in charge of converting the user input in an
 *          instruction for the robot. Up to now, the valid instructions are:
 * 
 *          MOVE | MOVER TURN | GIRAR { LEFT | RIGHT } PICK | COGER <ITEM> DROP
 *          | SOLTAR <ITEM> SCAN | ESCANEAR [ <ITEM> ] RADAR OPERATE | OPERAR
 *          <ITEM> HELP | AYUDA QUIT | SALIR
 */

public class Interpreter {

	/**
	 * Define an array of valid instructions.
	 */

	private static Instruction[] allInstructions = new Instruction[] {

	new DropInstruction(), new HelpInstruction(), new MoveInstruction(),
			new OperateInstruction(), new PickInstruction(),
			new QuitInstruction(), new RadarInstruction(),
			new ScanInstruction(), new TurnInstruction()

	};

	/**
	 * Generates a new instruction according to the user input
	 * 
	 * @param line
	 *            is a line receive from robotEngine (from keyboard)
	 * @return line The instruction read from the given line. If the instruction
	 *         is not correct, then it throws an exception
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
	 * It returns information about all the instructions that the robot
	 * understands
	 * 
	 * @return String A string with the information about all the available
	 *         instructions
	 */

	public static String interpreterHelp() {

		String messageHelp = HEAD_HELP;
		for (Instruction instruction : allInstructions) {
			messageHelp = messageHelp + instruction.getHelp() + LINE_SEPARATOR;

		}
		return messageHelp;
	}

}
