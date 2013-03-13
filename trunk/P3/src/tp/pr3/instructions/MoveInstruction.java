package tp.pr3.instructions;

import java.util.StringTokenizer;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
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
			System.out.println(navigation.getCurrentPlace().toString());
			System.out.println(POWER2 + robot.getFuel() + LINE_SEPARATOR
					+ RECYCLED_MATERIAL + robot.getRecycledMaterial()
					+ LINE_SEPARATOR);

		} catch (InstructionExecutionException e) {
			throw new InstructionExecutionException();
		}

	}

}
