package tp.pr1;

import java.util.Scanner;
import static tp.pr1.Constants.*;

public class RobotEngine {

	private Place actualPlace;
	private Direction lookingDirection;
	private Street[] cityMap;

	public RobotEngine(Place initialPlace, Direction direction, Street[] cityMap) {
		this.actualPlace = initialPlace;
		this.lookingDirection = direction;
		this.cityMap = cityMap;
	}

	public Direction getDirection() {
		return lookingDirection;
	}

	public void setDirection(Direction direction) {
		this.lookingDirection = direction;
	}

	public void startEngine() {
		
		Scanner read = new Scanner(System.in);
		Interpreter interpreter = new Interpreter();
		Instruction instruction = new Instruction();

		System.out.println(actualPlace.toString() + LINE_SEPARATOR +  MESSAGE_TURN  + lookingDirection);

		while (!isEndGame(instruction)) {
			System.out.print(PROMPT);
			instruction = interpreter.generateInstruction(read.nextLine());
			if (instruction.isValid()) {
				processInstruction(instruction);
			}else
				System.out.println(MESSAGE_BAD_INSTRUCTION);
		}read.close();

		System.out.println(END_GAME);

	}

	private boolean isEndGame(Instruction instruction) {
		return instruction.equals("QUIT") || actualPlace.isSpaceship();
	}

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

	private void executeMoveAction() {
		int i = 0;	
		boolean change = false;
		while (!change && i < cityMap.length) {
			if (cityMap[i].comeOutFrom(actualPlace, lookingDirection)) {
				System.out.println(LINE_SEPARATOR + MESSAGE_MOVE + lookingDirection);
				actualPlace = cityMap[i].nextPlace(actualPlace);
				System.out.println(actualPlace.toString() + LINE_SEPARATOR + MESSAGE_TURN + lookingDirection);
				change = true;
			} else {
				i++;
			}
		}
			System.out.println(MESSAGE_NO_STREET);
		
	}

	private void executeTurnAction(Instruction instruction) {
		switch (instruction.getRotation()) {
		case LEFT:
			lookingDirection = lookingDirection.turnLeft();
			System.out.println(MESSAGE_TURN + lookingDirection);
			break;
		case RIGHT:
			lookingDirection = lookingDirection.turnRight();
			System.out.println(MESSAGE_TURN + lookingDirection);
			break;
		case UNKNONW:
			break;
		}
	}

	private void executeHelpAction(Interpreter interpreter) {
		System.out.println(interpreter.interpreterHelp());
	}

}
