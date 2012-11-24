package tp.pr2;
import static tp.pr2.Constants.*;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
*/

public class Interpreter {
	
	/**
	 * 
	 * @param prompt is introduced by player 
	 * @return Instruction to interpreter by Robot
	 * 
	 */

	public Instruction generateInstruction(String prompt) {
	
			prompt = prompt.trim().toUpperCase();
			String[] words = prompt.split(" ");
			
			Instruction instruction = null;
			if (words.length == 1) {
				String command = words[0];
				instruction = generateSimpleInstruction(command, instruction);
			} else if (words.length == 2) {
				if (words[0].equals("TURN")){
					String turnCommand = words[0];
					String rotation = words[1];
					instruction = generateTurnInstruction(instruction, turnCommand, rotation);
				}else if (words[0].equals("SCAN")){
					String scanCommand = words[0];
					String id = words[1];
					instruction = generateScanInstruction(instruction, scanCommand, id);
				}else if (words[0].equals("OPERATE")){
					String operateCommand = words[0];
					String id = words[1];
					instruction = generateOperateInstruction(instruction, operateCommand, id);
				}else if (words[0].equals("PICK")){
					String pickCommand = words[0];
					String id = words[1];
					instruction = generatePickInstruction(instruction, pickCommand, id);
				}else
					instruction = new Instruction();
				
			}else if (words.length>2){
				instruction = new Instruction();
			}
		
		return instruction;
	}
	
	private Instruction generatePickInstruction(Instruction instruction,
			String pickCommand, String id) {
		if (pickCommand.equals("PICK")) {
			if (!id.isEmpty()){//como comparar si el id es correcto??
				instruction = new Instruction(Action.PICK, id); 
			} 
			else {
				instruction = new Instruction(Action.PICK, "");
			}			
		}else instruction = new Instruction(Action.UNKNOWN);
		
		return instruction;
	
	}

	private Instruction generateScanInstruction(Instruction instruction,
			String scanCommand, String id) {
		
		if (scanCommand.equals("SCAN")) {
			if (!id.isEmpty()){//como comparar si el id es correcto??
				instruction = new Instruction(Action.SCAN, id); 
			} 
			else {
				instruction = new Instruction(Action.SCAN, "");
			}			
		}else instruction = new Instruction(Action.UNKNOWN);
		
		return instruction;
	}
	
	private Instruction generateOperateInstruction(Instruction instruction,
			String operateCommand, String id) {
		
		if (operateCommand.equals("SCAN")) {
			if (!id.isEmpty()){//como comparar si el id es correcto??
				instruction = new Instruction(Action.OPERATE, id); 
			} 
			else {
				instruction = new Instruction(Action.OPERATE, "");
			}			
		}else instruction = new Instruction(Action.UNKNOWN);
		
		return instruction;
	}

	/**
	 * 
	 * @param instruction is command to Robot generates
	 * @param turnCommand is command to Robot turns
	 * @param rotation is direction to Robots turns
	 * @return generateTurnInstruction is two 
	 * 		   instructions (turn and where rotation) to interpreter by Robot
	 * 
	 */

	private Instruction generateTurnInstruction(Instruction instruction,
			String turnCommand, String rotation) {
		if (turnCommand.equals("TURN")) {
			if (rotation.equals("LEFT")) {
				instruction = new Instruction(Action.TURN, Rotation.LEFT);
			} else if (rotation.equals("RIGHT")) {
				instruction = new Instruction(Action.TURN, Rotation.RIGHT);
			} else {
				instruction = new Instruction(Action.TURN, Rotation.UNKNONW);
			}			
		}
		else instruction = new Instruction(Action.UNKNOWN);
		
		return instruction;
	}
	
	/**
	 * 
	 * @param command is command to Robot receives
	 * @param instruction is simple (one word) command to Robot generates
	 * @return generateSimpleInstruction is one instruction to interpreter by Robot
	 * 
	 */

	private Instruction generateSimpleInstruction(String command,
			Instruction instruction) {

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
	
	/**
	 *
	 * @return String override with help message
	 */

	public String interpreterHelp() {
		return HELP + LINE_SEPARATOR;
	}

}
