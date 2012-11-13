package tp.pr1;


import java.util.Scanner;
import static tp.pr1.Constants.*;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
* 
*/

public class RobotEngine {
	

	private Place actualPlace;
	private Direction lookingDirection;
	private Street[] cityMap;
	
	/**
	 * Creates a new Robot Engine
	 * @param initialPlace is the initial place of the robot
	 * @param direction is the default direction
	 * @param cityMap is the map where the robot lives
	 */

	public RobotEngine(Place initialPlace, Direction direction, Street[] cityMap) {
		this.actualPlace = initialPlace;
		this.lookingDirection = direction;
		this.cityMap = cityMap;
	}

	/**
	 * @return lookingDirection is the direction which Robot looks
	 */
	public Direction getDirection() {
		return lookingDirection;
	}
	
	/*
	 * 
	 */

	public void setDirection(Direction direction) {
		this.lookingDirection = direction;
	}
	
	/**
	 * Is Robot begins
	 */

	public void startEngine() {
		
		Scanner read = new Scanner(System.in);
		Interpreter interpreter = new Interpreter();
		Instruction instruction = new Instruction();

		System.out.println(actualPlace.toString() + LINE_SEPARATOR +  
							MESSAGE_TURN  + lookingDirection + LINE_SEPARATOR);

		while (!isEndGame(instruction)) {
			System.out.print(PROMPT);
			instruction = interpreter.generateInstruction(read.nextLine());
			if (instruction.isValid()) {
				processInstruction(instruction);
			}else
				System.out.println(MESSAGE_BAD_INSTRUCTION+LINE_SEPARATOR);
		}read.close();
		
		System.out.println(END_GAME);

	}
	
	/**
	 * 
	 * @param instruction is a command which the Robot processes
	 * @return isEndGame is a some ways to finish the game
	 * 
	 */

	private boolean isEndGame(Instruction instruction) {
		return instruction.equals("QUIT") || actualPlace.isSpaceship();
	}
	
	/**
	 * @param instruction is a command which the Robot processes
	 */

	public void processInstruction(Instruction instruction) {
		
		Interpreter interpreter = new Interpreter();

		switch (instruction.getAction()) {

		case HELP:
			executeHelpAction(interpreter);
			break;

		case MOVE:
			executeMoveAction();
			break;

		case TURN:
			executeTurnAction(instruction);
			break;

		case QUIT:
			System.out.println(MESSAGE_QUIT);
			System.exit(0);

		case UNKNOWN:
			break;

		}
	}
	
	/**
	 * The Robot moves or not--muy basico?
	 */

	private void executeMoveAction() {
		int i = 0;	
		boolean change = false;
		while (!change && i < cityMap.length) 
			if (cityMap[i].comeOutFrom(actualPlace, lookingDirection)) {
				System.out.println(MESSAGE_MOVE + lookingDirection);
				actualPlace = cityMap[i].nextPlace(actualPlace);
				System.out.println(actualPlace.toString() + LINE_SEPARATOR + MESSAGE_TURN 
								   + lookingDirection + LINE_SEPARATOR);
				change = true;
				
			}else i++;
		if (!change){
			System.out.println(MESSAGE_NO_STREET);
		}
	}

	/**
	 * The Robot turns or not--te gusta esto asi--
	 * @param instruction is a command which the Robot processes
	 */
	private void executeTurnAction(Instruction instruction) {
		switch (instruction.getRotation()) {
		case LEFT:
			lookingDirection = lookingDirection.turnLeft();
			System.out.println(MESSAGE_TURN + lookingDirection + LINE_SEPARATOR);
			break;
		case RIGHT:
			lookingDirection = lookingDirection.turnRight();
			System.out.println(MESSAGE_TURN + lookingDirection + LINE_SEPARATOR);
			break;
		case UNKNONW:
			break;
		}
	}

	/**
	 * The Robot gives information about his instructions
	 * @param interpreter is a command which the Robot interpreters
	 */
	private void executeHelpAction(Interpreter interpreter) {
		System.out.println(interpreter.interpreterHelp());
	}

}
