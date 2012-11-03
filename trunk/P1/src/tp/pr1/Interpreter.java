package tp.pr1;

public class Interpreter {

	public Instruction generateInstruction(String prompt) {
		prompt = prompt.trim().toUpperCase();
		String[] words = prompt.split(" ");
		Instruction instruction = null;

		if (words.length == 1) {
			String command = words[0];
			instruction = generateSimpleInstruction(command, instruction);
		} else if (words.length == 2) {

			String turnCommand = words[0];
			String rotation = words[1];
			instruction = generateTurnInstruction(instruction, turnCommand,
					rotation);

		}
		return instruction;
	}

	private Instruction generateTurnInstruction(Instruction instruction,
			String turnCommand, String rotation) {
		if (turnCommand.equals("TURN")) {
			if (rotation.equals("LEFT")) {
				instruction = new Instruction(Action.TURN, Rotation.LEFT);
			} else if (rotation.equals("RIGTH")) {
				instruction = new Instruction(Action.TURN, Rotation.RIGHT);
			} else {
				instruction = new Instruction(Action.TURN, Rotation.UNKNONW);
			}
		}
		return instruction;
	}

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

	public String interpreterHelp() {
		return Constants.MESSAGE_HELP;
	}

}
