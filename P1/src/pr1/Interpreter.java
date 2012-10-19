package pr1;

public class Interpreter {
	private String line;
		
	
	public Instruction generateInstruction(String line){
		Instruction ins = null;//preguntar si esta bien inicializar a null
		int i = 0;
		line = this.line.toUpperCase();
		String text = line.trim();
		String[] words = text.split(" ");
		while (i < words.length) {
			if (words.length > 2)
				return ins = new Instruction();
			
			if (words[0].contains("TURN")){
				if (words.length == 1){
					return ins = new Instruction();
				}else {
					ins = new Instruction(Action.TURN);
					//ins.setAction(Action.TURN);
					if (words[1].contains("LEFT")){
						ins.setRotation(Rotation.LEFT);
					}else {
						if (words[1].contains("RIGHT")){
							ins.setRotation(Rotation.RIGHT);
						}	
					} 
					ins.setRotation(Rotation.UNKNONW);
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
	}

}
