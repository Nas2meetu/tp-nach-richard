package tp.pr5.console;

import static tp.pr5.Constants.PROMPT;
import java.util.Scanner;
import tp.pr5.Controller;
import tp.pr5.Interpreter;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          The controller employed when the application is configured as a
 *          console application. It contains the simulation loop that executes
 *          the instructions written by the user on the console.
 */

public class ConsoleController extends Controller {

	public ConsoleController(RobotEngine robot) {
		super(robot);
	}

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

	public void startEngine() {

		Scanner reader = new Scanner(System.in);
		Instruction instruction = null;

		while (!robot.isOver()) {
			robot.saySomething(PROMPT);
			String input = reader.nextLine();

			try {
				instruction = Interpreter.generateInstruction(input);
				try {
					robot.communicateRobot(instruction);
				} catch (Exception e) {
					robot.requestError(e.getMessage());
				}
			} catch (WrongInstructionFormatException e) {
				robot.requestError(e.getMessage());
			}
		}
		reader.close();

	}
}
