package pr1;

public class Interpreter {
	
	private String line;
		
	
	public Instruction generateInstruction(String line){
		Instruction ins = null;
		//int i = 0;
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
				Rotation rotation = null;
				switch (words[1]) {
				case "LEFT":
					ins = new Instruction (Action.TURN, rotation.LEFT);
					break;
				case "RIGHT":
					ins = new Instruction (Action.TURN, rotation.RIGHT);
					break;
				default:
					ins = new Instruction (Action.TURN, rotation.UNKNONW);
					break;
				}
			default:
				return ins = new Instruction (Action.UNKNOWN);	
			
			} System.out.println("says: I dont understand. Plase repeat");
			
		return ins;
	}
		
		/*while (i < words.length) {
			if (words.length > 2)
				return ins = new Instruction();
			
			if (words[0].contains("TURN")){
				if (words.length == 1){
					return ins = new Instruction();
				}else {
					Rotation rotation = null;
					//ins.setAction(Action.TURN);
					if (words[1].contains("LEFT")){
						return ins = new Instruction (Action.TURN, rotation.LEFT);
					}else {
						if (words[1].contains("RIGHT")){
							return ins = new Instruction (Action.TURN, rotation.RIGHT);
						}	
					} 
					return ins= new Instruction(Action.TURN, Rotation.UNKNONW);
				}		
			}
			
			if (words[0].contains("MOVE")) 
				ins = new Instruction(Action.MOVE);
				//ins.setAction(Action.MOVE);
				//System.out.printf("Moving in direction",direction);preguntar si poner aqui esto
			if (words[0].contains("HELP")) 
				ins = new Instruction(Action.HELP);
			if (words[0].contains("QUIT")) 
				ins = new Instruction(Action.QUIT);
			i++;
		}
		return ins = new Instruction();
	}*/
	
	public String interpreterHelp(){
		String LINE_SEPARATOR = System.getProperty("line.separator");
		
		return "The valid instructions for WALL·E are:" + LINE_SEPARATOR +"MOVE" + LINE_SEPARATOR +
				"TURN <LEFT | RIGHT>" + LINE_SEPARATOR + "HELP" + LINE_SEPARATOR + "QUIT";
	}

}
