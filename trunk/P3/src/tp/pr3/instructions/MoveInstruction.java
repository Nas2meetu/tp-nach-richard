package tp.pr3.instructions;

import java.util.StringTokenizer;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.intructions.exceptions.InstructionExecutionException;
import tp.pr3.intructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;
import static tp.pr3.Constants.*;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 3
 * 
 */

public class MoveInstruction implements Instruction {

	private NavigationModule navigation;

	/**
	 * Read a string with an action, compare if this action is correct 
	 * and generate MoveInstruction, else throw an exception.
	 */
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("MOVE")) || (words.equals("MOVER"))) {
			if (!st.hasMoreTokens())
				return new MoveInstruction();
			else
				throw new WrongInstructionFormatException(BAD_INSTRUCTION);
		} else
			throw new WrongInstructionFormatException(BAD_INSTRUCTION);
	}

	/**
	 * Show information about MOVE instruction syntax.
	 */
	
	@Override
	public String getHelp() {
		return "MOVE | MOVER";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.navigation = navigation;

	}
	
	/**
	 * Execute MOVE instruction
	 */

	@Override
	public void execute() throws InstructionExecutionException {
		navigation.move();
	}

	/*public Street getHeadingStreet() {
		City map = navigation.getCityMap();
		Place actualPlace = navigation.getCurrentPlace();
		Direction lookingDirection = navigation.getCurrentHeading();
		return map.lookForStreet(actualPlace, lookingDirection);
	}*/

}
