package tp.pr5.gui;

import tp.pr5.Controller;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.DropInstruction;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.MoveInstruction;
import tp.pr5.instructions.OperateInstruction;
import tp.pr5.instructions.PickInstruction;
import tp.pr5.instructions.TurnInstruction;
import tp.pr5.instructions.exceptions.InstructionExecutionException;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          The controller employed when the application is configured as a
 *          swing application. It is responsible for requesting the robot engine
 *          start and it redirects the actions performed by the user on the
 *          window to the robot engine.
 */

public class GUIController extends Controller {

	/**
	 * 
	 * Constructor that uses the model
	 * 
	 * @param robot
	 *            The reference to the model
	 * 
	 */

	public GUIController(RobotEngine robot) {
		super(robot);
	}

	public void startGuiController() {
		robot.requestStart();
	}

	/**
	 * Create a new DropInstruction when dropButton has pressed and communicate
	 * it to robot
	 * 
	 * @param id
	 *            item selected
	 */

	public void dropPressed(String id) {
		Instruction dropInstruction = new DropInstruction(id);
		robot.communicateRobot(dropInstruction);

	}

	/**
	 * Create a new MoveInstruction when moveButton has pressed and communicate
	 * it to robot
	 * 
	 */

	public void movePressed() {
		Instruction moveInstruction = new MoveInstruction();
		robot.communicateRobot(moveInstruction);

	}

	/**
	 * Create a new OperateInstruction when operateButton has pressed and
	 * communicate it to robot
	 * 
	 * @param id
	 *            item selected
	 */

	public void operatePressed(String id) {
		Instruction operateInstruction = new OperateInstruction(id);
		robot.communicateRobot(operateInstruction);
	}

	/**
	 * Create a new PickInstruction when pickButton has pressed and communicate
	 * it to robot
	 * 
	 * @param id
	 *            item selected
	 */

	public void pickPressed(String id) {
		Instruction pickInstruction = new PickInstruction(id);
		robot.communicateRobot(pickInstruction);

	}

	/**
	 * Create a new TurnInstruction when turnButton has pressed and communicate
	 * it to robot
	 * 
	 * @param rotation
	 *            direction
	 */

	public void turnPressed(Rotation rotation) {
		Instruction turnInstruction = new TurnInstruction(rotation);
		robot.communicateRobot(turnInstruction);

	}

}
