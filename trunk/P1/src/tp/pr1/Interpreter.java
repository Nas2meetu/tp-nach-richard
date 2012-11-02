package tp.pr1;


public class Interpreter {
	
	private String line;
	
		
	public Instruction generateInstruction(String line){
		Instruction ins = null;
		line = this.line.toUpperCase();
		String text = line.trim();
		String[] words = text.split(" ");
			switch (words[0]) {
			case "HELP":
				ins = new Instruction(Action.HELP);
				break;
			case "QUIT":
				ins = new Instruction(Action.QUIT);
				break;
			case "MOVE":
				ins = new Instruction(Action.MOVE);
				break;
			case "TURN":
				switch (words[1]) {
				case "LEFT":
					ins = new Instruction (Action.TURN, Rotation.LEFT);
					break;
				case "RIGHT":
					ins = new Instruction (Action.TURN, Rotation.RIGHT);
					break;
				default:
					ins = new Instruction (Action.TURN, Rotation.UNKNONW);
					break;
				}
			default:
				return ins = new Instruction (Action.UNKNOWN);	
			
			} System.out.println("says: I dont understand. Plase repeat");
			
		return ins;
	}
		
		
	
	public String interpreterHelp(){
		return Constants.MESSAGE_HELP;
	}

}
