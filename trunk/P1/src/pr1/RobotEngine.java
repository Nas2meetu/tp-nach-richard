package pr1;

import java.util.Scanner;

public class RobotEngine {
	
	private Place initialPlace;
	private Direction direction;
	private Street[] cityMap;
	private Interpreter interpreter;


	public RobotEngine(Place initialPlace, Direction direction, Street cityMap){
		this.initialPlace = initialPlace;
		this.direction = direction;
		this.cityMap[0] = cityMap;//ver esto
	}
	public void startEngine(){
		String LINE_SEPARATOR = System.getProperty("line.separator");
		Scanner read = new Scanner(System.in);
		Instruction ins;
		boolean end = false;
		System.out.println(initialPlace.getDescription()+ LINE_SEPARATOR + direction);

		while (!end) {
			System.out.print("WALL-E> ");
			ins = interpreter.generateInstruction(read.nextLine());
			if (read.equals("QUIT")){
				end = true;
			}
		
		}
		System.out.println("GAME OVER\nThank you for playing, goodbye.");
	}
		
	
}
