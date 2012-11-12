package tp.pr1;

/**
* 
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
* @param  
* 
* @return
*/

import static tp.pr1.Constants.*;

public class Interpreter {

	/**
	 * Read, create and interpret instructions.
	 * @return instruction.
	 */
	
	public Instruction generateInstruction(String prompt) {
		
		/**
		 * Read, delete white spaces and generate simple or turn instruction
		 * @return instruction
		 */
		
	
			prompt = prompt.trim().toUpperCase();
			String[] words = prompt.split(" ");
			
			Instruction instruction = null;
			if (words.length == 1) {
				String command = words[0];
				instruction = generateSimpleInstruction(command, instruction);
			} else if (words.length == 2) {
				String turnCommand = words[0];
				String rotation = words[1];
				instruction = generateTurnInstruction(instruction, turnCommand, rotation);
			}else if (words.length>2){
				instruction = new Instruction();
			}
		
		return instruction;
	}

	private Instruction generateTurnInstruction(Instruction instruction,
			String turnCommand, String rotation) {
		
		/**
		 * Generate turn instruction
		 * @return instruction
		 */
		
		if (turnCommand.equals("TURN")) {
			if (rotation.equals("LEFT")) {
				instruction = new Instruction(Action.TURN, Rotation.LEFT);
			} else if (rotation.equals("RIGHT")) {
				instruction = new Instruction(Action.TURN, Rotation.RIGHT);
			} else {
				instruction = new Instruction(Action.TURN, Rotation.UNKNONW);
			}
		}else instruction = new Instruction(Action.UNKNOWN);
		
		return instruction;
	}

	private Instruction generateSimpleInstruction(String command,
			Instruction instruction) {
		
		/**
		 * Generate simple instruction
		 * @return instruction
		 */

		switch (command) {
		case "HELP":
			instruction = new Instruction(Action.HELP);
			break;

		case "MOVE":
			instruction = new Instruction(Action.MOVE);
			break;

		case "QUIT":
			instruction = new Instruction(Action.QUIT);
			break;
			
		case "TURN":
			instruction = new Instruction(Action.TURN);
			break;
		default:
			instruction = new Instruction();
			break;
		}
		return instruction;
	}

	public String interpreterHelp() {
		return MESSAGE_HELP + LINE_SEPARATOR;
	}

}
