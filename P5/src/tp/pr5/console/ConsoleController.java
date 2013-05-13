package tp.pr5.console;

import static tp.pr5.Constants.PROMPT;

import java.util.Scanner;

import tp.pr5.Controller;
import tp.pr5.Interpreter;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

public class ConsoleController extends Controller {

	public ConsoleController(RobotEngine robot) {
		super(robot);
	}

	private RobotEngine robot;
	/**
	 * Constructor of the controller. It receives the model main class.
	 * 
	 * @param game
	 *            Game that is being played.
	 */
	

	public void startController() {
		super.startController();
		startEngine();
	}
	
	public void startEngine()  {

		Scanner reader = new Scanner(System.in);
		boolean ship = false;
		Instruction instruction = null;
		
		while (!robot.isOver()) {// TODO mirar esto con detenimiento
			System.out.print(PROMPT);
			String input = reader.nextLine();
			try {
				instruction = Interpreter.generateInstruction(input);
			} catch (WrongInstructionFormatException e) {
				robot.requestError(e.getMessage());
			}
			robot.communicateRobot(instruction);
			robot.engineOff(ship);
			
		}
		reader.close();

	}
}
