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
	 * Set the execution context. The method receives the entire engine 
	 * (engine, navigation and the robot container) even though the actual implementation
	 *  of execute() may not require it.
	 * 
	 * engine 
	 * 		The robot engine
     * navigation 
     * 		The information about the game, i.e., the places, current direction and 
     * 		current heading to navigate
     * robotContainer  
     * 		The inventory of the robot 
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
			robot.saySomething(MOVE + navigation.getCurrentHeading());
		} catch (InstructionExecutionException e) {
			throw new InstructionExecutionException(e);
		}

	}

}
