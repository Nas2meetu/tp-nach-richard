package tp.pr1;

import java.util.Scanner;
import static tp.pr1.Constants.*;

public class RobotEngine {
	
	private Place initialPlace;
	private Direction direction;
	private Street[] cityMap;
	


	public RobotEngine(Place initialPlace, Direction direction, Street[] cityMap){
		this.initialPlace = initialPlace;
		this.direction = direction;
		this.cityMap = cityMap;//ver esto
	}
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public void startEngine(){
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
				}else 
					processInstruction(ins);
					
			}
		}System.out.println("GAME OVER" + LINE_SEPARATOR + "Thank you for playing, goodbye.");
		
	}
	
	public void processInstruction (Instruction ins) {
		Interpreter inter= new Interpreter();
		
			
		switch (ins.getAction()){
		
			case HELP:
					inter.interpreterHelp();
			case MOVE:
				int i=0;
				boolean change=false;
				while (!change){
					if (cityMap[i].comeOutFrom(initialPlace, direction)){
						Place possiblePlace = cityMap[i].nextPlace(initialPlace);
						initialPlace = possiblePlace;
						change=true;
					}
					i++;
				}
			case TURN:
				switch (ins.getRotation()){
				case LEFT:
					direction.turnLeft();				
				case RIGHT:
					direction.turnRight();
				case UNKNONW:
					break;
				
				}
			case QUIT:
				System.exit(-1);
			case UNKNOWN:
				break;
		
		}
	}	
	
}	
	

