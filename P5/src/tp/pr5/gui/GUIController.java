package tp.pr5.gui;

import tp.pr5.Controller;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.Instruction;
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

	public void executeDropInstruction(Instruction dropInstruction) {
		try {
			robot.communicateRobot(dropInstruction);
		} catch (InstructionExecutionException e) {
			robot.requestError(e.getMessage());
		}

	}

	public void executeMoveInstruction(Instruction moveInstruction)
			throws InstructionExecutionException {
		try {
			robot.communicateRobot(moveInstruction);
		} catch (InstructionExecutionException e) {
			robot.requestError(e.getMessage());// TODO mirar si se puede
												// requestError y ademas lanzar
												// excepcion
			throw new InstructionExecutionException(e.getMessage());
		}

	}

	public void executeOperateInstruction(Instruction operateInstruction) throws InstructionExecutionException {
		try {
			robot.communicateRobot(operateInstruction);
		} catch (InstructionExecutionException e) {
			robot.requestError(e.getMessage());
			throw new InstructionExecutionException(e.getMessage());
		}//TODO preguntar cual es mejor para usar, try and cacth y lanzar excepcion o lanzar directamente excepcion
		
	}

	public void executePickInstruction(Instruction pickInstruction) throws InstructionExecutionException {
		robot.communicateRobot(pickInstruction);
	}

	public void executeTurnInstruction(Instruction turnInstruction) throws InstructionExecutionException {
		robot.communicateRobot(turnInstruction);
		
	}

}
