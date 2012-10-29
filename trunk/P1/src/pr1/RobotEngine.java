package pr1;

import java.util.Scanner;

public class RobotEngine {
	
	private Place initialPlace;
	private Direction direction;
	private Street[] cityMap;
	//private Interpreter interpreter;


	public RobotEngine(Place initialPlace, Direction direction, Street cityMap){
		this.initialPlace = initialPlace;
		this.direction = direction;
		this.cityMap[0] = cityMap;//ver esto
	}
	public void startEngine(){
		String LINE_SEPARATOR = System.getProperty("line.separator");//donde poner el line_separator
		Scanner read = new Scanner(System.in);
		Interpreter inter= new Interpreter();
		Instruction ins = new Instruction();
		boolean end = false;
		System.out.println(initialPlace.getDescription()+ LINE_SEPARATOR + direction);
		while (!end) {
			System.out.print("WALL-E> ");
			ins = inter.generateInstruction(read.nextLine());
			if (ins.isValid()){
				if (ins.equals("QUIT")){
					end = true;
				} 
				if (ins.equals("HELP")){
					inter.interpreterHelp();
				} 
				if (ins.equals("MOVE")){
					int i=0;
					boolean change=false;
					while (!change){
						i++;
						if (cityMap[i].comeOutFrom(initialPlace, direction)){
							Place possiblePlace = cityMap[i].nextPlace(initialPlace);
							initialPlace = possiblePlace;
							change=true;
						}
					}
					//for (int i = 0; i < cityMap.length; i++) {
						//if (cityMap[i].comeOutFrom(initialPlace, direction)){
						//	Place possiblePlace = cityMap[i].nextPlace(initialPlace);
							//initialPlace = possiblePlace;
						//	change=true;
						//}
					//}
				}
			}else
				end= true;
		}
		System.out.println("GAME OVER" + LINE_SEPARATOR + "Thank you for playing, goodbye.");
		}
	}	
	

