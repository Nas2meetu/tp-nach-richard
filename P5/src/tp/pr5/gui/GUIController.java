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
 */

public class GUIController extends Controller {

	public GUIController(RobotEngine robot) {
		super(robot);
	}

	public void startGuiController() {
		robot.requestStart();
	}

	public void dropPressed(String id) {
		Instruction dropInstruction = new DropInstruction(id);
		robot.communicateRobot(dropInstruction);
		
	}

	public void movePressed() {
		Instruction moveInstruction = new MoveInstruction();
		robot.communicateRobot(moveInstruction);
		
	}

	public void operatePressed(String id) {
		Instruction operateInstruction = new OperateInstruction(id);
		robot.communicateRobot(operateInstruction);
	}

	public void pickPressed(String id) {
		Instruction pickInstruction = new PickInstruction(id);
		robot.communicateRobot(pickInstruction);
		
	}

	public void turnPressed(Rotation itemAt) {
		Instruction turnInstruction = new TurnInstruction(itemAt);
		robot.communicateRobot(turnInstruction);
		
	}

}
