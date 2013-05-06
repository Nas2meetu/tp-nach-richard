package tp.pr5.instructions;

import java.util.StringTokenizer;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;
import static tp.pr5.Constants.*;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class MoveInstruction implements Instruction {

	private NavigationModule navigation;
	private RobotEngine robot;

	/**
	 * Read a string with an action, compare if this action is correct and
	 * generate MoveInstruction, else throw an exception.
	 */

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("MOVE")) || (words.equals("MOVER"))) {
			if (!st.hasMoreTokens())
				return new MoveInstruction();
			else
				throw new WrongInstructionFormatException();
		} else
			throw new WrongInstructionFormatException();
	}

	/**
	 * Show information about MOVE instruction syntax.
	 */

	@Override
	public String getHelp() {
		return "MOVE | MOVER";
	}

	/**
	 * 
	 * Method receives complete engine and use part of configureContext 
	 * depends of the instruction needs.
	 * 
	 * engine robot engine
     * navigation information about map (actualPlace, currentHeading, rotation...)
     * robotContainer inventory of robot 
	 * 
	 */
	
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.navigation = navigation;
		this.robot = engine;
	}

	/**
	 * Execute MOVE instruction
	 */

	@Override
	public void execute() throws InstructionExecutionException {
		try {
			navigation.move();
			robot.addFuel(-5);
			System.out.println(MOVE + navigation.getCurrentHeading());
			//robot.printCurrentPlace();
			//robot.printRobotExecuteState();

		} catch (InstructionExecutionException e) {
			throw new InstructionExecutionException(e.getMessage());
		}

	}

}
